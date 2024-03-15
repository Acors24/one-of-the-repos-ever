package com.oneofever.shapes;

public class Square {
  public enum ArgType {
    Side,
    Diagonal,
    Area
  }

  private double side;
  private double diagonal;
  private double area;

  public Square(ArgType type, double value) {
    switch (type) {
      case Side:
        side = value;
        diagonal = side * Math.sqrt(2);
        area = side * side;
        break;

      case Diagonal:
        diagonal = value;
        side = diagonal / Math.sqrt(2);
        area = side * side;
        break;
    
      case Area:
        area = value;
        side = Math.sqrt(area);
        diagonal = side * Math.sqrt(2);
        break;
    }
  }

  public double getSide() {
    return side;
  }

  public double getDiagonal() {
    return diagonal;
  }

  public double getArea() {
    return area;
  }
}