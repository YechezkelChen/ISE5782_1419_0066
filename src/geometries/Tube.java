package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.*;

/**
 * Tube class represents infinite cylinder with ray and radius
 */
public class Tube implements Geometry {

    protected final Ray axisRay;
    protected final double radius;

    /**
     * Constructor to initialize Tube based object with ray and radius
     *
     * @param axisRay ray of tube
     * @param radius  radius of tube
     */
    public Tube(Ray axisRay, double radius) {
        if (radius <= 0)
            throw new IllegalArgumentException("The radius must to be bigger than 0");

        this.axisRay = axisRay;
        this.radius = radius;
    }

    public Ray getAxisRay() {
        return axisRay;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    /**
     * returns the normal vector (vertical) to the body at the point.
     *
     * @param P one point-type parameter [across the geometric body]
     * @return the normal vector (vertical) to the body at this point.
     */
    @Override
    public Vector getNormal(Point P) {
        // Finding the normal according to the formula:
        // n = normalize(P - O)
        // t = v * (P - P0)
        // O = P0 + t * v

        Vector v = axisRay.getDir();
        Point P0 = axisRay.getP0();

        double t = v.dotProduct(P.subtract(P0));

        Point O = P0;
        // if t=0, then t*v is the zero vector and O=P0.
        if (!isZero(t))
            O = P0.add(v.scale(t));

        return P.subtract(O).normalize();
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
