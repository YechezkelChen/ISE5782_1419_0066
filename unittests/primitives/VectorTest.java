package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals("crossProduct() wrong result length", v1.length() * v2.length(), vr.length(), 0.00001);

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v2)));

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2.0, -4.0, -6.0);
        assertThrows("crossProduct() for parallel vectors does not throw an exception",
                IllegalArgumentException.class, () -> v1.crossProduct(v3));

    }

    /**
     * Test method for {@link double dotProduct(Vector)}.
     */
    @Test
    void testDotProduct() {
    }

    /**
     * Test method for {@link double lengthSquared()}.
     */
    @Test
    void testLengthSquared() {
    }

    /**
     * Test method for {@link double length()}.
     */
    @Test
    void testLength() {
    }

    /**
     * Test method for {@link Vector normalize()}.
     */
    @Test
    void testNormalize() {
    }
}