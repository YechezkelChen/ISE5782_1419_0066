package geometries;

import primitives.*;

import java.util.List;

/**
 * Sphere class represents geometric body composed of
 * points in space whose distance from a fixed point is
 * at most a certain fixed number, called a radius.
 *
 */
public class Sphere implements Geometry{

    private final Point center;
    private final double radius;

    /**
     * Constructor to initialize Sphere based object with point and radius
     *
     * @param center center of sphere
     * @param radius radius of sphere
     */
    public Sphere(Point center, double radius) {
        if (radius <= 0)
            throw new IllegalArgumentException("The radius must to be bigger than 0");

        this.center = center;
        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }

    /**
     * returns the normal vector (vertical) to the body at the point.
     *
     * @param point one point-type parameter [across the geometric body]
     * @return the normal vector (vertical) to the body at this point.
     */
    @Override
    public Vector getNormal(Point point) {
        // Finding the normal according to the formula:
        // n = normalize(P - O)
        // O = center of sphere

        return point.subtract(this.center).normalize();
    }

    /**
     * Given a ray, find all the points where the ray intersects the sphere
     *
     * @param ray The ray to test for intersections.
     * @return A list of points.
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
