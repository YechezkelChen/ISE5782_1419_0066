package geometries;

import primitives.*;

/**
 * Cylinder class represents geometric place of all the points in space,
 * which are at a fixed distance, the radius of the cylinder, some plane, the axis of the cylinder
 *
 */
public class Cylinder extends Tube{

    private double height;

    /**
     * Constructor to initialize Cylinder based object on Tube and height
     *
     * @param axisRay ray of tube
     * @param radius radius of tube
     * @param height height of cylinder
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
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
     * @param point one point-type parameter [across the geometric body]
     * @return the normal vector (vertical) to the body at this point.
     */
    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
