package de.grid.springgrpc.persistence;

import de.grid.springgrpc.domain.Person;
import de.grid.springgrpc.domain.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class PersonRepositoryJpaImpl implements PersonRepository
{
    private final PersonRepositoryJpaBasic personRepositoryJpaBasic;

    public PersonRepositoryJpaImpl(PersonRepositoryJpaBasic personRepositoryJpaBasic)
    {
        this.personRepositoryJpaBasic = personRepositoryJpaBasic;
    }

    @Override
    public void persist(Person person)
    {
        personRepositoryJpaBasic.save(PersonEntity.of(person));
    }

    @Override
    public Optional<Person> queryById(UUID personId)
    {
        return personRepositoryJpaBasic.findById(personId).map(PersonEntity::toPerson);
    }

    @Override
    public List<Person> queryAll()
    {
        List<PersonEntity> people = personRepositoryJpaBasic.findAll();

        return people.stream().map(PersonEntity::toPerson).collect(Collectors.toList());
    }

    @Override
    public void deleteAll()
    {
        personRepositoryJpaBasic.deleteAll();
    }

    @Override
    public void deleteById(UUID personId)
    {
        personRepositoryJpaBasic.deleteById(personId);
    }
}
