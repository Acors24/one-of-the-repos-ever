/* (C)2024 - one-of-the-teams-ever */
package com.oneofever.parsing;

import com.oneofever.Pair;
import java.util.ArrayList;
import java.util.Hashtable;

public class ArgumentHandler {
    Fulfillable argumentTree;
    String currentArgument;
    Hashtable<String, Pair<Integer, ArrayList<Double>>> values;

    public ArgumentHandler(Fulfillable argumentTree) {
        this.argumentTree = argumentTree;
        currentArgument = null;
        values = new Hashtable<>();
    }

    public void handleArgumentName(String name) throws IllegalArgumentException {
        if (currentArgument != null) {
            throw new IllegalArgumentException("Not enough values for '" + currentArgument + "'");
        }

        if (!argumentTree.containsName(name)) {
            throw new IllegalArgumentException("Unrecognized argument: " + name);
        }

        if (values.containsKey(name)) {
            throw new IllegalArgumentException("Redeclaration of '" + name + "'");
        }

        currentArgument = name;
        values.put(
                name,
                new Pair<Integer, ArrayList<Double>>(
                        argumentTree.getExpectedAmount(name), new ArrayList<>()));
    }

    public ArgumentState handleValue(Double value) throws IllegalArgumentException {
        if (currentArgument == null) {
            throw new IllegalArgumentException("Unexpected value: " + value);
        }

        var entry = values.get(currentArgument);
        entry.b.add(value);
        if (entry.a == entry.b.size()) {
            currentArgument = null;
        }

        return argumentTree.getState(values);
    }

    public Hashtable<String, Pair<Integer, ArrayList<Double>>> values() {
        return values;
    }
}
