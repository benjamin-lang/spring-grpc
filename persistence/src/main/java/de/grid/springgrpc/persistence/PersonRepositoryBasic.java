package de.grid.springgrpc.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepositoryBasic extends JpaRepository<PersonEntity, UUID>
{
}
