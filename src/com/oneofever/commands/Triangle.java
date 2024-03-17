package com.oneofever.commands;

import com.oneofever.shapes.Triangle.ArgType;

public class Triangle implements ICommand {

  @Override
  public String name() {
    return "triangle";
  }

  @Override
  public String description() {
    return "display triangle info";
  }

  @Override
  public void run(String[] tokens) {
    if (tokens.length < 3) {
      System.err.println("Not enough arguments.\n" + usage());
      return;
    }

    ArgType argType = switch (tokens[1]) {
      case "side" -> ArgType.Side;
      case "height" -> ArgType.Height;
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

    com.oneofever.shapes.Triangle triangle = new com.oneofever.shapes.Triangle(argType, value);

    System.out.println("side = " + triangle.getSide());
    System.out.println("height = " + triangle.getHeight());
    System.out.println("area = " + triangle.getArea());
  }

  public String usage() {
    return "Usage:\n" + //
            "\ttriangle{side | height | area} <value>";
  }
}
