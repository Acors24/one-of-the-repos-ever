/* (C)2024 - one-of-the-teams-ever */
package com.oneofever.parsing;

import com.oneofever.Pair;
import java.util.ArrayList;
import java.util.Hashtable;

public interface Fulfillable {
    public abstract ArgumentState getState(
            Hashtable<String, Pair<Integer, ArrayList<Double>>> values);

    public abstract Integer getExpectedAmount(String name);

    public abstract boolean containsName(String name);
}
