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