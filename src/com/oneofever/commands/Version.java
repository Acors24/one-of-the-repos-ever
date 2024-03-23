/* (C)2024 - one-of-the-teams-ever */
package com.oneofever.commands;

import com.oneofever.Pair;
import com.oneofever.parsing.All;
import com.oneofever.parsing.Fulfillable;
import java.util.ArrayList;
import java.util.Hashtable;

public class Version extends Command {

    @Override
    public String name() {
        return "version";
    }

    @Override
    public String description() {
        return "display program version";
    }

    @Override
    public String usage() {
        return "version";
    }

    @Override
    public void run(Hashtable<String, Pair<Integer, ArrayList<Double>>> values) {
        System.out.println("0.0.5");
    }

    @Override
    public Fulfillable getArgumentTree() {
        return new All(new Fulfillable[] {});
    }
}
