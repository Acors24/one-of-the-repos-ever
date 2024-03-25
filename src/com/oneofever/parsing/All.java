package com.oneofever.parsing;

import com.oneofever.Pair;
import java.util.ArrayList;
import java.util.Hashtable;

public class All extends ArgumentGroup {
    public All(Fulfillable[] arguments) {
        super(arguments);
    }

    @Override
    public boolean isFulfilled(Hashtable<String, Pair<Integer, ArrayList<Double>>> values) {
        return arguments.stream().allMatch(argument -> argument.isFulfilled(values));
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
