package primitives;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.*;

/**
 * Unit tests for Vector class
 */
public class VectorTest {

    final Vector v1 = new Vector(1.0, 2.0, 3.0);
    final Vector v2 = new Vector(0.0, 3.0, -2.0);
    final Vector v3 = new Vector(-1.0, -2.0, -3.0);
    final Vector v4 = new Vector(2.0, 4.0, 6.0);
    final Vector v5 = new Vector(0.0, 3.0, 4.0);

    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test the add operation with vectors
        assertEquals(v1.add(v2), new Vector(1.0, 5.0, 1.0), "Vector + Vector does not work correctly");

        // =============== Boundary Values Tests ==================
        // TC11: Test the add operation with vector and contrasting vector of the same coordinate
        assertThrows(IllegalArgumentException.class, () ->v1.add(v3), "Vector + (-)Vector give vector Zero and not throw  exception");
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
    @Test
    void testScale() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test the scale operation with vector
        assertEquals(v1.scale(5), new Vector(5.0, 10.0, 15.0), "scale * Vector does not work correctly");

        // TC02: Test the scale operation with negative scale on vector
        assertEquals(v1.scale(-5), new Vector(-5.0, -10.0, -15.0), "scale * Vector does not work correctly");

        // =============== Boundary Values Tests ==================
        // TC11: Test the scale operation with 0 on vector
        assertThrows(IllegalArgumentException.class, () ->v1.scale(0), "0 * Vector does not work correctly and not throw  exception");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    void testCrossProduct() {
        // ============ Equivalence Partitions Tests ==============
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-product of parallel vectors
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v4), "crossProduct() for parallel vectors does not throw an exception");
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
     */
    @Test
    void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that dotProduct for parallel vectors
        assertEquals(v1.dotProduct(v4), 28, "dotProduct() does not work correctly");

        // =============== Boundary Values Tests ==================
        // TC11: test that return zero from dot-product of orthogonal vectors
        assertTrue(isZero(v1.dotProduct(v2)), "dotProduct() for orthogonal vectors does not return 0");
    }

    /**
     * Test method for {@link primitives.Vector#lengthSquared()}.
     */
    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test if the length result is correct
        assertEquals(v1.lengthSquared(), 14, "lengthSquared() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#length()}.
     */
    @Test
    void testLength() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test if the length result is correct
        assertEquals(v5.length(), 5, "length() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#normalize()}.
     */
    @Test
    void testNormalize() {
        Vector n = v5.normalize();
        // ============ Equivalence Partitions Tests ==============

        // TC01: Test if the normalization return unit vector
        assertEquals(n, new Vector(0.0, 0.6, 0.8), "wrong normalized vector");

        // TC02: Test if the normalization return vector in length of 1
        assertEquals(1d, n.lengthSquared(), 0.00001, "wrong normalized vector length");
    }
}