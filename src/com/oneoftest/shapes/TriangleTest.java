package com.oneofever.shapes;

import com.oneofever.shapes.Triangle;
import com.oneofever.shapes.Triangle.ArgType;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;


class TriangleTest {

    Triangle triangle;

    public TriangleTest()
    {
        triangle = new com.oneofever.shapes.Triangle(ArgType.Side, 10.0);
    }

    @Test
    @DisplayName("Side should be equal to 10")
    void testSide() {
        assertEquals(10.0, triangle.getSide());
    }

    @Test
    @DisplayName("Cannot build a triangle with negative length side")
    void testNonZero() {
        IllegalArgumentException thrown = assertThrows(
           IllegalArgumentException.class,
           () -> new com.oneofever.shapes.Triangle(ArgType.Side, -1));
        assertTrue(thrown.getMessage().contains("Side length cannot be negative."));
    }
}
