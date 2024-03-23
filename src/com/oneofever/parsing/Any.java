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
    public boolean isFulfilled(Hashtable<String, Pair<Integer, ArrayList<Double>>> values) {
        return arguments.stream().filter(argument -> argument.isFulfilled(values)).count()
                == requiredN;
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
}
