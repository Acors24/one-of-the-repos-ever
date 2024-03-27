/* (C)2024 - one-of-the-teams-ever */
package com.oneofever.commands;

import com.oneofever.Pair;
import com.oneofever.parsing.All;
import com.oneofever.parsing.Fulfillable;
import java.util.ArrayList;
import java.util.Hashtable;

public class Help extends Command {

    ArrayList<Command> commands;

    @SuppressWarnings("unchecked")
    public void setCommands(ArrayList<Command> commands2) {
        this.commands = (ArrayList<Command>) commands2.clone();
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
    public String usage() {
        return "help";
    }

    @Override
    public void run(Hashtable<String, Pair<Integer, ArrayList<Double>>> values) {
        for (Command command : commands) {
            System.err.println(command.name() + " - " + command.description());
        }
    }

    @Override
    public Fulfillable getArgumentTree() {
        return new All(new Fulfillable[] {});
    }
}
