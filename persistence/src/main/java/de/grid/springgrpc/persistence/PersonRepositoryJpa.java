package de.grid.springgrpc.persistence;

import de.grid.springgrpc.domain.Person;
import de.grid.springgrpc.domain.PersonRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
public class PersonRepositoryJpa extends SimpleJpaRepository<PersonEntity, UUID> implements PersonRepository
{
    public PersonRepositoryJpa(EntityManager entityManager)
    {
        super(PersonEntity.class, entityManager);
    }

    @Override
    public void persist(Person person)
    {
        save(PersonEntity.of(person));
    }

    @Override
    public Optional<Person> queryById(UUID personId)
    {
        Optional<PersonEntity> personOpt = findById(personId);

        return personOpt.map(PersonEntity::toPerson);
    }

    @Override
    public List<Person> queryAll()
    {
        List<PersonEntity> personEntities = findAll();

        return personEntities.stream().map(PersonEntity::toPerson).collect(Collectors.toList());
    }
}
