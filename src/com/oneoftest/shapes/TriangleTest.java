/* (C)2024 - one-of-the-teams-ever */
package com.oneoftest.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.oneofever.shapes.Properties;
import com.oneofever.shapes.Triangle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TriangleTest {

    Triangle triangle;

    public TriangleTest() {
        Properties props = new Properties();
        props.setSides(new Double[] {10.0});
        triangle = new Triangle(props);
    }

    @Test
    @DisplayName("Side should be equal to 10")
    void testSide() {
        assertEquals(10.0, triangle.getSide());
    }

    @Test
    @DisplayName("Cannot build a triangle with negative length side")
    void testNonZero() {
        IllegalArgumentException thrown =
                assertThrows(
                        IllegalArgumentException.class,
                        () ->
                                new Triangle(
                                        Properties.create(new Double[] {-1.0}, null, null, null)));
        assertTrue(thrown.getMessage().contains("Side length cannot be negative."));
    }
}
