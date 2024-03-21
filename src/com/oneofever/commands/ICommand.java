package com.oneofever.commands;

import java.util.ArrayList;

public abstract class ICommand
{
    public ArrayList<CommandInfo> commandInfo = new ArrayList<CommandInfo>();
    public abstract String[] next(String current);
    public abstract Integer tokens(String current);

    public abstract String name();

    public abstract String description();

    public abstract void run(String[] tokens);
}
