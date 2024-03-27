package com.oneofever.parsing;

import com.oneofever.Pair;
import java.util.ArrayList;
import java.util.Hashtable;

public class Argument implements Fulfillable {
    public String name;
    public int desiredValueAmount;

    public Argument(String name, int desiredValueAmount) {
        this.name = name;
        this.desiredValueAmount = desiredValueAmount;
    }

    @Override
    public ArgumentState getState(Hashtable<String, Pair<Integer, ArrayList<Double>>> values) {
        if (values.containsKey(name) && values.get(name).b.size() == desiredValueAmount) {
            return ArgumentState.COMPLETE;
        } else {
            return ArgumentState.EMPTY;
        }
    }

    @Override
    public Integer getExpectedAmount(String name) {
        return this.name.equals(name) ? desiredValueAmount : null;
    }

    @Override
    public boolean containsName(String name) {
        return this.name.equals(name);
    }

    @Override
    public String toString() {
        String result = name;

        for (int i = 0; i < desiredValueAmount; i++) {
            result += " <value" + (i + 1) + ">";
        }

        return result;
    }
}
