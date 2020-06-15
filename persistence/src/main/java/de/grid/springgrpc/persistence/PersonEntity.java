package de.grid.springgrpc.persistence;

import de.grid.springgrpc.domain.Gender;
import de.grid.springgrpc.domain.Person;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "person")
public class PersonEntity
{
    @Id
    private UUID id;

    private String name;
    private String firstname;
    private LocalDate dateOfBirth;
    private Gender gender;

    public PersonEntity()
    {
        // required by jpa
    }

    public static PersonEntity of(Person person)
    {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setId(person.getId());
        personEntity.setName(person.getName());
        personEntity.setFirstname(person.getFirstname());
        personEntity.setDateOfBirth(person.getDateOfBirth());
        personEntity.setGender(person.getGender());

        return personEntity;
    }

    public Person toPerson()
    {
        return new Person(this.id, this.name, this.firstname, this.dateOfBirth, this.gender, null);
    }

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender()
    {
        return gender;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }
}
