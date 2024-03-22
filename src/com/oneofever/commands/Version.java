package com.oneofever.commands;

import com.oneofever.commands.CommandInfo;

public class Version extends ICommand
{
    @Override
    public String name()
    {
        return "version";
    }

    @Override
    public String description()
    {
        return "display program version";
    }

    @Override
    public void run(String[] tokens)
    {
        System.out.println("0.0.5");
    }
}
