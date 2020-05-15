package de.grid.springgrpc.application;

import de.grid.springgrpc.domain.PersonRepository;
import de.grid.springgrpc.domain.PersonService;
import de.grid.springgrpc.persistence.PersonRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class GrpcDemoConfig
{
    @Bean
    public PersonService personService(PersonRepository personRepository)
    {
        return new PersonService(personRepository);
    }

    @Bean
    public PersonRepositoryJpa personRepository(EntityManager entityManager)
    {
        return new PersonRepositoryJpa(entityManager);
    }
}
