package de.grid.springgrpc;

import de.grid.springgrpc.domain.Gender;
import de.grid.springgrpc.domain.Person;
import de.grid.springgrpc.domain.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class GrpcDemoApplication implements CommandLineRunner
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GrpcDemoApplication.class);

    private final PersonService personService;

    public GrpcDemoApplication(PersonService personService)
    {
        this.personService = personService;
    }

    public static void main(String[] args)
    {
        SpringApplication.run(GrpcDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        Person person = new Person(UUID.randomUUID(), "Time", "Justin", LocalDate.now(), Gender.MALE);
        UUID personId = personService.createNewPerson(person);

        Optional<Person> personOpt = personService.queryPerson(personId);
        personOpt.ifPresent(readPerson -> LOGGER.info(person.toString()));
    }
}
