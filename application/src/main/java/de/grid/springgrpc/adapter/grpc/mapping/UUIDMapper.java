package de.grid.springgrpc.adapter.grpc.mapping;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDMapper
{
    public String map(UUID value)
    {
        return value.toString();
    }

    public UUID map(String value)
    {
        return UUID.fromString(value);
    }
}
