package de.grid.springgrpc.persistence;

import de.grid.springgrpc.domain.Person;
import de.grid.springgrpc.domain.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class PersonRepositoryImpl implements PersonRepository
{
    private final PersonRepositoryBasic personRepositoryBasic;

    public PersonRepositoryImpl(PersonRepositoryBasic personRepositoryBasic)
    {
        this.personRepositoryBasic = personRepositoryBasic;
    }

    @Override
    public void persist(Person person)
    {
        personRepositoryBasic.save(PersonEntity.of(person));
    }

    @Override
    public Optional<Person> queryById(UUID personId)
    {
        return personRepositoryBasic.findById(personId).map(PersonEntity::toPerson);
    }

    @Override
    public List<Person> queryAll()
    {
        List<PersonEntity> people = personRepositoryBasic.findAll();

        return people.stream().map(PersonEntity::toPerson).collect(Collectors.toList());
    }

    @Override
    public void deleteAll()
    {
        personRepositoryBasic.deleteAll();
    }

    @Override
    public void deleteById(UUID personId)
    {
        personRepositoryBasic.deleteById(personId);
    }
}
