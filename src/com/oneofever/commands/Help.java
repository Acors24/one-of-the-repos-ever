/* (C)2024 - one-of-the-teams-ever */
package com.oneofever.commands;

import java.util.LinkedList;

public class Help extends AbstractCommand {
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
    public void run() {
        for (AbstractCommand command : commands) {
            System.err.println(command.name() + " - " + command.description());
        }
    }

    @Override
    public Help newObject() {
        Help newInstance = new Help();
        newInstance.setCommands(commands);
        return newInstance;
    }
}
