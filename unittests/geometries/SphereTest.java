package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Sphere class
 *
 */
public class SphereTest {

    /**
     * Test method for {@link Vector getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test the getNormal of Sphere works right
        Sphere sphere1 = new Sphere(new Point(1.0, 1.0, 1.0), 3);
        assertEquals(new Vector(1d / 3, 1d / 3, 1d / 3), sphere1.getNormal(new Point(1.0, 2.0, 3.0)), "Bad normal to sphere");

        // =============== Boundary Values Tests ==================
        // TC11: Test the calculation with radius 0 throws an exception accordingly
        Sphere sphere2 = new Sphere(new Point(1.0, 2.0, 3.0), 0);
        assertThrows(IllegalArgumentException.class, () -> sphere2.getNormal(new Point(1.0, 2.0, 3.0)),
                "getNormal() for radius = 0 does not throw an exception");
    }
}