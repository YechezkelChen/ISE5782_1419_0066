package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Point class
 *
 */
public class PointTest {

    /**
     * Test method for {@link Point add(Vector)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test the add operation with point and vector
        Point p1 = new Point(1.0, 2.0, 3.0);
        assertEquals(p1.add(new Vector(-1.0, -2.0, -3.0)), new Point(0.0, 0.0, 0.0), "Point + Vector does not work correctly");

        // =============== Boundary Values Tests ==================
        // TC11: Test the add operation with Point and contrasting vector of the same coordinate
        assertThrows(IllegalArgumentException.class, () ->p1.add(new Vector(-1.0, -2.0, -3.0)),
                "point + (-)Vector give vector Zero and not throw  exception");
    }

    /**
     * Test method for {@link Vector subtract(Point)}.
     */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test the subtract operation with point and point
        Point p1 = new Point(1.0, 2.0, 3.0);
        assertEquals(p1.subtract(new Point(2.0, 3.0, 4.0)), new Vector(1.0, 1.0, 1.0),
                "Point - Point does not work correctly");

        // =============== Boundary Values Tests ==================
        // TC11: Test the subtract operation with Point and same vector of the same coordinate
        assertThrows(IllegalArgumentException.class, () ->p1.subtract(new Vector(1.0, 2.0, 3.0)),
                "Vector + (-)Vector give vector Zero and not throw  exception");
    }

    /**
     * Test method for {@link double distanceSquared(Point)}.
     */
    @Test
    void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test the distanceSquare with point
        Point p1 = new Point(1.0, 2.0, 3.0);
        assertEquals(p1.distanceSquared(new Point(1.0, 5.0, 7.0)), 25, 0.00001, "distanceSquare of 2 Points does not work correctly");

        // =============== Boundary Values Tests ==================
        // TC11: Test the distanceSquare of point with himself
        assertEquals(p1.distanceSquared(new Point(1.0, 2.0, 3.0)), 0, 0.00001, "distanceSquare of Point with himself, does not work correctly");
    }

    /**
     * Test method for {@link double distance(Point)}.
     */
    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test the distance with point
        Point p1 = new Point(1.0, 2.0, 3.0);
        assertEquals(p1.distance(new Point(1.0, 5.0, 7.0)), 5, 0.00001, "distance of 2 Points does not work correctly");

        // =============== Boundary Values Tests ==================
        // TC11: Test the distance of point with himself
        assertEquals(p1.distance(new Point(1.0, 2.0, 3.0)), 0, 0.00001, "distance of Point with himself, does not work correctly");
    }
}