package com.oneofever.commands;

import com.oneofever.Pair;
import com.oneofever.parsing.Any;
import com.oneofever.parsing.Argument;
import com.oneofever.parsing.Fulfillable;
import java.util.ArrayList;
import java.util.Hashtable;

public class RectangleCommand extends Command {

    @Override
    public String name() {
        return "rectangle";
    }

    @Override
    public String description() {
        return "display rectangle info";
    }

    @Override
    public void run(Hashtable<String, Pair<Integer, ArrayList<Double>>> values) {
        try {
            com.oneofever.shapes.Rectangle rectangle = new com.oneofever.shapes.Rectangle(values);

            System.out.println(
                    "sides = [" + rectangle.getSide1() + ", " + rectangle.getSide2() + "]");
            System.out.println("diagonal = " + rectangle.getDiagonal());
            System.out.println("area = " + rectangle.getArea());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return;
        }
    }

    public Fulfillable getArgumentTree() {
        return new Any(
                1,
                new Fulfillable[] {
                    new Argument("sides", 2),
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
