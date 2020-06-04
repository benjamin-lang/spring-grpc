package de.grid.springgrpc.application;

import de.grid.springgrpc.domain.PersonRepository;
import de.grid.springgrpc.domain.PersonService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcDemoConfig
{
    @Bean
    public PersonService personService(PersonRepository personRepository)
    {
        return new PersonService(personRepository);
    }
}
