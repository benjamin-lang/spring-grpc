package de.grid.springgrpc.domain;

public class Architect implements PersonalityType
{
    @Override
    public String getDescription()
    {
        return "INTPs are philosophical innovators, fascinated by logical analysis, systems, and design." +
            " They are preoccupied with theory, and search for the universal law behind everything they see." +
            " They want to understand the unifying themes of life, in all their complexity.";
    }
}
