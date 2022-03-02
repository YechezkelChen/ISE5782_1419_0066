package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Point class
 *
 */
class PointTest {

    /**
     * Test method for {@link Point add(Vector)}.
     */
    @Test
    void testAdd() {
        Point p1 = new Point(1.0, 2.0, 3.0);
        if (!(p1.add(new Vector(-1.0, -2.0, -3.0)).equals(new Point(0.0, 0.0, 0.0))))
            out.println("ERROR: Point + Vector does not work correctly");
    }

    /**
     * Test method for {@link Vector subtract(Point)}.
     */
    @Test
    void testSubtract() {
        Point p1 = new Point(1.0, 2.0, 3.0);
        if (!new Vector(1.0, 1.0, 1.0).equals(new Point(2.0, 3.0, 4.0).subtract(p1)))
            out.println("ERROR: Point - Point does not work correctly");
    }

    /**
     * Test method for {@link double distanceSquared(Point)}.
     */
    @Test
    void testDistanceSquared() {
    }

    /**
     * Test method for {@link double distance(Point)}.
     */
    @Test
    void testDistance() {
    }
}