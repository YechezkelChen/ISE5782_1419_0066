package geometries;

import java.util.*;
import primitives.*;

/**
 * Intersectable interface represents cutting points of all the shapes we work with
 *
 */
public interface Intersectable {
    /**
     * Given a ray, find all the points where the ray intersects the sphere
     *
     * @param ray The ray to test for intersections.
     * @return A list of points.
     */
    public List<Point> findIntersections(Ray ray);
}
