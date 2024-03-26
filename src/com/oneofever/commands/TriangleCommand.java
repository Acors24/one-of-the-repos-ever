/* (C)2024 - one-of-the-teams-ever */
package com.oneofever.commands;

import com.oneofever.Message;
import com.oneofever.shapes.Properties;
import com.oneofever.shapes.Triangle;

public class TriangleCommand extends AbstractCommand {

    public TriangleCommand() {
        argGroups.add(new ArgGroup(Message.Info.SIDE, Message.Info.DOUBLE, 1));
        argGroups.add(new ArgGroup(Message.Info.HEIGHT, Message.Info.DOUBLE, 1));
        argGroups.add(new ArgGroup(Message.Info.AREA, Message.Info.DOUBLE, 1));
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
    public void run() {
        try {
            Triangle triangle = new Triangle(toProperties());

            System.out.println("%s = %d".formatted(Message.Info.SIDE, triangle.getSide()));
            System.out.println("%s = %d".formatted(Message.Info.HEIGHT, triangle.getHeight()));
            System.out.println("%s = %d".formatted(Message.Info.AREA, triangle.getArea()));
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
                        props.setSides(new Double[] {value});
                        break;
                    case Message.Info.HEIGHT:
                        props.setHeights(new Double[] {value});
                        break;
                    case Message.Info.AREA:
                        props.setArea(value);
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
