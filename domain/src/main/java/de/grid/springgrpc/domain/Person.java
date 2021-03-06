package de.grid.springgrpc.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Person
{
    private final UUID id;
    private final String name;
    private final String firstname;
    private final LocalDate dateOfBirth;
    private final Gender gender;
    private final PersonalityType personalityType;

    public Person(UUID id, String name, String firstname, LocalDate dateOfBirth, Gender gender, PersonalityType personalityType)
    {
        this.id = id;
        this.name = name;
        this.firstname = firstname;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.personalityType = personalityType;
    }

    public PersonalityType getPersonalityType()
    {
        return personalityType;
    }

    public UUID getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }

    public Gender getGender()
    {
        return gender;
    }

    @Override
    public String toString()
    {
        return "Person{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", firstname='" + firstname + '\'' +
            ", dateOfBirth=" + dateOfBirth +
            ", gender=" + gender +
            '}';
    }
}
