package de.grid.springgrpc.persistence;

import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends PersonRepositoryBasic, de.grid.springgrpc.domain.PersonRepository
{
}
