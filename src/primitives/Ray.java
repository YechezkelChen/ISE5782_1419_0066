package primitives;

import java.util.*;
import geometries.Intersectable.GeoPoint;
import static primitives.Util.*;

/**
 * This class will serve all primitive classes based on rays
 * A ray is defined by a point and a vector
 */
public class Ray {
    private final Point p0;
    private final Vector dir;

    /**
     * Constructor to initialize Ray based point and vector
     *
     * @param p0  the point in Ray
     * @param dir the vector in Ray
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = new Point(p0.xyz.d1, p0.xyz.d2, p0.xyz.d3);
        this.dir = dir.normalize();
    }

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0.toString() +
                ", dir=" + dir.toString() +
                '}';
    }

    /**
     * Given a scalar t, return the point on the line scale in t
     *
     * @param t The parameter value.
     * @return A point.
     */
    public Point getPoint(double t) {
        //P= P0 + t*v
        if (isZero(t))
            return p0;

        return p0.add(dir.scale(alignZero(t)));
    }

    /**
     * Find the point in a list of points that is closest to the head point of ray
     *
     * @param intersections a list of points
     * @return The closest GeoPoint to the head point of ray.
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> intersections) {
        if(intersections == null || intersections.size() == 0)
            return null;

        // 2 variables help
        GeoPoint closestPoint = intersections.get(0);
        double minDistance = this.p0.distance(closestPoint.point);
        double distance;

        for (var geoPoint : intersections) {
            distance = this.p0.distance(geoPoint.point);
            if (distance < minDistance) {
                closestPoint = geoPoint;
                minDistance = distance;
            }
        }

        return closestPoint;
    }

    /**
     * Find the point in a list of points that is closest to the head point of ray
     *
     * @param intersections a list of points
     * @return The closest point to the head point of ray.
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

}
