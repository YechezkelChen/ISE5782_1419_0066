package geometries;

import primitives.*;

public interface Geometry {

    /**
     * returns the normal vector (vertical) to the body at the point.
     *
     * @param point one point-type parameter [across the geometric body]
     * @return the normal vector (vertical) to the body at this point.
     */
    public Vector getNormal(Point point);
}
