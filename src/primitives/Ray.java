package primitives;

import java.util.*;

import geometries.Intersectable.GeoPoint;

import static primitives.Util.*;

/**
 * This class will serve all primitive classes based on rays
 * A ray is defined by a point and a vector
 */
public class Ray {

    /**
     * A private variable that is final.
      */
    private final Point p0;

    /**
     * A vector that is the direction of the ray.
     */
    private final Vector dir;

    /**
     * constant number for size moving first rays for shading rays
     */
    private static final double DELTA = 0.1;

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

    public Ray(Point p0, Vector dir, Vector normal) {
        this.dir = dir;
        //check that the normal and the direction are not orthogonal
        double nv = alignZero(normal.dotProduct(dir));

        // if the normal and the direction are not orthogonal
        if (isZero(nv))
            this.p0 = p0;
        else {
            Vector moveVector = normal.scale(nv > 0 ? DELTA : -DELTA);
            // move the head of the vector in the right direction
            this.p0 = p0.add(moveVector);
        }
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
        if (intersections == null)
            return null;

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
    public Point findClosestPoint(List<Point> intersections) {
        return intersections == null || intersections.isEmpty() ? null
                : findClosestGeoPoint(intersections.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

}
