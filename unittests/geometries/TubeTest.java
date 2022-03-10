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
     * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test the getNormal of tube works right
        Tube tube1 = new Tube(new Ray(new Point(0.0, 1.0, 1.0), new Vector(1.0, 0.0, 0.0)), 3);
        assertEquals(new Vector(0.0, 0.0, -1.0), tube1.getNormal(new Point(2.0, 1.0, 0.0)), "Bad normal to tube");
    }
}