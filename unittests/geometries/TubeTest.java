package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Tube class
 *
 */
public class TubeTest {

    /**
     * Test method for {@link Vector getNormal(Point)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test the getNormal of tube works right
        Tube tube1 = new Tube(new Ray(new Point(1.0, 2.0, 3.0), new Vector(2.0, 4.0, 6.0)), 3);
        assertEquals(new Vector(????????), tube1.getNormal(new Point(1.0, 2.0, 3.0)), "Bad normal to tube");

        // =============== Boundary Values Tests ==================
        // TC11: Test the calculation with radius 0 throws an exception accordingly
        Tube tube2 = new Tube(new Ray(new Point(1.0, 2.0, 3.0), new Vector(2.0, 4.0, 6.0)), 0);
        assertThrows(IllegalArgumentException.class, () -> tube2.getNormal(new Point(1.0, 2.0, 3.0)),
                "getNormal() for radius = 0 does not throw an exception");
    }
}