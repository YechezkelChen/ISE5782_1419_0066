package geometries;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.*;

/**
 * Cylinder class represents geometric place of all the points in space,
 * which are at a fixed distance, the radius of the cylinder, some plane, the axis of the cylinder,
 * and it has a height
 */
public class Cylinder extends Tube {

    private final double height;

    /**
     * Constructor to initialize Cylinder based object on Tube and height
     *
     * @param axisRay ray of tube
     * @param radius  radius of tube
     * @param height  height of cylinder
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        if (height <= 0)
            throw new IllegalArgumentException("The height must to be bigger than 0");

        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                "} " + super.toString();
    }

    /**
     * returns the normal vector (vertical) to the body at the point.
     *
     * @param P one point-type parameter [across the geometric body]
     * @return the normal vector (vertical) to the body at this point.
     */
    @Override
    public Vector getNormal(Point P) {
        // Finding the normal according to the formula:
        // n = normalize(P - O)
        // t = v * (P - P0)
        // O = P0 + t * v

        Vector v = axisRay.getDir();
        Point P0 = axisRay.getP0();

        //if P=P0, then (P-P0) is the zero vector
        //so return the vector of the base as a normal
        if (P.equals(P0))
            return v.scale(-1);

        double t = v.dotProduct(P.subtract(P0));
        //check if the point on the bottom edge
        if (isZero(t) || isZero(t * t))
            return v.scale(-1);

        //check if the point on the top edge
        if (isZero(t - this.height))
            return v;

        Point O = P0.add(v.scale(t));
        return P.subtract(O).normalize();
    }

    /**
     * Given a ray, find all the points where the ray intersects the sphere
     *
     * @param ray The ray to test for intersections.
     * @return A list of GeoPoints.
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {

        List<GeoPoint> intersections = null;
        List<GeoPoint> intersectionsHelper;

        Vector va = this.axisRay.getDir();
        Point p1 = this.axisRay.getP0();
        Point p2 = p1.add(va.scale(this.height));

        //1) get the intersections with tube that includes the cylinder
        //2) get the intersections with bottom plane
        //3) get the intersections with top plane

        intersectionsHelper = super.findGeoIntersectionsHelper(ray); //get intersections for tube
        if (intersectionsHelper != null) {
            intersections = new LinkedList<>();

            //Add all intersections of tube that are in the cylinder's bounders
            for (GeoPoint geoPoint : intersectionsHelper)
                if (va.dotProduct(geoPoint.point.subtract(p1)) > 0 && va.dotProduct(geoPoint.point.subtract(p2)) < 0)
                    intersections.add(new GeoPoint(this, geoPoint.point));
        }

        intersectionsHelper = findIntersectionsWithBase(ray, new Plane(p1,va)); //intersections with bottom's plane
        if(intersectionsHelper != null){
            if(intersections == null)
                intersections = new LinkedList<>();
            intersections.addAll(intersectionsHelper);
        }

        intersectionsHelper = findIntersectionsWithBase(ray, new Plane(p2, va)); //intersections with top's plane
        if(intersectionsHelper != null){
            if(intersections == null)
                intersections = new LinkedList<>();
            intersections.addAll(intersectionsHelper);
        }
        if(intersections == null || intersections.size() == 0)
            return null;

        return intersections;
    }

    private List<GeoPoint> findIntersectionsWithBase(Ray ray, Plane basePlane) {
        List<GeoPoint> intersections = null;

        List<GeoPoint> intersections1 = basePlane.findGeoIntersectionsHelper(ray); //intersections with bottom's plane
        if (intersections1 != null) {
            intersections = new LinkedList<>();

            //Add all intersections of plane that are in the base's bounders
            for (GeoPoint geoPoint : intersections1) {
                if (geoPoint.point.equals(basePlane.getQ0())) //to avoid vector ZERO
                    intersections.add(new GeoPoint(this, geoPoint.point));

                //Formula that checks that point is inside the base
                else if ((geoPoint.point.subtract(basePlane.getQ0()).dotProduct(geoPoint.point.subtract(basePlane.getQ0())) < this.radius * this.radius))
                    intersections.add(new GeoPoint(this, geoPoint.point));

            }
        }
        if(intersections == null || intersections.size() == 0)
            return null;

        return intersections;
    }
}