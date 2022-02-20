package primitives;

/**
 * This class will serve all primitive classes based on vectors
 *
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
        return "Vector{} " + super.toString();
    }

    /**
     * Adding a vector
     *
     * @param vector Vector for add
     * @return a new vector after the adding
     */
    public Vector add(Vector vector) {
        return null;
    }

    /**
     * Vector subtraction
     *
     * @param vector - second vector
     * @return a new vector after the subtraction
     */
    public Vector subtract(Vector vector) {
        return null;
    }

    /**
     * Multiply Vector by Number - Scalar
     *
     * @param Scalar the number for the multiply
     * @return a new vector after the multiply
     */
    public Vector scale(double Scalar){
        return null;
    }

    /**
     * Cross product
     *
     * @param vector the second vector for the multiply
     * @return a new vector that is perpendicular to the two existing vectors
     */
    public Vector crossProduct(Vector vector) {
        return null;
    }

    /**
     * Dot product
     *
     * @param vector the second vector for the multiply
     * @return Scalar after the multiply
     */
    public double dotProduct(Vector vector) {
        return 0;
    }

    /**
     * Calculate the length of the vector squared
     *
     * @return the length of the vector squared
     */
    public double lengthSquared() {
        return 0;
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
     * @return  a new vector normalized in the same direction as the original vector
     */
    public Vector normalize() {
        return null;
    }
}
