package de.grid.springgrpc.application;

import de.grid.springgrpc.domain.PersonRepository;
import de.grid.springgrpc.domain.PersonDomainService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcDemoConfig
{
    @Bean
    public PersonDomainService personService(@Qualifier("personRepositoryJpa") PersonRepository personRepository)
    {
        return new PersonDomainService(personRepository);
    }
}
