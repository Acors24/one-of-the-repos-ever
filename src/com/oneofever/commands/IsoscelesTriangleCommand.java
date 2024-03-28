/* (C)2024 - one-of-the-teams-ever */
package com.oneofever.commands;

import com.oneofever.Pair;
import com.oneofever.functions.History;
import com.oneofever.parsing.Any;
import com.oneofever.parsing.Argument;
import com.oneofever.parsing.Fulfillable;
import java.util.ArrayList;
import java.util.Hashtable;

public class IsoscelesTriangleCommand extends Command {

    @Override
    public String name() {
        return "isoscelestriangle";
    }

    @Override
    public String description() {
        return "display isosceles triangle info";
    }

    @Override
    public void run(Hashtable<String, Pair<Integer, ArrayList<Double>>> values) {
        try {
            com.oneofever.shapes.IsoscelesTriangle triangle =
                    new com.oneofever.shapes.IsoscelesTriangle(values);

            System.out.println("side = " + triangle.getSide());
            System.out.println("height = " + triangle.getHeight());
            System.out.println("area = " + triangle.getArea());
            System.out.println("base = " + triangle.getBase());
            History.add(name(), triangle);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return;
        }
    }

    public Fulfillable getArgumentTree() {
        return new Any(
                2,
                new Fulfillable[] { // baza pisał stalin
                    new Argument("side", 1),
                    new Argument("height", 1),
                    new Argument("area", 1),
                    new Argument("base", 1)
                });
    }
}
