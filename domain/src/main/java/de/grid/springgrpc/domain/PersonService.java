package de.grid.springgrpc.domain;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class PersonService
{
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository)
    {
        this.personRepository = personRepository;
    }

    public UUID createNewPerson(Person person)
    {
        if (Objects.isNull(person.getId()))
        {
            UUID personId = UUID.randomUUID();
            personRepository.persist(new Person(personId, person.getName(), person.getFirstname(), person.getDateOfBirth(), person.getGender()));
            return personId;
        }

        personRepository.persist(person);
        return person.getId();
    }

    public Optional<Person> queryPerson(UUID personId)
    {
        return personRepository.queryById(personId);
    }

    public List<Person> queryAllPersons()
    {
        return personRepository.queryAll();
    }
}
