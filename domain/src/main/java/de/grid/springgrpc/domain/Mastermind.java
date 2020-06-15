package de.grid.springgrpc.domain;

public class Mastermind implements PersonalityType
{
    @Override
    public String getDescription()
    {
        return "INTJs are analytical problem-solvers, eager to improve systems and processes with their innovative ideas." +
            " They have a talent for seeing possibilities for improvement, whether at work, at home, or in themselves.";
    }
}
