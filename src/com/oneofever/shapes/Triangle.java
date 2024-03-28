/* (C)2024 - one-of-the-teams-ever */
package com.oneofever.shapes;

import com.oneofever.Pair;
import java.util.ArrayList;
import java.util.Hashtable;

public class Triangle extends Shape {
    Double side;
    Double height;
    Double area;

    public Triangle(Hashtable<String, Pair<Integer, ArrayList<Double>>> values)
            throws IllegalArgumentException {
        if (values.containsKey("side")) {
            side = values.get("side").b.get(0);

            if (side < 0) {
                throw new IllegalArgumentException("Side length cannot be negative.");
            }

            height = (side * Math.sqrt(3)) / 2;
            area = side * height / 2.0;
        } else if (values.containsKey("height")) {
            height = values.get("height").b.get(0);

            if (height < 0) {
                throw new IllegalArgumentException("Height length cannot be negative.");
            }

            side = height * 2 / Math.sqrt(3);
            area = side * height / 2.0;
        } else if (values.containsKey("area")) {
            area = values.get("area").b.get(0);

            if (area < 0) {
                throw new IllegalArgumentException("Area cannot be negative.");
            }

            side = Math.sqrt(4 * area / Math.sqrt(3));
            height = (side * Math.sqrt(3)) / 2;
        }
    }

    public Double getSide() {
        return side;
    }

    public Double getHeight() {
        return height;
    }

    public Double getArea() {
        return area;
    }
}
