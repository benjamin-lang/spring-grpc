package de.grid.springgrpc.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonRepository
{
    void persist(Person person);
    Optional<Person> queryById(UUID personId);
    List<Person> queryAll();
}
