package de.grid.springgrpc.adapter.grpc.controller;

import de.grid.grpcdemo.adapter.grpc.service.*;
import de.grid.springgrpc.adapter.grpc.mapping.MappingProto;
import de.grid.springgrpc.domain.Person;
import de.grid.springgrpc.domain.PersonDomainService;
import io.envoyproxy.pgv.ReflectiveValidatorIndex;
import io.envoyproxy.pgv.ValidationException;
import io.envoyproxy.pgv.ValidatorIndex;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@GRpcService
public class PersonGrpcController extends PersonServiceGrpc.PersonServiceImplBase
{
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonGrpcController.class);

    private final PersonDomainService personDomainService;
    private final MappingProto mappingProto;

    public PersonGrpcController(PersonDomainService personDomainService, MappingProto mappingProto)
    {
        this.personDomainService = personDomainService;
        this.mappingProto = mappingProto;
    }

    @Override
    public void createPerson(CreatePersonRequest request, StreamObserver<CreatePersonResponse> responseObserver)
    {
        try
        {
            validatePerson(request.getPerson());
        } catch (ValidationException e)
        {
            responseObserver.onError(Status.FAILED_PRECONDITION.withDescription(e.getMessage()).asException());
        }

        UUID personId = personDomainService.registerPerson(mappingProto.map(request.getPerson()));

        responseObserver.onNext(CreatePersonResponse.newBuilder().setPersonId(personId.toString()).build());
        responseObserver.onCompleted();
    }

    private void validatePerson(PersonDto person) throws ValidationException
    {
        ValidatorIndex index = new ReflectiveValidatorIndex();
        index.validatorFor(PersonDto.class).assertValid(person);
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

        Optional<Person> person = personDomainService.queryPerson(UUID.fromString(request.getPersonId()));

        if (person.isPresent())
        {
            PersonDto personDto = PersonDto.newBuilder()
                .setId(person.get().getId().toString())
                .setFirstname(person.get().getFirstname())
                .setName(person.get().getName())
                .setDateOfBirth(person.get().getDateOfBirth().toString())
                .setGender(GenderDto.valueOf(person.get().getGender().toString()))
                .build();

            responseObserver.onNext(QueryPersonResponse.newBuilder().setPerson(personDto).build());
            responseObserver.onCompleted();
        } else
            responseObserver.onError(new NoSuchElementException(String.format("Person with id='%s' not found!", request.getPersonId())));
    }

    @Override
    public void queryAllPersons(QueryAllPersonsRequest request, StreamObserver<QueryAllPersonsResponse> responseObserver)
    {
        List<Person> persons = personDomainService.queryAll();

        QueryAllPersonsResponse response = QueryAllPersonsResponse.newBuilder().addAllPersons(persons.stream().map(mappingProto::map)
            .collect(Collectors.toList())).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
