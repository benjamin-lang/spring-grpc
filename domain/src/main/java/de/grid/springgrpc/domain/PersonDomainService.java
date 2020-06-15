package de.grid.springgrpc.domain;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class PersonDomainService
{
    private final PersonRepository personRepository;

    public PersonDomainService(PersonRepository personRepository)
    {
        this.personRepository = personRepository;
    }

    public UUID registerPerson(Person person)
    {
        if (Objects.isNull(person.getId()))
        {
            UUID personId = UUID.randomUUID();

            personRepository.persist(new Person(personId, person.getName(), person.getFirstname(), person.getDateOfBirth(), person.getGender()
                , person.getPersonalityType()));

            return personId;
        }

        personRepository.persist(person);
        return person.getId();
    }

    public Optional<Person> queryPerson(UUID personId)
    {
        return personRepository.queryById(personId);
    }

    public List<Person> queryAll()
    {
        return personRepository.queryAll();
    }

    public void removeAll()
    {
        personRepository.deleteAll();
    }

    public void removePerson(UUID personId)
    {
        personRepository.deleteById(personId);
    }

    public void gotMarried(UUID id, String newName)
    {
        Optional<Person> personOpt = personRepository.queryById(id);

        if (personOpt.isPresent())
        {
            Person person = personOpt.get();
            personRepository.persist(new Person(person.getId(), newName, person.getFirstname(), person.getDateOfBirth(), person.getGender()
                , person.getPersonalityType()));
        }
    }
}
