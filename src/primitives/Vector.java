package primitives;

/**
 * This class will serve all primitive classes based on vectors
 */
public class Vector extends Point {

    /**
     * Constructor to initialize Vector based object with its three number values
     *
     * @param x first number value
     * @param y second number value
     * @param z third number value
     */
    public Vector(Double x, Double y, Double z) {
        super(x, y, z);
        if (xyz.equals(xyz.ZERO))
            throw new IllegalArgumentException("ERROR: You have entered the 0 vector, it is invalid");
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public String toString() {
        return "Vector: " + super.toString();
    }

    /**
     * Adding a vector
     *
     * @param vector Vector for add
     * @return a new vector after the adding
     */
    public Vector add(Vector vector) {
        return new Vector(this.xyz.d1 + vector.xyz.d1, this.xyz.d2 + vector.xyz.d2, this.xyz.d3 + vector.xyz.d3);
    }

    /**
     * Vector subtraction
     *
     * @param vector - second vector
     * @return a new vector after the subtraction
     */
    public Vector subtract(Vector vector) {
        return new Vector(vector.xyz.d1 - this.xyz.d1 , vector.xyz.d2 - this.xyz.d2 , vector.xyz.d3 - this.xyz.d3 );
    }


    /**
     * Given a vector and a scalar, return a new vector that is the original vector scaled by the scalar
     *
     * @param scalar the number to multiply the vector by
     * @return A new Vector object.
     */
    public Vector scale(double scalar) {
        return new Vector(this.xyz.d1 * scalar, this.xyz.d2 * scalar, this.xyz.d3 * scalar);
    }

    /**
     * Cross product
     *
     * @param vector the second vector for the multiply
     * @return a new vector that is perpendicular to the two existing vectors
     */
    public Vector crossProduct(Vector vector) {
        return new Vector(
                this.xyz.d2 * vector.xyz.d3 - this.xyz.d3 * vector.xyz.d2,
                this.xyz.d3 * vector.xyz.d1 - this.xyz.d1 * vector.xyz.d3,
                this.xyz.d1 * vector.xyz.d2 - this.xyz.d2 * vector.xyz.d1
        );
    }

    /**
     * Dot product
     *
     * @param vector the second vector for the multiply
     * @return Scalar after the multiply
     */
    public double dotProduct(Vector vector) {
        return this.xyz.d1 * vector.xyz.d1 + this.xyz.d2 * vector.xyz.d2 + this.xyz.d3 * vector.xyz.d3;
    }

    /**
     * Calculate the length of the vector squared
     *
     * @return the length of the vector squared
     */
    public double lengthSquared() {
        return
                this.xyz.d1 * this.xyz.d1
                        + this.xyz.d2 * this.xyz.d2
                        + this.xyz.d3 * this.xyz.d3;
    }

    /**
     * Calculate the length of the vector
     *
     * @return the length of the vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * A normalization operation on vector
     *
     * @return a new vector normalized in the same direction as the original vector
     */
    public Vector normalize() {
        return this.scale(1 / this.length());
    }
}
