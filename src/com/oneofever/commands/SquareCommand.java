package com.oneofever.commands;

import com.oneofever.shapes.Square.ArgType;
import java.util.Optional;

public class SquareCommand extends ICommand {

    public SquareCommand()
    {
        commandInfo.add(new CommandInfo("square", new String[]{"squareSide","squareDiagonal","squareArea"}, 1));
        commandInfo.add(new CommandInfo("squareSide", new String[]{"double"}, 1));
        commandInfo.add(new CommandInfo("squareDiagonal", new String[]{"double"}, 1));
        commandInfo.add(new CommandInfo("squareArea", new String[]{"double"}, 1));
        commandInfo.add(new CommandInfo("double", null, null));
    }

    @Override
    public String[] next(String current)
    {
        Optional<CommandInfo> match = commandInfo.stream().filter(σ -> σ.getActiveModifier().equals(current)).findFirst();
        if (match.isPresent()) {
            return match.get().getValidChanges();
        } else {
            return null;
        }
    }

    @Override
    public Integer tokens(String current)
    {
        Optional<CommandInfo> match = commandInfo.stream().filter(σ -> σ.getActiveModifier().equals(current)).findFirst();
        if (match.isPresent()) {
            return match.get().getCounter();
        } else {
            return null;
        }
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
}
