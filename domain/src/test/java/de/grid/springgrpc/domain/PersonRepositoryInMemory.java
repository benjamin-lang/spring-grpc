package de.grid.springgrpc.domain;

import java.util.*;

public class PersonRepositoryInMemory implements PersonRepository
{
    private final Map<UUID, Person> personStore = new LinkedHashMap<>();

    @Override
    public void persist(Person person)
    {
        personStore.put(person.getId(), person);
    }

    @Override
    public Optional<Person> queryById(UUID personId)
    {
        return Optional.ofNullable(personStore.get(personId));
    }

    @Override
    public List<Person> queryAll()
    {
        return new ArrayList<>(personStore.values());
    }

    @Override
    public void deleteAll()
    {
        personStore.clear();
    }

    @Override
    public void deleteById(UUID personId)
    {
        personStore.remove(personId);
    }
}
