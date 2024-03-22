package com.oneofever.commands;

import com.oneofever.shapes.Square.ArgType;
import java.util.Optional;
import com.oneofever.shapes.Properties;

public class SquareCommand extends AbstractCommand {

    public SquareCommand()
    {
        argGroups.add(new ArgGroup("side", "Double", 1));
        argGroups.add(new ArgGroup("diagonal", "Double", 1));
        argGroups.add(new ArgGroup("area", "Double", 1));
        groupNumber = 1;
    }

    @Override
    public String name() {
        return "square";
    }

    @Override
    public String description() {
        return "display square info";
    }

    @Override
    public void run(String[] tokens) {
        if (tokens.length < 3) {
            System.err.println("Not enough arguments.\n" + usage());
            return;
        }

        ArgType argType = switch (tokens[1]) {
            case "side" -> ArgType.Side;
            case "diagonal" -> ArgType.Diagonal;
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
            com.oneofever.shapes.Square square = new com.oneofever.shapes.Square(argType, value);

            System.out.println("side = " + square.getSide());
            System.out.println("diagonal = " + square.getDiagonal());
            System.out.println("area = " + square.getArea());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return;
        }
    }

    public String usage() {
        return "Usage:\n" + //
                "\tsquare {side | diagonal | area} <value>";
    }

    @Override
    public Properties toProperties()
    {
        Properties props = new Properties();
        Double side = null;
        Double diagonal = null;
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
                case "diagonal":
                    props.setDiagonals(new Double[]{value});
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
