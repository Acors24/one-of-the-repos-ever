/* (C)2024 - one-of-the-teams-ever */
package com.oneoftest.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.oneofever.shapes.Triangle;
import com.oneofever.shapes.Triangle.ArgType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class TriangleTest {

    Triangle triangle;
    double i = 0.0;

    @BeforeEach
    void setUp() {
        triangle = new com.oneofever.shapes.Triangle(ArgType.Side, 10.0);
        i -= 1.0;
    }

    @Test
    @DisplayName("Side should be equal to 10")
    void testSide() {
        assertEquals(10.0, triangle.getSide());
    }

    @RepeatedTest(5)
    @DisplayName("Cannot build a triangle with negative length side")
    void testNonZero() {
        IllegalArgumentException thrown =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> new com.oneofever.shapes.Triangle(ArgType.Side, i));
        assertTrue(thrown.getMessage().contains("Side length cannot be negative."));
    }
}
