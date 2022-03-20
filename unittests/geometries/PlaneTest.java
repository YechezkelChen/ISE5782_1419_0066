package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import primitives.Util.*;
import primitives.Vector;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

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

    @Test
    /**
     * Test method for {@link geometries.Plane.FindIntersections(primitives.Ray)}
     */
    void testFindIntersections() {
        Plane plane1 = new Plane(new Point(1.0, 0.0, 0.0), new Point(0.0, 1.0, 0.0), new Point(0.0, 0.0, 1.0));
        Plane plane2 = new Plane(new Point(1.0, 0.0, 0.0), new Point(0.0, 1.0, 0.0), new Point(0.0, 0.0, 0.0));
        List<Point> intersectionsPoints = new LinkedList<Point>();

        // ============ Equivalence Partitions Tests ==============

        // TC01: Test when the ray intersects the plane(1 points)
        intersectionsPoints = plane1.findIntersections(new Ray(new Point(2.0, 0.0, 0.0), new Vector(-2.0, 0.0, 1.0)));
        assertEquals(intersectionsPoints.size(), 1, "The number of intersections points is wrong");
        assertEquals(intersectionsPoints.get(0), new Point(0.0, 0.0, 1.0), "The intersection point is wrong");

        // TC02: Test when the ray does not intersect the plane(0 points)
        assertNull(plane1.findIntersections(new Ray(new Point(2.0, 0.0, 0.0), new Vector(-2.0, 0.0, 2.0))),"There should be no point of intersection");

        // =============== Boundary Values Tests ==================

        // ****************** Cases where the ray is parallel ******************
        // TC11: Test when the ray is parallel to the plane and the ray included in the plane(0 points)
        assertNull(plane1.findIntersections(new Ray(new Point(1.0, 0.0, 0.0), new Vector(-1.0, 0.0, 1.0))),"There should be no point of intersection because the ray is parallel to the plane and included in the plane");

        // TC12: Test when the ray is parallel to the plane and the ray not included in the plane - this is need to be 0 intersection point
        assertNull(plane1.findIntersections(new Ray(new Point(2.0, 0.0, 0.0), new Vector(-2.0, 0.0, 2.0))),"There should be no point of intersection because the ray is parallel to the plane");

        // ****************** Cases where the ray is orthogonal ******************
        // TC13: Test when Ray is orthogonal to the plane before the plane(1 points)
        intersectionsPoints = plane2.findIntersections(new Ray(new Point(0.0, 0.0, -1.0), new Vector(0.0, 0.0, 2.0)));
        assertEquals(intersectionsPoints.size(), 1, "The number of intersections points is wrong");
        assertEquals(intersectionsPoints.get(0), new Point(0.0, 0.0, 0.0), "The intersection point is wrong");

        // TC14: Test when Ray is orthogonal to the plane in the plane(0 point)
        assertNull(plane2.findIntersections(new Ray(new Point(0.0, 0.0, 0.0), new Vector(0.0, 0.0, 1.0))),"There should be no point of intersection because the ray is after the plane");

        // TC15: Test when Ray is orthogonal to the plane after the plane(0 points)
        assertNull(plane2.findIntersections(new Ray(new Point(0.0, 0.0, 1.0), new Vector(0.0, 0.0, 1.0))),"There should be no point of intersection because the ray is after the plane");

        // ****************** Special cases ******************
        // TC16: Ray is neither orthogonal nor parallel to and begins at the plane (𝑃0 is in the plane, but not the ray) - this is need to be 0 intersection point
        assertNull(plane2.findIntersections(new Ray(new Point(1.0, 1.0, 0.0), new Vector(-1.0, -1.0, 1.0))),"There should be no point of intersection because the ray begins at the plane");

        // TC17: Ray is neither orthogonal nor parallel to the plane and begins in the same point which appears as reference point in the plane (Q) - this is need to be 0 intersection point
        assertNull(plane2.findIntersections(new Ray(plane2.getQ0(), new Vector(-1.0, 0.0, 1.0))),"There should be no point of intersection because the ray begins in the same point which appears as reference point in the plane");
    }
}