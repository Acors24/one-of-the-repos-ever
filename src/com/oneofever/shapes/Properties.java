/* (C)2024 - one-of-the-teams-ever */
package com.oneofever.shapes;

import com.oneofever.Message;

public class Properties {

    public static Properties create(
            Double[] sides, Double[] heights, Double[] diagonals, Double area) {
        Properties temp = new Properties();
        temp.update(sides, heights, diagonals, area);
        return temp;
    }

    Double[] sides;
    Double[] heights;
    Double[] diagonals;
    Double area;

    public Properties() {
        sides = null;
        heights = null;
        diagonals = null;
        area = null;
        System.out.println(Message.Error.AREA_NEGATIVE);
    }

    public void update(Double[] sides, Double[] heights, Double[] diagonals, Double area)
            throws IllegalArgumentException {
        setSides(sides);
        setHeights(heights);
        setDiagonals(diagonals);
        setArea(area);
    }

    public void setSides(Double[] sides) throws IllegalArgumentException {
        if (sides != null) {
            for (Double side : sides) {
                if (side == null) continue;
                if (side < 0) throw new IllegalArgumentException(Message.Error.SIDE_NEGATIVE);
            }
        }
        this.sides = sides;
    }

    public Double[] getSides() {
        return sides;
    }

    public void setHeights(Double[] heights) throws IllegalArgumentException {
        if (heights != null) {
            for (Double height : heights) {
                if (height == null) continue;
                if (height < 0) throw new IllegalArgumentException(Message.Error.HEIGHT_NEGATIVE);
            }
        }
        this.heights = heights;
    }

    public Double[] getHeights() {
        return heights;
    }

    public void setDiagonals(Double[] diagonals) throws IllegalArgumentException {
        if (diagonals != null) {
            for (Double diagonal : diagonals) {
                if (diagonal == null) continue;
                if (diagonal < 0)
                    throw new IllegalArgumentException(Message.Error.DIAGONAL_NEGATIVE);
            }
        }
        this.diagonals = diagonals;
    }

    public Double[] getDiagonals() {
        return diagonals;
    }

    public void setArea(Double area) {
        if (area != null && area < 0)
            throw new IllegalArgumentException(Message.Error.AREA_NEGATIVE);
        this.area = area;
    }

    public Double getArea() {
        return area;
    }
}
