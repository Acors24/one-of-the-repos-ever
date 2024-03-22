package com.oneofever.commands;

import com.oneofever.shapes.Triangle.ArgType;
import com.oneofever.shapes.Properties;

public class TriangleCommand extends AbstractCommand {

    public TriangleCommand()
    {
        argGroups.add(new ArgGroup("side", "Double", 1));
        argGroups.add(new ArgGroup("height", "Double", 1));
        argGroups.add(new ArgGroup("area", "Double", 1));
        groupNumber = 1;
    }

    @Override
    public String name() {
        return "triangle";
    }

    @Override
    public String description() {
        return "display triangle info";
    }

    @Override
    public void run(String[] tokens) {
        if (tokens.length < 3) {
            System.err.println("Not enough arguments.\n" + usage());
            return;
        }

        ArgType argType = switch (tokens[1]) {
        case "side" -> ArgType.Side;
        case "height" -> ArgType.Height;
        case "area" -> ArgType.Area;
        default -> null;
        };

        if (argType == null) {
        System.err.println("Wrong arguments.\n" + usage());
        return;
        }

        double value = 0;
        try {
        value = Double.parseDouble(tokens[2]);
        } catch (NumberFormatException ex) {
        System.err.println("Wrong value format.\n" + usage());
        return;
        }

        try {
        com.oneofever.shapes.Triangle triangle = new com.oneofever.shapes.Triangle(argType, value);

        System.out.println("side = " + triangle.getSide());
        System.out.println("height = " + triangle.getHeight());
        System.out.println("area = " + triangle.getArea());
        } catch (IllegalArgumentException ex) {
        System.err.println(ex.getMessage());
        return;
        }
    }

    @Override
    public String usage() {
        return "Usage:\n" + //
                "\ttriangle {side | height | area} <value>";
    }

    @Override
    public Properties toProperties()
    {
        Properties props = new Properties();
        Double side = null;
        Double height = null;
        Double area = null;
        for (ArgGroup arg : argGroups)
        {
            Object obj = arg.contents.getFirst();
            Double value = null;
            if (obj != null && obj instanceof Double)
            {
                value = (Double) obj;
            }
            switch (arg.name)
            {
                case "side":
                    props.setSides(new Double[]{value});
                    break;
                case "height":
                    props.setHeights(new Double[]{value});
                    break;
                case "area":
                    props.setArea(value);
                    break;
                default:
                    break;
            }
        }
        return props;
    }
}
