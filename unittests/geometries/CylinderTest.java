package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for Cylinder class
 *
 */
public class CylinderTest {

    /**
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        Cylinder cylinder = new Cylinder(new Ray(new Point(1.0, 1.0, 0.0), new Vector(0.0, 0.0, 1.0)), 1d, 3d);

        // ============ Equivalence Partitions Tests ==============
        //TC01: Test with point on the bottom edge of the cylinder
        assertEquals(new Vector(0.0, 0.0, -1.0), cylinder.getNormal(new Point(1.0, 1.0, 0.0)), "Bad normal to the bottom of the cylinder");

        //TC02: Test with point on the top edge of the cylinder
        assertEquals(new Vector(0.0, 0.0, 1.0), cylinder.getNormal(new Point(1.0, 1.0, 3.0)), "Bad normal to the top of the cylinder");

        //TC03: Test with point on the side edge of the cylinder
        assertEquals(new Vector(0.0, -1.0, 0.0), cylinder.getNormal(new Point(1.0, 0.0, 1.0)), "Bad normal to the side of the cylinder");

        // =============== Boundary Values Tests ==================
        //TC10: Test with point in the center of top edge of the cylinder
        assertEquals(new Vector(0.0, 0.0, 1.0), cylinder.getNormal(new Point(1.0, 0.0, 3.0)), "Bad normal to the top-edge of the cylinder");

        //TC11: Test with point in the center of bottom edge of the cylinder
        assertEquals(new Vector(0.0, 0.0, -1.0), cylinder.getNormal(new Point(0.0, 1.0, 0.0)), "Bad normal to the bottom-edge of the cylinder");
    }
}