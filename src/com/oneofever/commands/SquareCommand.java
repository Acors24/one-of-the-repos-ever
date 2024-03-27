/* (C)2024 - one-of-the-teams-ever */
package com.oneofever.commands;

import com.oneofever.Pair;
import com.oneofever.functions.History;
import com.oneofever.parsing.Any;
import com.oneofever.parsing.Argument;
import com.oneofever.parsing.Fulfillable;
import java.util.ArrayList;
import java.util.Hashtable;

public class SquareCommand extends Command {

    @Override
    public String name() {
        return "square";
    }

    @Override
    public String description() {
        return "display square info";
    }

    @Override
    public void run(Hashtable<String, Pair<Integer, ArrayList<Double>>> values) {
        try {
            com.oneofever.shapes.Square square = new com.oneofever.shapes.Square(values);

            System.out.println("side = " + square.getSide());
            System.out.println("diagonal = " + square.getDiagonal());
            System.out.println("area = " + square.getArea());
            History.add(name(), square);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return;
        }
    }

    public Fulfillable getArgumentTree() {
        return new Any(
                1,
                new Fulfillable[] {
                    new Argument("side", 1), new Argument("diagonal", 1), new Argument("area", 1)
                });
    }
}
