package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for Cylinder class
 *
 */
public class CylinderTest {

    /**
     * Test method for {@link Vector getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test the getNormal of cylinder works right
        Cylinder cylinder1 = new Cylinder(new Ray(new Point(1.0, 2.0, 3.0), new Vector(2.0, 4.0, 6.0)), 3, 5);
        assertEquals(new Vector(????????), cylinder1.getNormal(new Point(1.0, 2.0, 3.0)), "Bad normal to cylinder");

        // =============== Boundary Values Tests ==================
        // TC11: Test the calculation with radius 0 throws an exception accordingly
        Cylinder cylinder2 = new Cylinder(new Ray(new Point(1.0, 2.0, 3.0), new Vector(2.0, 4.0, 6.0)), 0, 5);
        assertThrows(IllegalArgumentException.class, () -> cylinder2.getNormal(new Point(1.0, 2.0, 3.0)),
                "getNormal() for radius = 0 does not throw an exception");
    }
}