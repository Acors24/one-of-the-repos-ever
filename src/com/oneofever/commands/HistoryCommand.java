/* (C)2024 - one-of-the-teams-ever */
package com.oneofever.commands;

import com.oneofever.Pair;
import com.oneofever.functions.History;
import com.oneofever.parsing.All;
import com.oneofever.parsing.Fulfillable;
import java.util.ArrayList;
import java.util.Hashtable;

public class HistoryCommand extends Command {
    @Override
    public String name() {
        return "history";
    }

    @Override
    public String description() {
        return "display created shapes";
    }

    @Override
    public String usage() {
        return "history";
    }

    @Override
    public void run(Hashtable<String, Pair<Integer, ArrayList<Double>>> values) {
        String[] createdShapes = History.getHistory();
        for (String s : createdShapes) System.out.println(s);
    }

    @Override
    public Fulfillable getArgumentTree() {
        return new All(new Fulfillable[] {});
    }
}
