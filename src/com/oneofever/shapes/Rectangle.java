/* (C)2024 - one-of-the-teams-ever */
package com.oneofever.shapes;

import com.oneofever.Message;

public class Rectangle extends Shape {
    public Rectangle(Properties props) throws IllegalArgumentException {
        this.props = props;

        Double side_short = null;
        Double side_long = null;
        Double diagonal = null;
        Double area = null;

        Double ss = null;
        Double sl = null;
        Double di = null;
        Double ar = props.getArea();

        Double[] si = props.getSides();
        Double[] diag = props.getDiagonals();
        if (diag != null) di = diag[0];

        int i = 0;

        if (si != null) {
            if (si.length != 2) {
                throw new IllegalArgumentException(Message.Error.WRONG_NUMBER_OF_SIDES);
            }
            ss = si[0];
            sl = si[1];
            if (sl != null) {
                if (di != null) {
                    // sl and di availble
                    side_long = sl;
                    side_short = Math.sqrt(di * di - sl * sl);
                    area = side_short * side_long;
                    diagonal = di;
                    i++;
                } else if (ar != null) {
                    // sl and ar availble
                    side_long = sl;
                    side_short = ar / sl;
                    area = ar;
                    diagonal = Math.sqrt(side_short * side_short + sl * sl);
                    i++;
                } else if (ss != null) {
                    // ss and sl availble
                    side_short = ss;
                    side_long = sl;
                    diagonal = Math.sqrt(ss * ss + sl * sl);
                    area = sl * ss;
                    i++;
                }
                i++;
            } else if (ss != null) {
                if (di != null) {
                    // ss and di availble
                    side_short = ss;
                    side_long = Math.sqrt(di * di - ss * ss);
                    area = side_short * side_long;
                    diagonal = di;
                    i++;
                } else if (ar != null) {
                    // ss and ar availble
                    side_short = ss;
                    side_long = ar / ss;
                    diagonal = Math.sqrt(ss * ss + side_long * side_long);
                    area = ar;
                    i++;
                }
                i++;
            }

            props.setSides(new Double[] {side_short, side_long});
            props.setDiagonals(new Double[] {diagonal});
            props.setArea(area);
        } else if (ar != null) {
            if (di != null) {
                // ar and di availble
                area = ar;
                diagonal = di;
                // ss*ss + sl*sl = di*di
                // ss * sl = ar
                side_short =
                        Math.sqrt(((di * di) + Math.sqrt(di * di * di * di - 4 * ar * ar)) / 2);
                side_long = ar / side_short;

                if (side_short.isNaN())
                    throw new IllegalArgumentException(Message.Error.DIAGONAL_TOO_SHORT);
                if (side_short > side_long) {
                    Double temp = side_short;
                    side_short = side_long;
                    side_long = temp;
                }
                i++;
            }

            props.setSides(new Double[] {side_short, side_long});
            props.setDiagonals(new Double[] {diagonal});
            props.setArea(area);
            i++;
        }
        if (i < 2) {
            throw new IllegalArgumentException(Message.Error.TOO_FEW_ARGUMENTS);
        }
        if (i > 2) {
            throw new IllegalArgumentException(Message.Error.TOO_MANY_ARGUMENTS);
        }

        if (side_short > side_long) {
            throw new IllegalArgumentException(Message.Error.SHORTER_IS_LONGER);
        }
    }

    public double getSideS() {
        return props.getSides()[0];
    }

    public double getSideL() {
        return props.getSides()[1];
    }

    public double getDiagonal() {
        return props.getDiagonals()[0];
    }

    public Double getArea() {
        return props.getArea();
    }
}
