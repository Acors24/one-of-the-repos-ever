package com.oneofever.commands;

public class CommandInfo
{
    private String activeModifier;
    private String[] validChanges;
    private Integer counter;

    public CommandInfo(String activeModifier, String[] validChanges, Integer counter)
    {
        this.activeModifier = activeModifier;
        this.validChanges = validChanges;
        this.counter = counter;
    }

    public String getActiveModifier()
    {
        return activeModifier;
    }

    public String[] getValidChanges()
    {
        return validChanges;
    }

    public Integer getCounter()
    {
        return counter;
    }

    @Override
    public String toString()
    {
        return this.getActiveModifier();
    }
}
