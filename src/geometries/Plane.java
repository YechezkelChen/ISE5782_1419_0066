package geometries;

import primitives.*;
import primitives.Vector;

import java.util.*;

import static primitives.Util.*;

/**
 * Plane class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 * A plane is defined by a point and a normal vector
 */
public class Plane extends Geometry {

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
        if (p1.equals(p2) || p1.equals(p3) || p2.equals(p3))
            throw new IllegalArgumentException("All points should be different");

        this.q0 = p1;
        Vector v1 = p1.subtract(p2);
        Vector v2 = p2.subtract(p3);
        try {
            this.normal = v1.crossProduct(v2).normalize();
        }
        // Check that the three points not on the same line
        catch (IllegalArgumentException e) {
            // If the 3 points are on the same line a crossProduct will give the 0 vector and an exception is thrown
            throw new IllegalArgumentException("The 3 points are on the same line");
        }
    }

    /**
     * Constructor to initialize Plane based object with point and normal vector
     *
     * @param q0     point
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
        return this.normal;
    }

    /**
     * Given a ray, find all the points where the ray intersects the sphere
     *
     * @param ray The ray to test for intersections.
     * @return A list of points.
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        //t=n*(q0-Po)/n*v
        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        //if the ray on the plane, there is no intersections points
        if (this.q0.equals(p0))
            return null;

        double nqp = this.normal.dotProduct(this.q0.subtract(p0));
        //if the ray on the plane, there is no intersections points
        if (isZero(nqp))
            return null;

        double nv = this.normal.dotProduct(v);
        //if this is zero the denominator is zero, and this is illegal
        if (isZero(nv))
            return null;

        double t = nqp / nv;

        //if the ray after the plane, there is no intersections points
        if (t <= 0)
            return null;

        Point point=ray.getPoint(t);
        //the ray crosses the plane
        return List.of(new GeoPoint(this,point));
    }
}
