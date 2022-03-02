package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.*;

/**
 * Unit tests for Vector class
 *
 */
class VectorTest {

    /**
     * Test method for {@link Vector add(Vector)}.
     */
    @Test
    void testAdd() {
    }

    /**
     * Test method for {@link Vector scale(double)}.
     */
    @Test
    void testScale() {
    }

    /**
     * Test method for {@link Vector crossProduct(Vector)}.
     */
    @Test
    void testCrossProduct() {
        Vector v1 = new Vector(1.0, 2.0, 3.0);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0.0, 3.0, -2.0);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2.0, -4.0, -6.0);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3), "crossProduct() for parallel vectors does not throw an exception");

    }

    /**
     * Test method for {@link double dotProduct(Vector)}.
     */
    @Test
    void testDotProduct() {
        Vector v1 = new Vector(1.0, 2.0, 3.0);
        Vector v2 = new Vector(-2.0, -4.0, -6.0);
        Vector v3 = new Vector(0.0, 3.0, -2.0);
        if (!isZero(v1.dotProduct(v3)))
            out.println("ERROR: dotProduct() for orthogonal vectors is not zero");
        if (!isZero(v1.dotProduct(v2) + 28))
            out.println("ERROR: dotProduct() wrong value");
    }

    /**
     * Test method for {@link double lengthSquared()}.
     */
    @Test
    void testLengthSquared() {
        Vector v1 = new Vector(1.0, 2.0, 3.0);
        if (!isZero(v1.lengthSquared() - 14))
            out.println("ERROR: lengthSquared() wrong value");
    }

    /**
     * Test method for {@link double length()}.
     */
    @Test
    void testLength() {
        Vector v1 = new Vector(0.0, 3.0, 4.0);
        if (!isZero(v1.length() - 5))
            out.println("ERROR: length() wrong value");
    }

    /**
     * Test method for {@link Vector normalize()}.
     */
    @Test
    void testNormalize() {
        Vector v = new Vector(0.0, 3.0, 4.0);
        Vector n = v.normalize();
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(1d, n.lengthSquared(), 0.00001, "wrong normalized vector length");
        assertThrows(IllegalArgumentException.class, () -> v.crossProduct(n), //
                "normalized vector is not in the same direction");
        assertEquals(new Vector(0.0, 0.6, 0.8), n, "wrong normalized vector");
    }
}