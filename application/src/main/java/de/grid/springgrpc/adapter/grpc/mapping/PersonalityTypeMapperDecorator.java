package de.grid.springgrpc.adapter.grpc.mapping;

import de.grid.grpcdemo.adapter.grpc.service.*;
import de.grid.springgrpc.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class PersonalityTypeMapperDecorator implements MappingProto
{
    @Autowired
    @Qualifier("delegate")
    private MappingProto delegate;

    @Override
    public PersonDto map(Person person)
    {
        PersonDto personDto = delegate.map(person);

        PersonalityType personalityType = person.getPersonalityType();

        PersonDto.Builder builder = PersonDto.newBuilder(personDto);

        if (personalityType instanceof Architect)
        {
            builder.setArchitect(ArchitectDto.newBuilder().setDescription(personalityType.getDescription()).build());
        } else if (personalityType instanceof Healer)
        {
            builder.setHealer(HealerDto.newBuilder().setDescription(personalityType.getDescription()).build());
        } else if (personalityType instanceof Counselor)
        {
            builder.setCounselor(CounselorDto.newBuilder().setDescription(personalityType.getDescription()).build());
        } else if (personalityType instanceof Mastermind)
        {
            builder.setMastermind(MastermindDto.newBuilder().setDescription(personalityType.getDescription()).build());
        }

        return builder.build();
    }
}
