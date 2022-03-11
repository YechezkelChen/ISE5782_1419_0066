package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Plane class
 *
 */
public class PlaneTest {

    /**
     * Test method for {@link geometries.Plane#Plane(primitives.Point, primitives.Point, primitives.Point)}
     */
    @Test
    public void testConstructor() {
        final Point p1 = new Point(1.0, 0.0, 0.0);
        final Point p2 = new Point(0.0, 1.0, 0.0);
        final Point p3 = new Point(0.0, 2.0, 0.0);
        final Point p4 = new Point(0.0, 3.0, 0.0);

        // =============== Boundary Values Tests ==================

        // TC11: The first and second points are the same
        assertThrows(IllegalArgumentException.class, () -> new Plane(p1, p1, p2),
                "No exception is thrown when building a plane with 2 identical points");

        // TC12: The 3 points are on the same line
        assertThrows(IllegalArgumentException.class, () -> new Plane(p2, p3, p4),
                "No exception is thrown when building a plane with 3 points on the same line");
    }

    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test the getNormal of Plane works right
        Plane plane = new Plane(new Point(1.0, 0.0, 0.0), new Point(0.0, 1.0, 0.0), new Point(0.0, 0.0, 1.0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), plane.getNormal(new Point(0.0, 0.0, 1.0)), "Bad normal to plane");
    }
}