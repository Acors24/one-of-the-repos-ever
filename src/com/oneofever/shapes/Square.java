package com.oneofever.shapes;

public class Square extends Shape {
    public enum ArgType {
        Side,
        Diagonal,
        Area
    }

    public Square(ArgType type, double value) throws IllegalArgumentException
    {
        super();

        Double side = 0.0;
        Double diagonal = 0.0;
        Double area = 0.0;

        switch (type)
        {
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
        props.setSides(new Double[]{side});
        props.setDiagonals(new Double[]{diagonal});
        props.setArea(area);
    }

    public double getSide() {
        return props.getSides()[0];
    }

    public double getDiagonal() {
        return props.getDiagonals()[0];
    }

    public Double getArea() {
        return props.getArea();
    }
}
