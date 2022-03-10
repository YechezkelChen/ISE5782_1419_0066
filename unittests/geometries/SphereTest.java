package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Sphere class
 *
 */
public class SphereTest {

    /**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test the getNormal of Sphere works right
        Sphere sphere = new Sphere(new Point(1.0, 1.0, 1.0), 3);
        assertEquals(new Vector(0.6, 0.8, 0.0 ), sphere.getNormal(new Point(4.0, 5.0, 1.0)), "Bad normal to sphere");
    }
}