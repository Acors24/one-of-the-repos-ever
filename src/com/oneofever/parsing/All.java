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
        var states = arguments.stream().map(argument -> argument.getState(values));

        if (states.allMatch(state -> state == ArgumentState.COMPLETE)) {
            return ArgumentState.COMPLETE;
        }

        if (states.allMatch(state -> state == ArgumentState.EMPTY)) {
            return ArgumentState.EMPTY;
        }

        if (states.anyMatch(state -> state == ArgumentState.EXCESS)) {
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
