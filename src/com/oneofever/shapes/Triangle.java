package com.oneofever.shapes;

public class Triangle extends Shape {
    public Triangle(Properties props) throws IllegalArgumentException
    {
        this.props = props;

        Double side = null;
        Double height = null;
        Double area = null;

        Double[] si = props.getSides();
        Double[] he = props.getHeights();
        Double ar = props.getArea();

        int i = 0;

        if (si != null)
        {
            if (si.length != 1)
            {
                throw new IllegalArgumentException("Wrong number of sides.");
            }
            side = si[0];
            height = (side * Math.sqrt(3)) / 2;
            area = (side * height) / 2;

            props.setHeights(new Double[]{height});
            props.setArea(area);
            i++;
        }
        if (he != null)
        {
            if (he.length != 1)
            {
                throw new IllegalArgumentException("Wrong number of heights.");
            }
            height = he[0];
            side = height * 2 / Math.sqrt(3);
            area = (side * height) / 2;

            props.setSides(new Double[]{side});
            props.setArea(area);
            i++;
        }
        if (ar != null)
        {
            area = ar;
            side = Math.sqrt(area / Math.sqrt(3)) * 2;
            height = (side * Math.sqrt(3)) / 2;

            props.setSides(new Double[]{side});
            props.setHeights(new Double[]{height});
            i++;
        }
        if (i == 0)
        {
            throw new IllegalArgumentException("Not enough arguments.");
        }
        if (i > 1)
        {
            throw new IllegalArgumentException("Too many arguments.");
        }
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
