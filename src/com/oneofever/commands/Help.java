package com.oneofever.commands;

import java.util.LinkedList;

public class Help extends ICommand
{
    LinkedList<ICommand> commands;

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

    @SuppressWarnings("unchecked")
    public void setCommands(LinkedList<ICommand> commands) {
        this.commands = (LinkedList<ICommand>) commands.clone();
    }

    @Override
    public String name() {
        return "help";
    }

    @Override
    public String description() {
        return "display help";
    }

    @Override
    public void run(String[] tokens) {
        for (ICommand command : commands) {
        System.err.println(command.name() + " - " + command.description());
        }
    }
}
