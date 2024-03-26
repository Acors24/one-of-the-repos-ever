/* (C)2024 - one-of-the-teams-ever */
package com.oneofever.shapes;

import com.oneofever.Message;

public class Square extends Shape {
    public Square(Properties props) throws IllegalArgumentException {
        this.props = props;

        Double side = null;
        Double diagonal = null;
        Double area = null;

        Double[] si = props.getSides();
        Double[] di = props.getDiagonals();
        Double ar = props.getArea();

        int i = 0;

        if (si != null) {
            if (si.length != 1) {
                throw new IllegalArgumentException(Message.Error.WRONG_NUMBER_OF_SIDES);
            }
            side = si[0];
            diagonal = side * Math.sqrt(2);
            area = side * side;

            props.setDiagonals(new Double[] {diagonal});
            props.setArea(area);
            i++;
        }
        if (di != null) {
            if (di.length != 1) {
                throw new IllegalArgumentException(Message.Error.WRONG_NUMBER_OF_SIDES);
            }
            diagonal = di[0];
            side = diagonal / Math.sqrt(2);
            area = side * side;

            props.setSides(new Double[] {side});
            props.setArea(area);
            i++;
        }
        if (ar != null) {
            area = ar;
            side = Math.sqrt(area);
            diagonal = side * Math.sqrt(2);

            props.setSides(new Double[] {side});
            props.setDiagonals(new Double[] {diagonal});
            i++;
        }
        if (i == 0) {
            throw new IllegalArgumentException(Message.Error.TOO_FEW_ARGUMENTS);
        }
        if (i > 1) {
            throw new IllegalArgumentException(Message.Error.TOO_MANY_ARGUMENTS);
        }
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
