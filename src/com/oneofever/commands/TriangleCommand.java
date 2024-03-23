/* (C)2024 - one-of-the-teams-ever */
package com.oneofever.commands;

import com.oneofever.Pair;
import com.oneofever.parsing.Any;
import com.oneofever.parsing.Argument;
import com.oneofever.parsing.Fulfillable;
import java.util.ArrayList;
import java.util.Hashtable;

public class TriangleCommand extends Command {

    @Override
    public String name() {
        return "triangle";
    }

    @Override
    public String description() {
        return "display triangle info";
    }

    @Override
    public void run(Hashtable<String, Pair<Integer, ArrayList<Double>>> values) {
        try {
            com.oneofever.shapes.Triangle triangle = new com.oneofever.shapes.Triangle(values);

            System.out.println("side = " + triangle.getSide());
            System.out.println("height = " + triangle.getHeight());
            System.out.println("area = " + triangle.getArea());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return;
        }
    }

    @Override
    public String usage() {
        return "Usage:\n"
                + //
                "\ttriangle {side | height | area} <value>";
    }

    public Fulfillable getArgumentTree() {
        return new Any(
                1,
                new Fulfillable[] {
                    new Argument("side", 1), new Argument("height", 1), new Argument("area", 1)
                });
    }
}
