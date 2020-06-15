package de.grid.springgrpc.domain;

public class Healer implements PersonalityType
{
    @Override
    public String getDescription()
    {
        return "INFPs are imaginative idealists, guided by their own core values and beliefs." +
            " To a Healer, possibilities are paramount; the reality of the moment is only of passing concern." +
            " They see potential for a better future, and pursue truth and meaning with their own flair.";
    }
}
