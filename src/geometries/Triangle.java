package geometries;

import primitives.*;
import primitives.Vector;

import java.util.*;

/**
 * Triangle class represents a polygon with three points
 */
public class Triangle extends Polygon {

    /**
     * Constructor to initialize Triangle based on Polygon with 3 points
     *
     * @param p1 first point value
     * @param p2 second point value
     * @param p3 third point value
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    /**
     * Given a ray, find all the points where the ray intersects the sphere
     *
     * @param ray The ray to test for intersections.
     * @return A list of points.
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> result = this.plane.findGeoIntersections(ray);

        if (result == null)
            return null;

        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        Vector v1 = this.vertices.get(0).subtract(p0); // p1-p0
        Vector v2 = this.vertices.get(1).subtract(p0); // p2-p0
        Vector v3 = this.vertices.get(2).subtract(p0); // p3-p0

        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();

        double product1 = v.dotProduct(n1);
        double product2 = v.dotProduct(n2);
        double product3 = v.dotProduct(n3);

        //If all the products are the same sign, a point of intersection within the triangle
        if ((product1 > 0 && product2 > 0 && product3 > 0) || (product1 < 0 && product2 < 0 && product3 < 0))
            return List.of(new GeoPoint(this, result.get(0).point));

        //1. If one product is zero the point on a rib or on the continuation of the rib
        //2. If 2 multiples are reset the point on one of the vertices of the triangle
        //3. If 3 multiples are reset it is not possible
        //4. If 2 positive multiples and 1 negative product then the point of intersection in front of the side
        //5. If 2 multiples are negative and 1 product is positive then the point of intersection is opposite the vertex
        return null;
    }
}
