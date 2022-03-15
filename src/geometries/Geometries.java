package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable{
    private List<Intersectable> geometries;

    public Geometries() {
        this.geometries = new LinkedList<Intersectable>();
    }//The goal is to create a union between them all, we would prefer a linked list
    // where everyone points to the other than working by indexes,
    // which will be useful while running in the transition from 1 to the second
    // and not by accessing a direct index that at best takes us an O of n to find it

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
