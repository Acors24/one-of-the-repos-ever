/* (C)2024 - one-of-the-teams-ever */
package com.oneofever.shapes;

import com.oneofever.Pair;
import java.util.ArrayList;
import java.util.Hashtable;

public class IsoscelesTriangle extends Shape {
    Double side;
    Double height;
    Double area;
    Double base;

    public IsoscelesTriangle(Hashtable<String, Pair<Integer, ArrayList<Double>>> values)
            throws IllegalArgumentException {
        if (values.containsKey("side")) {
            side = values.get("side").b.get(0);

            if (side < 0) {
                throw new IllegalArgumentException("Side length cannot be negative.");
            }
        }
        if (values.containsKey("height")) {
            height = values.get("height").b.get(0);

            if (height < 0) {
                throw new IllegalArgumentException("Height length cannot be negative.");
            }
        }
        if (values.containsKey("area")) {
            area = values.get("area").b.get(0);

            if (area < 0) {
                throw new IllegalArgumentException("Area cannot be negative.");
            }
        }
        if (values.containsKey("base")) {
            base = values.get("base").b.get(0);

            if (area < 0) {
                throw new IllegalArgumentException("stalin nie pisał o ujemnej bazie.");
            }
        }

        if (base != null) {
            if (height != null) {
                area = (base * height) / 2.0;
                // from pithagoras theorem
                side = Math.sqrt(height * height + ((base * base) / 4.0));
            } else if (side != null) {
                // from pithagoras theorem
                height = Math.sqrt(side * side - ((base * base) / 4.0));
                area = (base * height) / 2.0;
            } else if (area != null) {
                // from area formula
                height = (2.0 * area) / base;
                // from pithagoras theorem
                side = Math.sqrt(height * height + ((base * base) / 4.0));
            }
        } else if (height != null) {
            if (side != null) {
                base = 2.0 * Math.sqrt(side * side - height * height);
                area = area = (base * height) / 2.0;
            } else if (area != null) {
                base = (2.0 * area) / height;
            }
        } else if (side != null && area != null) {
            height =
                    Math.sqrt((side * side + Math.sqrt(Math.pow(side, 4) - 4 * area * area)) / 2.0);
            base = 2.0 * Math.sqrt(side * side - height * height);
        } else {
            throw new IllegalArgumentException("Parsing died. ;-;");
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

    public Double getBase() {
        return base;
    }
}
