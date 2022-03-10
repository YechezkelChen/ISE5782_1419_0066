package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
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
}