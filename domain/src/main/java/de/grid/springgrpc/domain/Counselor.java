package de.grid.springgrpc.domain;

public class Counselor implements PersonalityType
{
    @Override
    public String getDescription()
    {
        return "INFJs are creative nurturers with a strong sense of personal integrity and a drive to help others realize their potential." +
            " Creative and dedicated, they have a talent for helping others with original solutions to their personal challenges.";
    }
}
