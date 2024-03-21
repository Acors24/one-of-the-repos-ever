/* (C)2024 - one-of-the-teams-ever */
package com.oneofever.shapes;

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
                if (side < 0) throw new IllegalArgumentException("Side length cannot be negative.");
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
                if (height < 0)
                    throw new IllegalArgumentException("Height length cannot be negative.");
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
                if (diagonal < 0)
                    throw new IllegalArgumentException("Diagonal length cannot be negative.");
            }
        }
        this.diagonals = diagonals;
    }

    public Double[] getDiagonals() {
        return diagonals;
    }

    public void setArea(Double area) {
        if (area != null && area < 0)
            throw new IllegalArgumentException("Area cannot be negative.");
        this.area = area;
    }

    public Double getArea() {
        return area;
    }
}
