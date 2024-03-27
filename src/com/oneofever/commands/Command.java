/* (C)2024 - one-of-the-teams-ever */
package com.oneofever.commands;

import com.oneofever.Pair;
import com.oneofever.parsing.Fulfillable;
import java.util.ArrayList;
import java.util.Hashtable;

public abstract class Command {
    public abstract String name();

    public abstract String description();

    public String usage() {
        return "Usage:\n\t" + name() + " " + getArgumentTree();
    }

    public abstract void run(Hashtable<String, Pair<Integer, ArrayList<Double>>> values);

    public abstract Fulfillable getArgumentTree();
}
