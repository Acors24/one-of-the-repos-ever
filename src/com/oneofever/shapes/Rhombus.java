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

        props.setSides(new Double[] {side});
        props.setDiagonals(new Double[] {diagonal1, diagonal2});
        props.setArea(area);

        // int i = 0;

        // if (si != null) {
        //     if (si.length != 1) {
        //         throw new IllegalArgumentException("Wrong number of sides.");
        //     }
        //     s = si[0];
        //     if (sl != null) {
        //         if (di != null) {
        //             // sl and di availble
        //             side_long = sl;
        //             side_short = Math.sqrt(di * di - sl * sl);
        //             area = side_short * side_long;
        //             diagonal = di;
        //             i++;
        //         } else if (ar != null) {
        //             // sl and ar availble
        //             side_long = sl;
        //             side_short = ar / sl;
        //             area = ar;
        //             diagonal = Math.sqrt(side_short * side_short + sl * sl);
        //             i++;
        //         } else if (ss != null) {
        //             // ss and sl availble
        //             side_short = ss;
        //             side_long = sl;
        //             diagonal = Math.sqrt(ss * ss + sl * sl);
        //             area = sl * ss;
        //             i++;
        //         }
        //         i++;
        //     } else if (ss != null) {
        //         if (di != null) {
        //             // ss and di availble
        //             side_short = ss;
        //             side_long = Math.sqrt(di * di - ss * ss);
        //             area = side_short * side_long;
        //             diagonal = di;
        //             i++;
        //         } else if (ar != null) {
        //             // ss and ar availble
        //             side_short = ss;
        //             side_long = ar / ss;
        //             diagonal = Math.sqrt(ss * ss + side_long * side_long);
        //             area = ar;
        //             i++;
        //         }
        //         i++;
        //     }

        //     props.setSides(new Double[] {side_short, side_long});
        //     props.setDiagonals(new Double[] {diagonal});
        //     props.setArea(area);
        // } else if (ar != null) {
        //     if (di != null) {
        //         // ar and di availble
        //         area = ar;
        //         diagonal = di;
        //         // ss*ss + sl*sl = di*di
        //         // ss * sl = ar
        //         side_short =
        //                 Math.sqrt(((di * di) + Math.sqrt(di * di * di * di - 4 * ar * ar)) / 2);
        //         side_long = ar / side_short;

        //         if (side_short.isNaN())
        //             throw new IllegalArgumentException("Diagonal is too short.");
        //         if (side_short > side_long) {
        //             Double temp = side_short;
        //             side_short = side_long;
        //             side_long = temp;
        //         }
        //         i++;
        //     }

        //     props.setSides(new Double[] {side_short, side_long});
        //     props.setDiagonals(new Double[] {diagonal});
        //     props.setArea(area);
        //     i++;
        // }
        // if (i < 2) {
        //     throw new IllegalArgumentException("Not enough arguments.");
        // }
        // if (i > 2) {
        //     throw new IllegalArgumentException("Too many arguments.");
        // }

        // if (side_short > side_long) {
        //     throw new IllegalArgumentException("Shorter side is longer than the longer side.");
        // }
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
