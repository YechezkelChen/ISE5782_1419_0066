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
