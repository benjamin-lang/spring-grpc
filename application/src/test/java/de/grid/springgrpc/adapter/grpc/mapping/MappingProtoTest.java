package de.grid.springgrpc.adapter.grpc.mapping;

import de.grid.grpcdemo.adapter.grpc.service.GenderDto;
import de.grid.grpcdemo.adapter.grpc.service.PersonDto;
import de.grid.springgrpc.GrpcDemoApplication;
import de.grid.springgrpc.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = GrpcDemoApplication.class)
class MappingProtoTest
{
    private final Person testPerson;
    private final PersonDto testPersonDto;

    @Autowired
    private MappingProto mapper;

    MappingProtoTest()
    {
        UUID personId = UUID.randomUUID();
        String name = "Time";
        String firstname = "Justin";
        LocalDate dateOfBirth = LocalDate.now();
        Gender gender = Gender.MALE;
        PersonalityType personalityType = new Architect();

        testPerson = new Person(personId, name, firstname, dateOfBirth, gender, personalityType);

        testPersonDto = PersonDto.newBuilder()
            .setId(personId.toString())
            .setName(name)
            .setFirstname(firstname)
            .setDateOfBirth(dateOfBirth.toString())
            .setGender(GenderDto.valueOf(gender.toString()))
            .build();
    }

    @Test
    void mapPersonTest_PersonalityArchitect()
    {
        PersonDto personDto = mapper.map(testPerson);

        assertEquals(testPerson.getId().toString(), personDto.getId());
        assertEquals(testPerson.getName(), personDto.getName());
        assertEquals(testPerson.getFirstname(), personDto.getFirstname());
        assertEquals(testPerson.getDateOfBirth().toString(), personDto.getDateOfBirth());
        assertEquals(testPerson.getGender().toString(), personDto.getGender().toString());

        assertEquals(PersonDto.PersonalityTypeCase.ARCHITECT, personDto.getPersonalityTypeCase());
        assertEquals(testPerson.getPersonalityType().getDescription(), personDto.getArchitect().getDescription());
    }

    @Test
    void mapPersonTest_PersonalityCounselor()
    {
        Counselor counselor = new Counselor();
        PersonDto personDto = mapper.map(new Person(testPerson.getId(), testPerson.getName(), testPerson.getFirstname(), testPerson.getDateOfBirth()
            , testPerson.getGender(), counselor));

        assertEquals(testPerson.getId().toString(), personDto.getId());
        assertEquals(testPerson.getName(), personDto.getName());
        assertEquals(testPerson.getFirstname(), personDto.getFirstname());
        assertEquals(testPerson.getDateOfBirth().toString(), personDto.getDateOfBirth());
        assertEquals(testPerson.getGender().toString(), personDto.getGender().toString());

        assertEquals(PersonDto.PersonalityTypeCase.COUNSELOR, personDto.getPersonalityTypeCase());
        assertEquals(counselor.getDescription(), personDto.getCounselor().getDescription());
    }

    @Test
    void mapPersonTest_PersonalityHealer()
    {
        Healer healer = new Healer();
        PersonDto personDto = mapper.map(new Person(testPerson.getId(), testPerson.getName(), testPerson.getFirstname(), testPerson.getDateOfBirth()
            , testPerson.getGender(), healer));

        assertEquals(testPerson.getId().toString(), personDto.getId());
        assertEquals(testPerson.getName(), personDto.getName());
        assertEquals(testPerson.getFirstname(), personDto.getFirstname());
        assertEquals(testPerson.getDateOfBirth().toString(), personDto.getDateOfBirth());
        assertEquals(testPerson.getGender().toString(), personDto.getGender().toString());

        assertEquals(PersonDto.PersonalityTypeCase.HEALER, personDto.getPersonalityTypeCase());
        assertEquals(healer.getDescription(), personDto.getHealer().getDescription());
    }

    @Test
    void mapPersonTest_PersonalityMastermind()
    {
        Mastermind mastermind = new Mastermind();
        PersonDto personDto = mapper.map(new Person(testPerson.getId(), testPerson.getName(), testPerson.getFirstname(), testPerson.getDateOfBirth()
            , testPerson.getGender(), mastermind));

        assertEquals(testPerson.getId().toString(), personDto.getId());
        assertEquals(testPerson.getName(), personDto.getName());
        assertEquals(testPerson.getFirstname(), personDto.getFirstname());
        assertEquals(testPerson.getDateOfBirth().toString(), personDto.getDateOfBirth());
        assertEquals(testPerson.getGender().toString(), personDto.getGender().toString());

        assertEquals(PersonDto.PersonalityTypeCase.MASTERMIND, personDto.getPersonalityTypeCase());
        assertEquals(mastermind.getDescription(), personDto.getMastermind().getDescription());
    }

    @Test
    void mapGenderTest()
    {
        GenderDto genderDto = mapper.map(Gender.FEMALE);

        assertEquals(Gender.FEMALE.toString(), genderDto.toString());
    }

    @Test
    void mapPersonDtoTest()
    {
        Person person = mapper.map(testPersonDto);

        assertEquals(testPersonDto.getId(), person.getId().toString());
        assertEquals(testPersonDto.getName(), person.getName());
        assertEquals(testPersonDto.getFirstname(), person.getFirstname());
        assertEquals(testPersonDto.getDateOfBirth(), person.getDateOfBirth().toString());
        assertEquals(testPersonDto.getGender().toString(), person.getGender().toString());
    }

    @Test
    void mapGenderDto()
    {
        Gender gender = mapper.map(GenderDto.OTHER);

        assertEquals(Gender.OTHER.toString(), gender.toString());
    }
}