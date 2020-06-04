package de.grid.springgrpc.domain;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonServiceTest
{
    private final PersonService personService = new PersonService(new PersonRepositoryInMemory());
    private final List<Person> testPeople = new ArrayList<>();

    @BeforeEach
    public void init()
    {
        // create 3 testable people
        for (int i = 0; i < 3; i++)
        {
            Person testPerson = randomPerson();
            personService.registerPerson(testPerson);
            testPeople.add(testPerson);
        }
    }

    private static Person randomPerson()
    {
        return new Person(UUID.randomUUID(), RandomStringUtils.randomAlphabetic(20), RandomStringUtils.randomAlphabetic(20)
            , LocalDate.ofEpochDay(RandomUtils.nextLong(1, 10000)), Gender.MALE);
    }

    @Test
    void registerPerson()
    {
        Person person = randomPerson();

        personService.registerPerson(person);

        Optional<Person> queriedPerson = personService.queryPerson(person.getId());

        assertTrue(queriedPerson.isPresent());
        assertEquals(person.getId(), queriedPerson.get().getId());
        assertEquals(person.getName(), queriedPerson.get().getName());
        assertEquals(person.getFirstname(), queriedPerson.get().getFirstname());
        assertEquals(person.getDateOfBirth(), queriedPerson.get().getDateOfBirth());
        assertEquals(person.getGender(), queriedPerson.get().getGender());
    }

    @Test
    void queryPerson()
    {
        Optional<Person> queriedPerson = personService.queryPerson(testPeople.get(0).getId());

        assertTrue(queriedPerson.isPresent());
        assertEquals(testPeople.get(0).getId(), queriedPerson.get().getId());
        assertEquals(testPeople.get(0).getName(), queriedPerson.get().getName());
        assertEquals(testPeople.get(0).getFirstname(), queriedPerson.get().getFirstname());
        assertEquals(testPeople.get(0).getDateOfBirth(), queriedPerson.get().getDateOfBirth());
        assertEquals(testPeople.get(0).getGender(), queriedPerson.get().getGender());
    }

    @Test
    void queryAll()
    {
        List<Person> people = personService.queryAll();

        assertTrue(Objects.nonNull(people.get(0)));
        assertEquals(testPeople.get(0).getId(), people.get(0).getId());
        assertEquals(testPeople.get(0).getName(), people.get(0).getName());
        assertEquals(testPeople.get(0).getFirstname(), people.get(0).getFirstname());
        assertEquals(testPeople.get(0).getDateOfBirth(), people.get(0).getDateOfBirth());
        assertEquals(testPeople.get(0).getGender(), people.get(0).getGender());

        assertTrue(Objects.nonNull(people.get(1)));
        assertEquals(testPeople.get(1).getId(), people.get(1).getId());
        assertEquals(testPeople.get(1).getName(), people.get(1).getName());
        assertEquals(testPeople.get(1).getFirstname(), people.get(1).getFirstname());
        assertEquals(testPeople.get(1).getDateOfBirth(), people.get(1).getDateOfBirth());
        assertEquals(testPeople.get(1).getGender(), people.get(1).getGender());

        assertTrue(Objects.nonNull(people.get(2)));
        assertEquals(testPeople.get(2).getId(), people.get(2).getId());
        assertEquals(testPeople.get(2).getName(), people.get(2).getName());
        assertEquals(testPeople.get(2).getFirstname(), people.get(2).getFirstname());
        assertEquals(testPeople.get(2).getDateOfBirth(), people.get(2).getDateOfBirth());
        assertEquals(testPeople.get(2).getGender(), people.get(2).getGender());
    }

    @Test
    void removeAll()
    {
        personService.removeAll();

        List<Person> people = personService.queryAll();

        assertEquals(0, people.size());
    }

    @Test
    void removePerson()
    {
        personService.removePerson(testPeople.get(0).getId());

        Optional<Person> person = personService.queryPerson(testPeople.get(0).getId());

        assertTrue(person.isEmpty());
    }

    @Test
    void gotMarried()
    {
        String newName = RandomStringUtils.randomAlphabetic(23);

        personService.gotMarried(testPeople.get(0).getId(), newName);

        Optional<Person> person = personService.queryPerson(testPeople.get(0).getId());

        assertTrue(person.isPresent());
        assertEquals(newName, person.get().getName());
    }
}