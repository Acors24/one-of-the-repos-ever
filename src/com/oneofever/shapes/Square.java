/* (C)2024 - one-of-the-teams-ever */
package com.oneofever.shapes;

import com.oneofever.Pair;
import java.util.ArrayList;
import java.util.Hashtable;

public class Square extends Shape {
    Double side;
    Double diagonal;
    Double area;

    public Square(Hashtable<String, Pair<Integer, ArrayList<Double>>> values)
            throws IllegalArgumentException {
        if (values.containsKey("side")) {
            side = values.get("side").b.get(0);

            if (side < 0) {
                throw new IllegalArgumentException("Side length cannot be negative.");
            }

            diagonal = side * Math.sqrt(2);
            area = side * side;
        } else if (values.containsKey("diagonal")) {
            diagonal = values.get("diagonal").b.get(0);

            if (diagonal < 0) {
                throw new IllegalArgumentException("Diagonal length cannot be negative.");
            }

            side = diagonal / Math.sqrt(2);
            area = side * side;
        } else if (values.containsKey("area")) {
            area = values.get("area").b.get(0);

            if (area < 0) {
                throw new IllegalArgumentException("Area cannot be negative.");
            }

            side = Math.sqrt(area);
            diagonal = side * Math.sqrt(2);
        }
    }

    public Double getSide() {
        return side;
    }

    public Double getDiagonal() {
        return diagonal;
    }

    public Double getArea() {
        return area;
    }
}
