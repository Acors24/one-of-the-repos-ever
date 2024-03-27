package com.oneofever.commands;

import com.oneofever.Pair;
import com.oneofever.functions.History;
import com.oneofever.parsing.Any;
import com.oneofever.parsing.Argument;
import com.oneofever.parsing.Fulfillable;
import java.util.ArrayList;
import java.util.Hashtable;

public class RhombusCommand extends Command {

    @Override
    public String name() {
        return "rhombus";
    }

    @Override
    public String description() {
        return "display rhombus info";
    }

    @Override
    public void run(Hashtable<String, Pair<Integer, ArrayList<Double>>> values) {
        try {
            com.oneofever.shapes.Rhombus rhombus = new com.oneofever.shapes.Rhombus(values);

            System.out.println("side = " + rhombus.getSide());
            System.out.println("diagonal1 = " + rhombus.getDiagonal1());
            System.out.println("diagonal2 = " + rhombus.getDiagonal2());
            System.out.println("area = " + rhombus.getArea());
            History.add(name(), rhombus);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return;
        }
    }

    public Fulfillable getArgumentTree() {
        return new Any(
                1,
                new Fulfillable[] {
                    new Argument("diagonals", 2),
                    new Any(
                            2,
                            new Fulfillable[] {
                                new Argument("side", 1),
                                new Argument("diagonal", 1),
                                new Argument("area", 1)
                            })
                });
    }
}
