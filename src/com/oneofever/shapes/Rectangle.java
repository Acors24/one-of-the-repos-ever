package com.oneofever.shapes;

import com.oneofever.Pair;
import java.util.ArrayList;
import java.util.Hashtable;

public class Rectangle extends Shape {
    Double side1;
    Double side2;
    Double diagonal;
    Double area;

    public Rectangle(Hashtable<String, Pair<Integer, ArrayList<Double>>> values)
            throws IllegalArgumentException {
        if (values.containsKey("side")) {
            side1 = values.get("side").b.get(0);
        }

        if (values.containsKey("sides")) {
            side1 = values.get("sides").b.get(0);
            side2 = values.get("sides").b.get(1);
        }

        if ((side1 != null && side1 <= 0) || (side2 != null && side2 <= 0)) {
            throw new IllegalArgumentException("Side length cannot be non-positive.");
        }

        if (values.containsKey("diagonal")) {
            diagonal = values.get("diagonal").b.get(0);
        }

        if (diagonal != null && diagonal < 0) {
            throw new IllegalArgumentException("Diagonal length cannot be negative.");
        }

        if (diagonal != null && side1 != null && diagonal < side1) {
            throw new IllegalArgumentException("Diagonal cannot be shorter than side.");
        }

        if (values.containsKey("area")) {
            area = values.get("area").b.get(0);
        }

        if (area != null && area < 0) {
            throw new IllegalArgumentException("Area cannot be negative.");
        }

        if (side1 != null && side2 != null) {
            diagonal = Math.sqrt(side1 * side1 + side2 * side2);
            area = side1 * side2;
        } else if (side1 != null && diagonal != null) {
            side2 = Math.sqrt(diagonal * diagonal - side1 * side1);
            area = side1 * side2;
        } else if (side1 != null) {
            side2 = area / side1;
            diagonal = Math.sqrt(side1 * side1 + side2 * side2);
        } else {
            side1 =
                    Math.sqrt(
                            (diagonal * diagonal
                                            + Math.sqrt(Math.pow(diagonal, 4) - 4 * area * area))
                                    / 2.0);
            side2 = area / side1;

            if (side1.isNaN() || side2.isNaN()) {
                throw new IllegalArgumentException("Diagonal length is too short.");
            }
        }
    }

    public Double getSide1() {
        return side1;
    }

    public Double getSide2() {
        return side2;
    }

    public Double getDiagonal() {
        return diagonal;
    }

    public Double getArea() {
        return area;
    }
}
