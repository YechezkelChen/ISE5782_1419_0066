package geometries;

import primitives.*;

/**
 * Plane class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 *
 */
public class Plane implements Geometry{

    private Point q0;
    private Vector normal;

    /**
     * Calculates the normal and inserts the normal vector,
     * also the constructor saves the first point as the reference point of the plane
     *
     * @param vertex1 first point value
     * @param vertex2 second point value
     * @param vertex3 third point value
     */
    public Plane(Point vertex1, Point vertex2, Point vertex3) {
        this.q0 = vertex1;
        this.normal = getNormal(q0);
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
    public Vector getNormal(Point point) {
        return null;
    }
}
