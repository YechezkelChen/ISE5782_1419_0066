package geometries;

import primitives.*;

/**
 * Sphere class represents .............................?????
 *
 */
public class Sphere implements Geometry{

    private Point center;
    private double radius;

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
