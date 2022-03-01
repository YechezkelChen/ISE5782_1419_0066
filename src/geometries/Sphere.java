package geometries;

import primitives.*;

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
        return null;
    }
}
