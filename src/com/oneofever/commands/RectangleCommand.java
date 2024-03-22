package com.oneofever.commands;

// import com.oneofever.shapes.Triangle.ArgType;

public class RectangleCommand extends AbstractCommand {

    public RectangleCommand()
    {
        argGroups.add(new ArgGroup("side", "Double", 1));
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
    public void run(String[] tokens){
        System.out.println("placeholder rectangle run");
    }
}
