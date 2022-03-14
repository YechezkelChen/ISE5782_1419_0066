package geometries;

import primitives.*;

/**
 * Geometry interface represents all the shapes we work with
 *
 */
public interface Geometry extends Intersectable{

    /**
     * returns the normal vector (vertical) to the body at the point.
     *
     * @param point one point-type parameter [across the geometric body]
     * @return the normal vector (vertical) to the body at this point.
     */
    Vector getNormal(Point point);
}
