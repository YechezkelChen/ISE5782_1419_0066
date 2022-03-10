package geometries;

import primitives.*;

/**
 * Plane class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 * A plane is defined by a point and a normal vector
 *
 */
public class Plane implements Geometry{

    private final Point q0;
    private final Vector normal;

    /**
     * Calculates the normal and inserts the normal vector,
     * also the constructor saves the first point as the reference point of the plane
     *
     * @param p1 first point value
     * @param p2 second point value
     * @param p3 third point value
     */
    public Plane(Point p1, Point p2, Point p3) {
        // Check that the three points are different from each other
        if(p1.equals(p2) || p1.equals(p3) || p2.equals(p3)) {
            throw new IllegalArgumentException("All points should be different");
        }

        this.q0 = p1;
        Vector v1 = p1.subtract(p2);
        Vector v2 = p2.subtract(p3);
        try {
            this.normal = v1.crossProduct(v2).normalize();
        }
        catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    /**
     * Constructor to initialize Plane based object with point and normal vector
     *
     * @param q0 point
     * @param normal vector
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal;
    }

    public Point getQ0() {
        return q0;
    }

    public Vector getNormal() {
        return normal;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + q0 +
                ", normal=" + normal +
                '}';
    }

    /**
     * returns the normal vector (vertical) to the body at the point.
     *
     * @param point one point-type parameter [across the geometric body]
     * @return the normal vector (vertical) to the body at this point.
     */
    @Override
    public Vector getNormal(Point point) {return this.normal;}
}
