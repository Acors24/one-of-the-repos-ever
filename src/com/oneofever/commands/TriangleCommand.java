package com.oneofever.commands;

import com.oneofever.shapes.Triangle;
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
    public void run()
    {
        try
        {
            Triangle triangle = new Triangle(toProperties());

            System.out.println("side = " + triangle.getSide());
            System.out.println("height = " + triangle.getHeight());
            System.out.println("area = " + triangle.getArea());
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
                "\ttriangle {side | height | area} <value>";
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
        }
        catch (NumberFormatException ex)
        {
            System.out.println("Parser failed: "+ex);
        }
        return props;
    }
}
