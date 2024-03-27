/* (C)2024 - one-of-the-teams-ever */
package com.oneofever.commands;

import com.oneofever.Pair;
import com.oneofever.parsing.All;
import com.oneofever.parsing.Fulfillable;
import java.util.ArrayList;
import java.util.Hashtable;

public class Exit extends Command {

    @Override
    public String name() {
        return "exit";
    }

    @Override
    public String description() {
        return "exit the program";
    }

    @Override
    public String usage() {
        return "exit";
    }

    @Override
    public void run(Hashtable<String, Pair<Integer, ArrayList<Double>>> values) {
        System.exit(0);
    }

    @Override
    public Fulfillable getArgumentTree() {
        return new All(new Fulfillable[] {});
    }
}
