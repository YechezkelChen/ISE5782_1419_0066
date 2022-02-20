package geometries;

import primitives.*;

/**
 * Cylinder class represents .............................?????
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
