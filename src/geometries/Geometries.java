package geometries;

import primitives.*;
import java.util.*;

/**
 * This class is used to store the geometries of the objects in the scene.
 */
public class Geometries implements Intersectable{
    /**
     * The goal is to create a union between them all, we would prefer a linked list
     * where everyone points to the other than working by indexes,
     * which will be useful while running in the transition from 1 to the second
     * and not by accessing a direct index that at best takes us an O of n to find it
     */
    private List<Intersectable> geometries;

    public Geometries() {
        this.geometries = new LinkedList<>();
    }

    public Geometries(Intersectable... geometries) {

    }

    public void add(Intersectable... geometries) {

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
