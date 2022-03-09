package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Plane class
 *
 */
public class PlaneTest {

    /**
     * Test method for {@link Vector getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        PolygonTests p = new PolygonTests();
        p.testGetNormal();
    }
}