/* (C)2024 - one-of-the-teams-ever */
package com.oneofever.commands;

import com.oneofever.Message;
import com.oneofever.shapes.Properties;
import com.oneofever.shapes.Square;

public class SquareCommand extends AbstractCommand {

    public SquareCommand() {
        argGroups.add(new ArgGroup(Message.Info.SIDE, Message.Info.DOUBLE, 1));
        argGroups.add(new ArgGroup(Message.Info.DIAGONAL, Message.Info.DOUBLE, 1));
        argGroups.add(new ArgGroup(Message.Info.AREA, Message.Info.DOUBLE, 1));
        groupNumber = 1;
    }

    @Override
    public String name() {
        return Message.Info.SQUARE;
    }

    @Override
    public String description() {
        return "display square info";
    }

    @Override
    public void run() {
        try {
            Square square = new Square(toProperties());

            System.out.println("%s = %d".formatted(Message.Info.SIDE, square.getSide()));
            System.out.println("%s = %d".formatted(Message.Info.DIAGONAL, square.getDiagonal()));
            System.out.println("%s = %d".formatted(Message.Info.AREA, square.getArea()));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return;
        }
    }

    @Override
    public String usage() {
        return "Usage:\n"
                + //
                "\tsquare {side | diagonal | area} <value>";
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
                    case Message.Info.SIDE:
                        if (value != null) props.setSides(new Double[] {value});
                        break;
                    case Message.Info.DIAGONAL:
                        if (value != null) props.setDiagonals(new Double[] {value});
                        break;
                    case Message.Info.AREA:
                        if (value != null) props.setArea(value);
                        break;
                    default:
                        break;
                }
            }
        } catch (NumberFormatException ex) {
            System.out.println(Message.Error.PARSER_FAILED + ex);
        }
        return props;
    }
}
