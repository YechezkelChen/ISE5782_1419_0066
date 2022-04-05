package geometries;

import java.util.*;
import java.util.stream.Collectors;

import primitives.*;

/**
 * Intersect-able interface represents cutting points of all the shapes we work with
 */
public abstract class Intersectable {


    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            GeoPoint geoPoint = (GeoPoint) o;

            if (geometry != null ? !geometry.equals(geoPoint.geometry) : geoPoint.geometry != null) return false;
            return point != null ? point.equals(geoPoint.point) : geoPoint.point == null;
        }

        @Override
        public String toString() {
            return "GeoPoint{" + "geometry=" + geometry + ", point=" + point + '}';
        }
    }

    /**
     * Given a ray, find all the points where the ray intersects the sphere
     *
     * @param ray The ray to test for intersections.
     * @return A list of points.
     */
    public List<Point> findIntersections(Ray ray) {
        List<GeoPoint> geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();

    }

    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return null;
    }

    ;
}
