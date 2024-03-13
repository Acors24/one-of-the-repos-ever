package com.oneofever.commands;

import java.util.LinkedList;

public class Help implements ICommand {

  LinkedList<ICommand> commands;

  @SuppressWarnings("unchecked")
  public void setCommands(LinkedList<ICommand> commands) {
    this.commands = (LinkedList<ICommand>) commands.clone();
  }

  @Override
  public String name() {
    return "help";
  }

  @Override
  public String description() {
    return "display help";
  }

  @Override
  public void run(String[] tokens) {
    for (ICommand command : commands) {
      System.err.println(command.name() + " - " + command.description());
    }
  }
  
}
