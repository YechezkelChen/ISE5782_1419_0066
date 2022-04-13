package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {

    /**
     * constant number for size moving first rays for shading rays
     */
    private static final double DELTA = 0.1;

    /**
     * constant number for size moving first rays for recursion stop conditions
     */
    private static final Double3 INITIAL_K = new Double3(1.0);
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;

    /**
     * This is the constructor for the RayTracerBasic class. It calls the constructor for the RayTracerBase class.
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * Given a ray, find the closest point of intersection with the scene, and return the color of that point
     *
     * @param ray The ray that we're tracing.
     * @return The color of the closest point.
     */
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        if (closestPoint == null)
            return scene.background;

        return calcColor(closestPoint, ray);
    }

    /**
     * Calculate the color of the point
     *
     * @param intersection The point in 3D space where the ray is originating from.
     * @return The color of the ambient light.
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
        if(intersection==null)
            return scene.background;

        Color color =  intersection.geometry.getEmission()
                .add(calcLocalEffects(intersection, ray));
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
    }

    private Color calcColor(GeoPoint closestPoint, Ray ray) {
        return calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(this.scene.ambientLight.getIntensity());
    }

    /**
     * It calculates the color of a point on a geometry, by calculating the color of the light sources that affect it
     *
     * @param gp  The point on the geometry that the ray intersected with.
     * @param ray the ray that hit the geometry
     * @return The color of the point.
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Color color = Color.BLACK;
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return color;

        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                if (unshaded(lightSource, gp, l, n, nv)) {
                    Color iL = lightSource.getIntensity(gp.point);
                    color = color.add(iL.scale(calcDiffusive(material, nl)))
                            .add(iL.scale(calcSpecular(material, n, l, nl, v)));
                }
            }
        }
        return color;
    }

    /**
     * If the dot product of the normal and light vector is negative, make it positive and scale it by the diffuse color of
     * the material.
     *
     * @param material The material of the object that the ray hit.
     * @param nl       the dot product of the normal and the light vector
     * @return The diffuse color of the material.
     */
    private Double3 calcDiffusive(Material material, double nl) {
        if (nl < 0) nl *= -1;
        return material.Kd.scale(nl);
    }

    /**
     * > Calculate the specular component of the light reflected from a point on a surface
     *
     * @param material The material of the object that is being shaded.
     * @param n        normal vector
     * @param l        the direction of the light source
     * @param nl       the dot product of the normal and the light vector
     * @param v        the vector from the intersection point to the camera
     * @return The specular component of the light.
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(nl).scale(2));
        double minus_vr = Math.pow(v.scale(-1).dotProduct(r), material.Shininess);
        if (minus_vr <= 0)
            return Double3.ZERO;

        return material.Ks.scale(minus_vr);
    }

    /**
     * If the ray from the point to the light source intersects with any geometry, then the point is shaded
     *
     * @param lightSource The light source that we are checking if it is unshaded.
     * @param gp The point on the geometry that we're currently shading.
     * @param l The vector from the point to the light source.
     * @param n The normal vector to the surface at the intersection point.
     * @param nv the dot product of the normal vector and the vector from the camera to the point.
     * @return the color of the point.
     */
    private boolean unshaded(LightSource lightSource, GeoPoint gp, Vector l, Vector n, double nv) {
        Vector lightDirection = l.scale(-1); // from point to light source

        Vector epsVector = n.scale(nv < 0 ? DELTA : -DELTA);
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(point, lightDirection);

        List<GeoPoint> intersections = this.scene.geometries.findGeoIntersections(lightRay);

        if (intersections == null)
            return true;

        double lightDistance = lightSource.getDistance(point);
        for (var geoPoint : intersections)
            if (new Double3(geoPoint.point.distance(point)).lowerThan(lightDistance)
                    && gp.geometry.getMaterial().Kt == Double3.ZERO)
                    return false;

        return true;
    }

    private Color calcGlobalEffects(GeoPoint geopoint, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Material material = geopoint.geometry.getMaterial();

        Vector v = ray.getDir();
        Vector n = geopoint.geometry.getNormal(geopoint.point);
        if (v.dotProduct(n) > 0)
            n = n.scale(-1);

        // the reflection effect
        Ray reflectedRay = constructReflectedRay(n, geopoint.point, ray);
        GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
        Double3 kr = geopoint.geometry.getMaterial().Kr;
        Double3 kkr = kr.product(k);
        if (!(kkr.lowerThan(MIN_CALC_COLOR_K) && kkr.equals(new Double3(MIN_CALC_COLOR_K))))
            color = color.add(calcColor(reflectedPoint, reflectedRay, (level-1), kkr).scale(kr));


        // the refraction effect
        Ray refractedRay = constructRefractedRay(n.scale(-1), geopoint.point, ray);
        GeoPoint refractedPoint = findClosestIntersection(refractedRay);
        Double3 kt = geopoint.geometry.getMaterial().Kt;
        Double3 kkt = kt.product(k);
        if (!(kkt.lowerThan(MIN_CALC_COLOR_K) && kkt.equals(new Double3(MIN_CALC_COLOR_K))))
            color = color.add(calcColor(reflectedPoint, reflectedRay, (level-1), kkt).scale(kt));

        return color;
    }

    private Ray constructReflectedRay(Vector n, Point point, Ray ray) {
        Vector v = ray.getDir();
        Vector vn = n.scale(-2 *  v.dotProduct(n));
        Vector r = v.add(vn);

        // use the constructor with 3 arguments to move the head on normal
        return new Ray(point, r, n);
    }

    private Ray constructRefractedRay(Vector n, Point point, Ray ray) {
        return new Ray(point, ray.getDir(), n);
    }

    private GeoPoint findClosestIntersection(Ray reflectedRay) {
        List<GeoPoint> intersections = this.scene.geometries.findGeoIntersections(reflectedRay);
        if (intersections == null) // || intersections.size() == 0)?????????????????????????????????????????????????????
            return null;
        else
            return reflectedRay.findClosestGeoPoint(intersections);
    }
}
