/* (C)2024 - one-of-the-teams-ever */
package com.oneofever.commands;

public class Exit extends AbstractCommand {
    @Override
    public String name() {
        return "exit";
    }

    @Override
    public String description() {
        return "exit the program";
    }

    @Override
    public void run() {
        System.exit(0);
    }
}
