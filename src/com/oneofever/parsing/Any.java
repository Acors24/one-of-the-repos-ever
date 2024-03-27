package com.oneofever.parsing;

import com.oneofever.Pair;
import java.util.ArrayList;
import java.util.Hashtable;

public class Any extends ArgumentGroup {
    int requiredN;

    public Any(int requiredN, Fulfillable[] arguments) {
        super(arguments);
        this.requiredN = requiredN;
    }

    @Override
    public ArgumentState getState(Hashtable<String, Pair<Integer, ArrayList<Double>>> values) {
        long complete = 0;
        long partial = 0;
        long empty = 0;
        long excess = 0;

        for (Fulfillable argument : arguments) {
            switch (argument.getState(values)) {
                case COMPLETE -> complete++;
                case PARTIAL -> partial++;
                case EMPTY -> empty++;
                case EXCESS -> excess++;
            }
        }

        if (excess != 0 || complete > requiredN) {
            return ArgumentState.EXCESS;
        }

        if (complete == requiredN) {
            if (partial != 0) {
                return ArgumentState.EXCESS;
            } else {
                return ArgumentState.COMPLETE;
            }
        } else {
            if (empty == arguments.size()) {
                return ArgumentState.EMPTY;
            } else {
                return ArgumentState.PARTIAL;
            }
        }
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
        String result = "{ ";

        result += String.join(" | ", arguments.stream().map(arg -> arg.toString()).toList());

        return result + (requiredN == 1 ? " }" : " }{" + requiredN + "}");
    }
}
