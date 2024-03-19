package com.oneofever.commands;

public class Version implements ICommand {

  @Override
  public String name() {
    return "version";
  }

  @Override
  public String description() {
    return "display program version";
  }

  @Override
  public void run(String[] tokens) {
    System.out.println("19.03.24 - terminal test");
  }
  
}
