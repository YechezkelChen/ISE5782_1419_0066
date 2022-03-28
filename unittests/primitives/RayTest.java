package primitives;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Ray class
 *
 */
class RayTest {
    Ray ray = new Ray(new Point(1.0, 2.0, 3.0), new Vector(1.0, 0.0, 0.0));

    @Test
    /**
     * Test method for  {@link primitives.Ray#getPoint(double)}}
     */
    void testGetPoint() {
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

    @Test
    /**
     * Test method for {@link Ray.FindClosestPoint}
     */
    void testFindClosestPoint() {
        List<Point> points = new LinkedList<>();
        Point p1 = new Point(10.0, 12.0, 13.0);
        Point p2 = new Point(2.0, 3.0, 4.0);
        Point p3 = new Point(-5.0, -6.0, -7.0);

        // ============ Equivalence Partitions Tests ==============
        // TC01: A point in the middle of the list is closest to the beginning of the fund
        points.add(p1); points.add(p2); points.add(p3);
        assertEquals(ray.findClosestPoint(points), p2, "closes point in the middle of the list don't work currently");
        points.clear();

        // =============== Boundary Values Tests ==================

        // TC11: Empty list
        assertNull(ray.findClosestPoint(points), "Empty list don't work currently");

        // TC12: The first point is closest to the beginning of the foundation
        points.add(p2); points.add(p1); points.add(p3);
        assertEquals(ray.findClosestPoint(points), p2, "closes point in the first of the list don't work currently");
        points.clear();

        // TC13: The last point is closest to the beginning of the foundation
        points.add(p1); points.add(p3); points.add(p2);
        assertEquals(ray.findClosestPoint(points), p2, "closes point in the last of the list don't work currently");
        points.clear();
    }
}