package geometries;

import primitives.*;

import java.util.*;

/**
 * This class is used to store the geometries of the objects in the scene.
 */
public class Geometries extends Intersectable {
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
        this.geometries = List.of(geometries);
    }

    public void add(Intersectable... geometries) {
        this.geometries.addAll(List.of(geometries));
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> result = null;

        for (var geometry : this.geometries) {
            List<Point> points = geometry.findIntersections(ray);

            if (points != null) {

                if (result == null) {
                    result = new LinkedList<>();
                }

                result.addAll(points);
            }
        }

        return result;

    }

    /**
     * Given a ray, find all the points where the ray intersects the sphere
     *
     * @param ray The ray to test for intersections.
     * @return A list of points.
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> intersections = null;

        for (var geometry : this.geometries) {
            List<GeoPoint> intersectionsPoints = geometry.findGeoIntersections(ray);

            if (intersectionsPoints != null) {
                if (intersections == null)
                    intersections = new LinkedList<>();

                intersections.addAll(intersectionsPoints);
            }
        }

        return intersections;
    }
}
