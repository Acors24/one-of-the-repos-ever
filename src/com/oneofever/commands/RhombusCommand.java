package com.oneofever.commands;

import com.oneofever.shapes.Properties;
import com.oneofever.shapes.Rhombus;

public class RhombusCommand extends AbstractCommand {

    public RhombusCommand() {
        argGroups.add(new ArgGroup("side", "Double", 1));
        argGroups.add(new ArgGroup("diagonal1", "Double", 1));
        argGroups.add(new ArgGroup("diagonal2", "Double", 1));
        argGroups.add(new ArgGroup("area", "Double", 1));
        groupNumber = 2;
    }

    @Override
    public String name() {
        return "rhombus";
    }

    @Override
    public String description() {
        return "display rhombus info";
    }

    @Override
    public void run() {
        try {
            Rhombus rhombus = new Rhombus(toProperties());

            System.out.println("side = " + rhombus.getSide());
            System.out.println("diagonal1 = " + rhombus.getDiagonal1());
            System.out.println("diagonal2 = " + rhombus.getDiagonal2());
            System.out.println("area = " + rhombus.getArea());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return;
        }
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
                    case "side":
                        if (value != null) {
                            props.setSides(new Double[] {value});
                        }
                        break;
                    case "diagonal1":
                        if (value != null) {
                            Double[] diagonals = props.getDiagonals();
                            if (diagonals == null) diagonals = new Double[] {value, null};
                            else diagonals[0] = value;
                            props.setDiagonals(diagonals);
                        }
                        break;
                    case "diagonal2":
                        if (value != null) {
                            Double[] diagonals = props.getDiagonals();
                            if (diagonals == null) diagonals = new Double[] {null, value};
                            else diagonals[1] = value;
                            props.setDiagonals(diagonals);
                        }
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
