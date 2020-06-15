package de.grid.springgrpc.persistence;

import de.grid.springgrpc.domain.PersonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepositoryJpa extends PersonRepositoryJpaBasic, PersonRepository
{
}
