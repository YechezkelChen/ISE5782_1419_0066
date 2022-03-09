package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.*;

/**
 * Unit tests for Vector class
 */
public class VectorTest {

    /**
     * Test method for {@link Vector add(Vector)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test the add operation with vectors
        Vector v1 = new Vector(1.0, 2.0, 3.0);
        assertEquals(v1.add(new Vector(-1.0, -2.0, -3.0)), new Vector(0.0, 0.0, 0.0), "Vector + Vector does not work correctly");

        // =============== Boundary Values Tests ==================
        // TC11: Test the add operation with vector and contrasting vector of the same coordinate
        assertThrows(IllegalArgumentException.class, () ->v1.add(new Vector(-1.0, -2.0, -3.0)),
                "Vector + (-)Vector give vector Zero and not throw  exception");
    }

    /**
     * Test method for {@link Vector scale(double)}.
     */
    @Test
    void testScale() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test the scale operation with vector
        Vector v1 = new Vector(1.0, 2.0, 3.0);
        assertEquals(v1.scale(5), new Vector(5.0, 10.0, 15.0), "scale * Vector does not work correctly");

        // TC02: Test the scale operation with negative scale on vector
        assertEquals(v1.scale(-5), new Vector(-5.0, -10.0, -15.0), "scale * Vector does not work correctly");

        // =============== Boundary Values Tests ==================
        // TC11: Test the scale operation with 0 on vector
        assertThrows(IllegalArgumentException.class, () ->v1.scale(0),
                "0 * Vector does not work correctly and not throw  exception");
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
        // TC11: test zero vector from cross-product of co-lined vectors
        Vector v3 = new Vector(-2.0, -4.0, -6.0);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");

    }

    /**
     * Test method for {@link double dotProduct(Vector)}.
     */
    @Test
    void testDotProduct() {
        Vector v1 = new Vector(1.0, 2.0, 3.0);
        Vector v2 = new Vector(-2.0, -4.0, -6.0);
        Vector v3 = new Vector(0.0, 3.0, -2.0);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Test that dotProduct for orthogonal vectors is zero
        assertTrue(isZero(v1.dotProduct(v3)), "dotProduct() for orthogonal vectors is not zero");

        // TC02: Test that dotProduct result is not the correct value
        assertTrue(isZero(v1.dotProduct(v2) + 28), "dotProduct() wrong value");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from dot-product of orthogonal vectors
        assertThrows(IllegalArgumentException.class, () -> v1.dotProduct(v3),
                "dotProduct() for orthogonal vectors does not throw an exception");
    }

    /**
     * Test method for {@link double lengthSquared()}.
     */
    @Test
    void testLengthSquared() {
        Vector v1 = new Vector(1.0, 2.0, 3.0);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test if the length result is correct
        assertTrue(isZero(v1.lengthSquared() - 14), "lengthSquared() wrong value");
    }

    /**
     * Test method for {@link double length()}.
     */
    @Test
    void testLength() {
        Vector v1 = new Vector(0.0, 3.0, 4.0);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test if the length result is correct
        assertTrue(isZero(v1.lengthSquared() - 5), "length() wrong value");
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