package com.oneofever.shapes;

public class Triangle extends Shape {
    public enum ArgType {
        Side,
        Height,
        Area
    }

    public Triangle(ArgType type, double value) throws IllegalArgumentException
    {
        super();

        Double side = 0.0;
        Double height = 0.0;
        Double area = 0.0;

        switch (type)
        {
            case Side:
                side = value;
                height = (side * Math.sqrt(3)) / 2;
                area = (side * height) / 2;
                break;

            case Height:
                height = value;
                side = height * 2 / Math.sqrt(3);
                area = (side * height) / 2;
                break;

            case Area:
                area = value;
                side = Math.sqrt(area / Math.sqrt(3)) * 2;
                height = (side * Math.sqrt(3)) / 2;
                break;
        }
        props.setSides(new Double[]{side});
        props.setHeights(new Double[]{height});
        props.setArea(area);
    }

    public double getSide() {
        return props.getSides()[0];
    }

    public double getHeight() {
        return props.getHeights()[0];
    }

    public Double getArea() {
        return props.getArea();
    }
}
