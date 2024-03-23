/* (C)2024 - one-of-the-teams-ever */
package com.oneofever.commands;

import com.oneofever.shapes.Properties;
import com.oneofever.shapes.Rectangle;

public class RectangleCommand extends AbstractCommand {

    public RectangleCommand() {
        argGroups.add(new ArgGroup("side_s", "Double", 1));
        argGroups.add(new ArgGroup("side_l", "Double", 1));
        argGroups.add(new ArgGroup("diagonal", "Double", 1));
        argGroups.add(new ArgGroup("area", "Double", 1));
        groupNumber = 2;
    }

    @Override
    public String name() {
        return "rectangle";
    }

    @Override
    public String description() {
        return "display rectangle info";
    }

    @Override
    public void run() {
        try {
            Rectangle rectangle = new Rectangle(toProperties());

            System.out.println("shorter side = " + rectangle.getSideS());
            System.out.println("longer side = " + rectangle.getSideL());
            System.out.println("diagonal = " + rectangle.getDiagonal());
            System.out.println("area = " + rectangle.getArea());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return;
        }
    }

    @Override
    public String usage() {
        return "Usage:\n"
                + //
                "\trectangle {side_s | side_l | diagonal | area} <value1> {side_s | side_l |"
                + " diagonal | area} <value2>";
    }

    @Override
    public Properties toProperties() {
        Properties props = new Properties();
        try {
            for (ArgGroup arg : argGroups) {
                if (arg.contents.isEmpty()) continue;
                Object obj = arg.contents.get(0);
                Double value = null;
                if (obj != null) {
                    value = Double.parseDouble((String) obj);
                }
                switch (arg.name) {
                    case "side_s":
                        if (value != null) {
                            Double[] sides = props.getSides();
                            if (sides == null) sides = new Double[] {value, null};
                            else sides[0] = value;
                            props.setSides(sides);
                        }
                        break;
                    case "side_l":
                        if (value != null) {
                            Double[] sides = props.getSides();
                            if (sides == null) sides = new Double[] {null, value};
                            else sides[1] = value;
                            props.setSides(sides);
                        }
                        break;
                    case "diagonal":
                        if (value != null) props.setDiagonals(new Double[] {value});
                        break;
                    case "area":
                        if (value != null) props.setArea(value);
                        break;
                    default:
                        break;
                }
            }
        } catch (NumberFormatException ex) {
            System.out.println("Parser failed: " + ex);
        }
        return props;
    }
}
