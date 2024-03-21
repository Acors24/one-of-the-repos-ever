/* (C)2024 - one-of-the-teams-ever */
package com.oneofever.shapes;

public abstract class Shape {
    Properties props;

    public Shape() {
        this.props = new Properties();
    }

    public Shape(Double[] sides, Double[] heights, Double[] diagonals, Double area) {
        this.props = Properties.create(sides, heights, diagonals, area);
    }

    Double[] getSides() {
        return props.getSides();
    }

    Double[] getHeights() {
        return props.getHeights();
    }

    Double[] getDiagonals() {
        return props.getDiagonals();
    }

    Double getArea() {
        return props.getArea();
    }
}
