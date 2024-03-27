package com.oneofever.shapes;

import com.oneofever.Pair;
import java.util.ArrayList;
import java.util.Hashtable;

public class Rhombus extends Shape {
    Double side;
    Double diagonal1;
    Double diagonal2;
    Double area;

    public Rhombus(Hashtable<String, Pair<Integer, ArrayList<Double>>> values)
            throws IllegalArgumentException {
        if (values.containsKey("side")) {
            side = values.get("side").b.get(0);
        }

        if (side != null && side < 0) {
            throw new IllegalArgumentException("Side length cannot be negative.");
        }

        if (values.containsKey("diagonal")) {
            diagonal1 = values.get("diagonal").b.get(0);
        }

        if (values.containsKey("diagonals")) {
            diagonal1 = values.get("diagonals").b.get(0);
            diagonal2 = values.get("diagonals").b.get(1);
        }

        if ((diagonal1 != null && diagonal1 < 0) || (diagonal2 != null && diagonal2 < 0)) {
            throw new IllegalArgumentException("Diagonal length cannot be negative.");
        }

        if (values.containsKey("area")) {
            area = values.get("area").b.get(0);
        }

        if (area != null && area < 0) {
            throw new IllegalArgumentException("Area cannot be negative.");
        }

        if (diagonal1 != null && diagonal2 != null) {
            area = diagonal1 * diagonal2 / 2.0;
            side = Math.sqrt(Math.pow(diagonal1 / 2.0, 2) + Math.pow(diagonal2 / 2.0, 2));
        } else if (diagonal1 != null && side != null) {
            diagonal2 = Math.sqrt(side * side - Math.pow(diagonal1 / 2.0, 2)) * 2;
            area = diagonal1 * diagonal2 / 2.0;
        } else if (diagonal1 != null && area != null) {
            diagonal2 = area * 2 / diagonal1;
            side = Math.sqrt(Math.pow(diagonal1 / 2.0, 2) + Math.pow(diagonal2 / 2.0, 2));
        } else if (diagonal2 != null && side != null) {
            diagonal1 = Math.sqrt(side * side - Math.pow(diagonal2 / 2.0, 2)) * 2;
            area = diagonal1 * diagonal2 / 2.0;
        } else if (diagonal2 != null && area != null) {
            diagonal1 = area * 2 / diagonal2;
            side = Math.sqrt(Math.pow(diagonal1 / 2.0, 2) + Math.pow(diagonal2 / 2.0, 2));
        } else if (side != null && area != null) {
            diagonal1 =
                    Math.sqrt(
                            2 * Math.pow(side, 2)
                                    + 2 * Math.sqrt(Math.pow(side, 4) - Math.pow(area, 2)));
            diagonal2 = area * 2 / diagonal1;
        } else {
            throw new IllegalArgumentException("Insufficient data.");
        }

        if (diagonal1.isNaN() || diagonal2.isNaN()) {
            throw new IllegalArgumentException("The area is too large or the side is too small");
        }
    }

    public Double getSide() {
        return side;
    }

    public Double getDiagonal1() {
        return diagonal1;
    }

    public Double getDiagonal2() {
        return diagonal2;
    }

    public Double getArea() {
        return area;
    }
}
