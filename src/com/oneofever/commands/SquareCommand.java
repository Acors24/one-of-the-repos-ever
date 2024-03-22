package com.oneofever.commands;

import com.oneofever.shapes.Square;
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
    public void run()
    {
        try
        {
            Square square = new Square(toProperties());

            System.out.println("side = " + square.getSide());
            System.out.println("diagonal = " + square.getDiagonal());
            System.out.println("area = " + square.getArea());
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
            return;
        }
    }

    @Override
    public String usage() {
        return "Usage:\n" + //
                "\tsquare {side | diagonal | area} <value>";
    }

    @Override
    public Properties toProperties()
    {
        Properties props = new Properties();
        try
        {
            for (ArgGroup arg : argGroups)
            {
                if (arg.contents.isEmpty())
                    continue;
                Object obj = arg.contents.get(0);
                Double value = null;
                if (obj != null)
                {
                    value = Double.parseDouble((String)obj);
                }
                switch (arg.name)
                {
                    case "side":
                        if(value != null)
                            props.setSides(new Double[]{value});
                        break;
                    case "diagonal":
                        if(value != null)
                            props.setDiagonals(new Double[]{value});
                        break;
                    case "area":
                        if(value != null)
                            props.setArea(value);
                        break;
                    default:
                        break;
                }
            }
        }
        catch (NumberFormatException ex)
        {
            System.out.println("Parser failed: "+ex);
        }
        return props;
    }
}

