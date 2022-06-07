package geometries;

import java.util.*;
import primitives.*;

/**
 * "An Intersectable is an object that can be intersected by a ray."
 *
 * The above class is abstract, which means that it cannot be instantiated. It is a template for other classes to extend
 */
public abstract class Intersectable {

    /**
     * A GeoPoint is a Geometry and Point
     */
    public static class GeoPoint {
        /**
         * Geometry on the scene
         */
        public Geometry geometry;
        /**
         * Point on the geometry
         */
        public Point point;


        /**
         * A constructor for the GeoPoint class.
         * @param geometry - on the scene
         * @param point - on the geometry
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GeoPoint)) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry.toString() +
                    ", point=" + point +
                    '}';
        }
    }

    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

    /**
     * Given a ray, find all the points where the ray intersects the sphere
     *
     * @param ray The ray to test for intersections.
     * @return A list of points.
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();
    }
}
