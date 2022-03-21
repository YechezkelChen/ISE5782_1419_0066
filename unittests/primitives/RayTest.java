package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Ray class
 *
 */
class RayTest {

    @Test
    /**
     * Test method for  {@link primitives.Ray#getPoint(double)}}
     */
    void testGetPoint() {
        Ray ray = new Ray(new Point(1.0, 2.0, 3.0), new Vector(1.0, 0.0, 0.0));

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test the getPoint operation with ray and t>0
        Point pxe = ray.getPoint(2);
        assertEquals(ray.getPoint(2), new Point(3.0, 2.0, 3.0), "getPoint of Ray with t>0 does not work correctly");

        // TC02: Test the getPoint operation with ray and t<0
        assertEquals(ray.getPoint(-2), new Point(-1.0, 2.0, 3.0), "getPoint of Ray with t<0 does not work correctly");

        // =============== Boundary Values Tests ==================
        // TC11: Test the getPoint operation with ray and t=0
        assertEquals(ray.getPoint(0), new Point(1.0, 2.0, 3.0), "getPoint of Ray with t=0 does not work correctly");
    }
}