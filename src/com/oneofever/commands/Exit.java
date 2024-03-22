package com.oneofever.commands;

public class Exit extends ICommand {
/*
    @Override
    public String[] next(String current)
    {
        return null;
    }

    @Override
    public Integer tokens(String current)
    {
        return 0;
    }
*/
    @Override
    public String name() {
        return "exit";
    }

    @Override
    public String description() {
        return "exit the program";
    }

    @Override
    public void run(String[] tokens) {
        System.exit(0);
    }
}
