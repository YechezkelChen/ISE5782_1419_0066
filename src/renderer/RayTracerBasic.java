package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {

    /**
     * constant number for size moving first rays for recursion stop conditions
     */
    private static final Double3 INITIAL_K = new Double3(1.0);
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double EPS = 0.1;

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
     * > Calculate the color of a given point on the scene, by calculating the local effects (diffuse, specular, etc.) and
     * the global effects (reflection, refraction, etc.)
     * <p>
     * The function receives the following parameters:
     * <p>
     * * `intersection` - the point on the scene we want to calculate the color for
     * * `ray` - the ray that hit the scene at the given point
     * * `level` - the recursion level of the ray (how many times it has been reflected/refracted)
     * * `k` - the attenuation factor of the ray (how much light is left after passing through a transparent object)
     * <p>
     * The function returns the color of the given point
     *
     * @param intersection The intersection point of the ray and the geometry.
     * @param ray          the ray that intersects the geometry
     * @param level        the recursion level.
     * @param k            the color of the current pixel
     * @return The color of the intersection point.
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
        if (intersection == null)
            return scene.background;

        Color color = intersection.geometry.getEmission()
                .add(calcLocalEffects(intersection, ray, k));
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
    }


    /**
     * The function calculates the color of a point on a surface of a geometry in the scene, by calculating the color of
     * the point by the light sources in the scene, and the color of the point by the reflected rays from the other
     * geometries in the scene
     *
     * @param closestPoint The closest point to the ray's head.
     * @param ray          the ray that was sent from the camera to the scene
     * @return The color of the closest point.
     */
    private Color calcColor(GeoPoint closestPoint, Ray ray) {
        return calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(this.scene.ambientLight.getIntensity());
    }

    /**
     * It calculates the color of a point on a geometry, by calculating the color of the light sources that affect it
     *
     * @param gp  The point on the geometry that the ray intersected with.
     * @param ray the ray that hit the geometry
     * @param k   The attenuation factor of the ray (how much light is left after passing through a transparent object)
     * @return The color of the point.
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
        Color color = Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point);
        Vector v = ray.getDir();
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return color;

        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Double3 ktr = transparency(gp, lightSource, l, n);
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
                    Color iL = lightSource.getIntensity(gp.point).scale(ktr);
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
        return material.kD.scale(nl);
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
        double minus_vr = v.scale(-1).dotProduct(r);
        if (minus_vr <= 0)
            return Double3.ZERO;
        return material.kS.scale(Math.pow(minus_vr, material.nShininess));
    }

    /**
     * If the ray from the point to the light source intersects with any geometry, then the point is shaded
     *
     * @param gp The point on the geometry that we're currently shading.
     * @param ls The light source that we are checking if it is unshaded.
     * @param l  The vector from the point to the light source.
     * @param n  The normal vector to the surface at the intersection point.
     * @return the color of the point.
     */
    private Double3 transparency(GeoPoint gp, LightSource ls, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1); // from point to light source

        Ray lightRay = new Ray(gp.point, lightDirection, n);
        Double3 ktr = new Double3(1);

        List<GeoPoint> intersections = this.scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null)
            return ktr;

        double lightDistance = ls.getDistance(lightRay.getP0());
        for (var geoPoint : intersections)
            if (geoPoint.point.distance(lightRay.getP0()) < lightDistance)
                ktr = geoPoint.geometry.getMaterial().kT.product(ktr);

        return ktr;
    }


    /**
     * If the ray from the point to the light source intersects with any geometry, then the point is shaded
     *
     * @param lightSource The light source that we are checking if it is unshaded.
     * @param gp          The point on the geometry that we're currently shading.
     * @param l           The vector from the point to the light source.
     * @param n           The normal vector to the surface at the intersection point.
     * @param nv          the dot product of the normal vector and the vector from the camera to the point.
     * @return the color of the point.
     */
    private boolean unshaded(LightSource lightSource, GeoPoint gp, Vector l, Vector n, double nv) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(gp.point, lightDirection, n);

        List<GeoPoint> intersections = this.scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null)
            return true;

        double lightDistance = lightSource.getDistance(lightRay.getP0());
        for (var geoPoint : intersections)
            if (geoPoint.point.distance(lightRay.getP0()) < lightDistance &&
                    gp.geometry.getMaterial().kT == Double3.ZERO)
                return false;

        return true;
    }

    private Color calcGlobalEffects(GeoPoint geopoint, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Vector n = geopoint.geometry.getNormal(geopoint.point);

        // the reflection effect
        Double3 kr = geopoint.geometry.getMaterial().kR;
        Double3 kkr = kr.product(k);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K)) {
            Ray reflectedRay = constructReflectedRay(n, geopoint.point, ray);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
        }

        // the refraction effect
        Double3 kt = geopoint.geometry.getMaterial().kT;
        Double3 kkt = kt.product(k);
        if (!kkt.lowerThan(MIN_CALC_COLOR_K)) {
            Ray refractedRay = constructRefractedRay(n, geopoint.point, ray);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
            color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
        }

        return color;
    }

    private Ray constructReflectedRay(Vector n, Point point, Ray ray) {
        Vector v = ray.getDir();
        Vector vn = n.scale(-2 * v.dotProduct(n));
        Vector r = v.add(vn);

        return new Ray(point, r, n);
    }

    private Ray constructRefractedRay(Vector n, Point point, Ray ray) {
        return new Ray(point, ray.getDir(), n);
    }

    private GeoPoint findClosestIntersection(Ray reflectedRay) {
        List<GeoPoint> intersections = this.scene.geometries.findGeoIntersections(reflectedRay);
        if (intersections == null)
            return null;
        return reflectedRay.findClosestGeoPoint(intersections);
    }
}