package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.*;

/**
 * Cylinder class represents geometric place of all the points in space,
 * which are at a fixed distance, the radius of the cylinder, some plane, the axis of the cylinder,
 * and it has a height
 */
public class Cylinder extends Tube {

    private final double height;

    /**
     * Constructor to initialize Cylinder based object on Tube and height
     *
     * @param axisRay ray of tube
     * @param radius  radius of tube
     * @param height  height of cylinder
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        if (height <= 0)
            throw new IllegalArgumentException("The height must to be bigger than 0");

        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                "} " + super.toString();
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

        //if P=P0, then (P-P0) is the zero vector
        //so return the vector of the base as a normal
        if (P.equals(P0))
            return v.scale(-1);

        double t = v.dotProduct(P.subtract(P0));
        //check if the point on the bottom edge
        if (isZero(t))
            return v.scale(-1);

        //check if the point on the top edge
        if (isZero(t - this.height))
            return v;

        Point O = P0.add(v.scale(t));
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
