package com.oneofever.commands;

import com.oneofever.commands.CommandInfo;

public class Version extends ICommand
{
    public Version()
    {
        commandInfo.add(new CommandInfo("version", null, 0));
    }

    @Override
    public String[] next(String current)
    {
        return null;
    }

    @Override
    public Integer tokens(String current)
    {
        return 0;
    }

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
