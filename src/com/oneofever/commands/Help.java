package com.oneofever.commands;

import java.util.LinkedList;

public class Help extends AbstractCommand
{
    LinkedList<AbstractCommand> commands;
/*
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
*/
    @SuppressWarnings("unchecked")
    public void setCommands(LinkedList<AbstractCommand> commands) {
        this.commands = (LinkedList<AbstractCommand>) commands.clone();
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
        for (AbstractCommand command : commands) {
        System.err.println(command.name() + " - " + command.description());
        }
    }
}
