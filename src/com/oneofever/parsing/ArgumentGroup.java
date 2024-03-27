package com.oneofever.parsing;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class ArgumentGroup implements Fulfillable {
    ArrayList<Fulfillable> arguments;

    ArgumentGroup(Fulfillable[] arguments) {
        this.arguments = new ArrayList<>(Arrays.asList(arguments));
    }
}
