package primitives;

/**
 * This class will serve all primitive classes based on points
 */
public class Point {
    final protected Double3 xyz;

    /**
     * Constructor to initialize Point based object with its three number values
     *
     * @param x first number value
     * @param y second number value
     * @param z third number value
     */
    public Point(Double x, Double y, Double z) {
        this.xyz = new Double3(x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return super.equals(point.xyz);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Adding a vector to a point
     *
     * @param vector Vector for add
     * @return a new point
     */
    public Point add(Vector vector) {
        return new Point(this.xyz.d1 + vector.xyz.d1, this.xyz.d2 + vector.xyz.d2, this.xyz.d3 + vector.xyz.d3);
    }

    /**
     * Vector subtraction
     *
     * @param point - second point
     * @return a vector from the second point to the point on which the operation is performed
     */
    public Vector subtract(Point point) {
        return new Vector(point.xyz.d1 - this.xyz.d1, point.xyz.d2 - this.xyz.d2, point.xyz.d3 - this.xyz.d3);
    }

    /**
     * Calculate the distance between two points squared
     *
     * @param point the second point
     * @return distance between the two points squared
     */
    public double distanceSquared(Point point) {
        double x = this.xyz.d1 + point.xyz.d1;
        double y = this.xyz.d2 + point.xyz.d2;
        double z = this.xyz.d3 + point.xyz.d3;

        return x * x + y * y + z * z;
    }

    /**
     * Calculate the distance between two points
     *
     * @param point the second point
     * @return distance between the two points
     */
    public double distance(Point point) {
        return Math.sqrt(distanceSquared(point));
    }
}
