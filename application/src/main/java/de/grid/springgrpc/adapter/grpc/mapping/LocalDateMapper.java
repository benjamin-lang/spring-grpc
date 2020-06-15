package de.grid.springgrpc.adapter.grpc.mapping;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

@Component
public class LocalDateMapper
{
    public String map(LocalDate date)
    {
        if (Objects.isNull(date))
            return null;

        return date.toString();
    }

    public LocalDate map(String value)
    {
        if (StringUtils.isEmpty(value))
            return null;

        return LocalDate.parse(value);
    }
}
