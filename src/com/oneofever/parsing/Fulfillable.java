package com.oneofever.parsing;

import com.oneofever.Pair;
import java.util.ArrayList;
import java.util.Hashtable;

public interface Fulfillable {
    public abstract boolean isFulfilled(Hashtable<String, Pair<Integer, ArrayList<Double>>> values);

    public abstract Integer getExpectedAmount(String name);

    public abstract boolean containsName(String name);
}
