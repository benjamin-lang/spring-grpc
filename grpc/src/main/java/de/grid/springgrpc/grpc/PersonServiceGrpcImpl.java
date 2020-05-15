package de.grid.springgrpc.grpc;

import de.grid.grpcdemo.adapter.grpc.service.*;
import de.grid.springgrpc.domain.PersonService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@GRpcService
public class PersonServiceGrpcImpl extends PersonServiceGrpc.PersonServiceImplBase
{
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceGrpcImpl.class);
    private final PersonService personService;

    public PersonServiceGrpcImpl(PersonService personService)
    {
        this.personService = personService;
    }

    @Override
    public void createPerson(CreatePersonRequest request, StreamObserver<CreatePersonResponse> responseObserver)
    {
        UUID personId = personService.createNewPerson(map(request.getPerson()));

        responseObserver.onNext(CreatePersonResponse.newBuilder().setPersonId(personId.toString()).build());
        responseObserver.onCompleted();
    }

    private de.grid.springgrpc.domain.Person map(Person person)
    {
        return new de.grid.springgrpc.domain.Person(map(person.getId())
            , person.getName()
            , person.getFirstname()
            , LocalDate.parse(person.getDateOfBirth())
            , de.grid.springgrpc.domain.Gender.valueOf(person.getGender().toString()));
    }

    private UUID map(String id)
    {
        if (StringUtils.isEmpty(id))
            return null;

        return UUID.fromString(id);
    }

    @Override
    public void queryPerson(QueryPersonRequest request, StreamObserver<QueryPersonResponse> responseObserver)
    {
        LOGGER.info("server received request with payload:'{}'", request);

        Optional<de.grid.springgrpc.domain.Person> person = personService.queryPerson(UUID.fromString(request.getPersonId()));

        if (person.isPresent())
        {
            Person grpcPerson = Person.newBuilder()
                .setId(person.get().getId().toString())
                .setFirstname(person.get().getFirstname())
                .setName(person.get().getName())
                .setDateOfBirth(person.get().getDateOfBirth().toString())
                .setGender(Gender.valueOf(person.get().getGender().toString()))
                .build();

            responseObserver.onNext(QueryPersonResponse.newBuilder().setPerson(grpcPerson).build());
            responseObserver.onCompleted();
        } else
            responseObserver.onError(new NoSuchElementException(String.format("Person with id='%s' not found!", request.getPersonId())));
    }

    @Override
    public void queryAllPersons(QueryAllPersonsRequest request, StreamObserver<QueryAllPersonsResponse> responseObserver)
    {
        List<de.grid.springgrpc.domain.Person> persons = personService.queryAllPersons();
        responseObserver.onNext(QueryAllPersonsResponse.newBuilder().addAllPersons(persons.stream().map(this::map).collect(Collectors.toList())).build());
        responseObserver.onCompleted();
    }

    private Person map(de.grid.springgrpc.domain.Person person)
    {
        return Person.newBuilder()
            .setId(person.getId().toString())
            .setName(person.getName())
            .setFirstname(person.getFirstname())
            .setDateOfBirth(person.getDateOfBirth().toString())
            .setGender(Gender.valueOf(person.getGender().toString()))
            .build();
    }
}
