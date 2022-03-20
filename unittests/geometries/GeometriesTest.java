package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Geometries class
 */
class GeometriesTest {

    @Test
    /**
     * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}}
     */
    void testFindIntersections() {
        Geometries geometries = new Geometries(
                new Sphere(new Point(1.0, 0.0, 0.0), 1.0),
                new Plane(
                        new Point(1.0, 0.0, 0.0),
                        new Point(0.0, 1.0, 0.0),
                        new Point(0.0, 0.0, 1.0)
                ),
                new Triangle(
                        new Point(1.0, 0.0, 0.0),
                        new Point(0.0, 1.0, 0.0),
                        new Point(0.0, 0.0, 1.0)
                )
        );
        List<Point> intersections;

        // ============ Equivalence Partitions Tests ==============
        // TC01: test that FindIntersections on collection that some (but not all) shapes are cut, working currently
        intersections = geometries.findIntersections(new Ray(new Point(3.0, 0.0, 0.0), new Vector(-3.0, 0.0, 1.5)));
        assertEquals(3, intersections.size(), "A few geometries intersects doesn't working currently");

        // =============== Boundary Values Tests ==================
        // TC11: test that FindIntersections on empty collection, working currently
        assertNull(new Geometries().findIntersections(new Ray(new Point(1.0, 1.0, 1.0), new Vector(2.0, 2.0, 3.0))), "empty collection doesn't working currently");

        // TC11: test that FindIntersections on collection that no cut shape, working currently
        assertNull(geometries.findIntersections(new Ray(new Point(3.0, 2.0, 0.0), new Vector(-3.0, -2.0, 5.0))), "No geometries intersects doesn't working currently");

        // TC11: test that FindIntersections on collection that only one shape is cut, working currently
        intersections = geometries.findIntersections(new Ray(new Point(3.0, 2.0, 0.0), new Vector(-3.0, -2.0, 2.0)));
        assertEquals(1, intersections.size(), "A one geometries intersect doesn't working currently");

        // TC11: test that FindIntersections on collection that all shapes are cut, working currently
        intersections = geometries.findIntersections(new Ray(new Point(2.5, 2.0, 0.0), new Vector(-2.5, -2.0, 0.5)));
        assertEquals(4, intersections.size(), "All geometries intersects doesn't working currently");
    }
}