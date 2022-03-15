package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import primitives.Util.*;
import primitives.Vector;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Triangle class
 *
 */
public class TriangleTest {

    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test the getNormal of Triangle works right
        Triangle triangle = new Triangle(new Point(1.0, 0.0, 0.0), new Point(0.0, 1.0, 0.0), new Point(0.0, 0.0, 1.0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), triangle.getNormal(new Point(0.0, 0.0, 1.0)), "Bad normal to triangle");
    }

    @Test
    /**
     * Test method for {@link geometries.Triangle.FindIntersections(primitives.Ray)}
     */
    void testFindIntersections() {
        Triangle triangle = new Triangle(new Point(1.0, 0.0, 0.0), new Point(0.0, 1.0, 0.0), new Point(0.0, 0.0, 1.0));
        List<Point> intersectionsPoints = new LinkedList<Point>();

        // ============ Equivalence Partitions Tests ==============

        // TC01: Test when the ray intersects inside the triangle - this is need to be 1 intersection point
        intersectionsPoints = triangle.findIntersections(new Ray(new Point(0.0,0.0,0.0),new Vector(1.0,1.0,2.0)));
        assertEquals(intersectionsPoints.size(), 1, "The number of intersections points is wrong");
        assertEquals(intersectionsPoints.get(0), new Point(0.25,0.25,0.5), "The intersection point is wrong");

        // TC02: Test when the ray outside against edge - this is need to be 0 intersection point
        assertNull(triangle.findIntersections(new Ray(new Point(0.0,0.0,0.0), new Vector(1.0,-1.0,2.0))),"There should be no point of intersection because the ray outside against edge");

        // TC03: Test when the ray outside against vertex - this is need to be 0 intersection point
        assertNull(triangle.findIntersections(new Ray(new Point(-1.0,-1.0,-1.0), new Vector(0.5,0.5,1.5))),"There should be no point of intersection because the ray outside against vertex");


        // =============== Boundary Values Tests ==================

        // ****************** the ray begins "before" the plane ******************
        // TC11: Test when the ray intersects the triangle on edge - this is need to be 0 intersection point
        assertNull(triangle.findIntersections(new Ray(new Point(0.5,0.0,0.0),new Vector(0.0,0.0,0.5))),"There should be no point of intersection because the ray intersects the triangle on edge");

        // TC12: Test when the ray intersects the triangle in vertex - this is need to be 0 intersection point
        assertNull(triangle.findIntersections(new Ray(new Point(0.0,-1.0,0.0), new Vector(0.0,1.0,1.0))),"There should be no point of intersection because the ray intersects the triangle in vertex");

        // TC13: Test when the ray intersects the triangle on edge's continuation - this is need to be 0 intersection point
        assertNull(triangle.findIntersections(new Ray(new Point(2.0,0.0,0.0), new Vector(-2.0,0.0,1.5))),"There should be no point of intersection because the ray intersects the triangle on edge's continuation");
    }
}