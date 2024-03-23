/* (C)2024 - one-of-the-teams-ever */
package com.oneoftest.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.oneofever.Pair;
import com.oneofever.shapes.Triangle;
import java.util.ArrayList;
import java.util.Hashtable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TriangleTest {

    @Test
    @DisplayName("Side should be equal to 10")
    void testSide() {
        Triangle triangle =
                new com.oneofever.shapes.Triangle(
                        new Hashtable<>() {
                            {
                                put(
                                        "side",
                                        new Pair<>(
                                                1,
                                                new ArrayList<>() {
                                                    {
                                                        add(10.0);
                                                    }
                                                }));
                            }
                        });
        assertEquals(10.0, (double) triangle.getSide());
    }

    @Test
    @DisplayName("Height should be equal to 20")
    void testHeight() {
        Triangle triangle =
                new com.oneofever.shapes.Triangle(
                        new Hashtable<>() {
                            {
                                put(
                                        "height",
                                        new Pair<>(
                                                1,
                                                new ArrayList<>() {
                                                    {
                                                        add(20.0);
                                                    }
                                                }));
                            }
                        });
        assertEquals(20.0, (double) triangle.getHeight());
    }

    @Test
    @DisplayName("Area should be equal to 5")
    void testArea() {
        Triangle triangle =
                new com.oneofever.shapes.Triangle(
                        new Hashtable<>() {
                            {
                                put(
                                        "area",
                                        new Pair<>(
                                                1,
                                                new ArrayList<>() {
                                                    {
                                                        add(5.0);
                                                    }
                                                }));
                            }
                        });
        assertEquals(5.0, (double) triangle.getArea());
    }

    @Test
    @DisplayName("Cannot build a triangle with negative length side")
    void testNegative() {
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        new com.oneofever.shapes.Triangle(
                                new Hashtable<>() {
                                    {
                                        put(
                                                "side",
                                                new Pair<>(
                                                        1,
                                                        new ArrayList<>() {
                                                            {
                                                                add(-1.0);
                                                            }
                                                        }));
                                    }
                                }));
    }
}
