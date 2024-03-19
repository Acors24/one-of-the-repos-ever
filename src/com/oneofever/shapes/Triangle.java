package com.oneofever.shapes;

public class Triangle {
  public enum ArgType {
    Side,
    Height,
    Area
  }

  private double side;
  private double height;
  private double area;

  public Triangle(ArgType type, double value) throws IllegalArgumentException {
    if (value < 0) {
      throw new IllegalArgumentException("Value must not be negative.");
    }
    switch (type) {
      case Side:
        side = value;
        height = (side * Math.sqrt(3))/2;
        area = (side * height)/2;
        break;

      case Height:
        height = value;
        side = height*2 / Math.sqrt(3);
        area = (side * height)/2;
        break;

      case Area:
        area = value;
        side = Math.sqrt(area/Math.sqrt(3))*2;
        height = (side * Math.sqrt(3))/2;
        break;
    }
  }

  public double getSide() {
    return side;
  }

  public double getHeight() {
    return height;
  }

  public double getArea() {
    return area;
  }
}
