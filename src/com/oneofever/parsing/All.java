package com.oneofever.parsing;

import com.oneofever.Pair;
import java.util.ArrayList;
import java.util.Hashtable;

public class All extends ArgumentGroup {
    public All(Fulfillable[] arguments) {
        super(arguments);
    }

    @Override
    public ArgumentState getState(Hashtable<String, Pair<Integer, ArrayList<Double>>> values) {
        long complete = 0;
        long empty = 0;
        long excess = 0;

        for (Fulfillable argument : arguments) {
            switch (argument.getState(values)) {
                case COMPLETE -> complete++;
                case EMPTY -> empty++;
                case EXCESS -> excess++;
                default -> {}
            }
        }

        if (complete == arguments.size()) {
            return ArgumentState.COMPLETE;
        }

        if (empty == arguments.size()) {
            return ArgumentState.EMPTY;
        }

        if (excess != 0) {
            return ArgumentState.EXCESS;
        }

        return ArgumentState.PARTIAL;
    }

    @Override
    public Integer getExpectedAmount(String name) {
        return arguments.stream()
                .map(arg -> arg.getExpectedAmount(name))
                .filter(res -> res != null)
                .findFirst()
                .get();
    }

    @Override
    public boolean containsName(String name) {
        return arguments.stream().anyMatch(arg -> arg.containsName(name));
    }

    @Override
    public String toString() {
        return String.join(" ", arguments.stream().map(arg -> arg.toString()).toList());
    }
}
