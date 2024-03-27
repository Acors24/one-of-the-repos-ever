/* (C)2024 - one-of-the-teams-ever */
package com.oneofever.shapes;

public class Rhombus extends Shape {
    public Rhombus(Properties props) throws IllegalArgumentException {
        this.props = props;

        Double side = null;
        {
            Double[] si = props.getSides();
            if (si != null) {
                side = si[0];
            }
        }

        Double diagonal1 = null;
        Double diagonal2 = null;
        {
            Double[] diag = props.getDiagonals();
            if (diag != null) {
                diagonal1 = diag[0];
                diagonal2 = diag[1];
            }
        }

        Double area = props.getArea();

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
            throw new IllegalArgumentException("This shape cannot exist.");
        }

        props.setSides(new Double[] {side});
        props.setDiagonals(new Double[] {diagonal1, diagonal2});
        props.setArea(area);
    }

    public double getSide() {
        return props.getSides()[0];
    }

    public double getDiagonal1() {
        return props.getDiagonals()[0];
    }

    public double getDiagonal2() {
        return props.getDiagonals()[1];
    }

    public Double getArea() {
        return props.getArea();
    }
}
