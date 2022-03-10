package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for Point class
 *
 */
public class PointTest {

    final Point p1 = new Point(1.0, 2.0, 3.0);
    final Point p2 = new Point(1.0, 5.0, 7.0);
    final Vector v1 = new Vector(4.0, 5.0, 6.0);
    final Vector v2 = new Vector(-1.0, -2.0, -3.0);

    /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test the add operation with point and vector
        assertEquals(p1.add(v1), new Point(5.0, 7.0, 9.0), "Point + Vector does not work correctly");

        // =============== Boundary Values Tests ==================
        // TC11: Test the add operation with Point and contrasting vector of the same coordinate
        assertEquals(p1.add(v2),  new Point(0.0, 0.0, 0.0),"point + (-)Vector does not give the zero point");
    }

    /**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}.
     */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test the subtract operation with point and point
        assertEquals(p1.subtract(p2), new Vector(0.0, -3.0, -4.0), "Point - Point does not work correctly");

        // =============== Boundary Values Tests ==================
        // TC11: Test the subtract operation with Point and same point of the same coordinate
        assertThrows(IllegalArgumentException.class, () ->p1.subtract(p1), "Vector + (-)Vector give vector Zero and not throw  exception");
    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
     */
    @Test
    void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test the distanceSquare with point
        assertEquals(p1.distanceSquared(p2), 25, 0.00001, "distanceSquare of 2 Points does not work correctly");

        // =============== Boundary Values Tests ==================
        // TC11: Test the distanceSquare of point with himself
        assertEquals(p1.distanceSquared(p1), 0, 0.00001, "distanceSquare of Point with himself, does not work correctly");
    }

    /**
     * Test method for {@link primitives.Point#distance(primitives.Point)}.
     */
    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test the distance with point
        assertEquals(p1.distance(p2), 5, 0.00001, "distance of 2 Points does not work correctly");

        // =============== Boundary Values Tests ==================
        // TC11: Test the distance of point with himself
        assertEquals(p1.distance(p1), 0, 0.00001, "distance of Point with himself, does not work correctly");
    }
}