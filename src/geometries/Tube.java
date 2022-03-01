package geometries;

import primitives.*;

/**
 * Tube class represents infinite cylinder with ray and radius
 *
 */
public class Tube implements Geometry {

    protected final Ray axisRay;
    protected final double radius;

    /**
     * Constructor to initialize Tube based object with ray and radius
     *
     * @param axisRay ray of tube
     * @param radius radius of tube
     */
    public Tube(Ray axisRay, double radius) {
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
     * @param point one point-type parameter [across the geometric body]
     * @return the normal vector (vertical) to the body at this point.
     */
    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
