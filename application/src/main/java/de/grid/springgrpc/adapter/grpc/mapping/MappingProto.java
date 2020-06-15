package de.grid.springgrpc.adapter.grpc.mapping;

import de.grid.grpcdemo.adapter.grpc.service.ArchitectDto;
import de.grid.grpcdemo.adapter.grpc.service.GenderDto;
import de.grid.grpcdemo.adapter.grpc.service.PersonDto;
import de.grid.springgrpc.domain.Architect;
import de.grid.springgrpc.domain.Gender;
import de.grid.springgrpc.domain.Person;
import de.grid.springgrpc.domain.PersonalityType;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {UUIDMapper.class, LocalDateMapper.class})
@DecoratedWith(PersonalityTypeMapperDecorator.class)
public interface MappingProto
{
//    @Named("inchToCentimeter")
//    public static PersonDto inchToCentimeter(PersonalityType personalityType, PersonDto personDto)
//    {
//        return PersonDto.newBuilder(personDto).build();
//    }

    //    @Mapping(target = "id", expression = "java(person.getId().toString())")
    PersonDto map(Person person);

    @ValueMapping(source = "UNBEKANNT", target = "UNRECOGNIZED")
    GenderDto map(Gender gender);

    //    @Mapping(target = "id", expression = "java(UUID.fromString(personDto.getId()))")
    Person map(PersonDto personDto);

    @ValueMapping(source = "UNRECOGNIZED", target = "UNBEKANNT")
    Gender map(GenderDto genderDto);

//    @Mapping(source = "personalityType", target = "personalityType_", qualifiedByName = "inchToCentimeter")
//    void update(Person person, @MappingTarget PersonDto personDto);

//    @BeanMapping(resultType = ArchitectDto.class)
//    ArchitectDto map(PersonalityType personalityType);
}
