package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {
    /**
     * This is the constructor for the RayTracerBasic class. It calls the constructor for the RayTracerBase class.
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    private static final double DELTA = 0.1;


    /**
     * Given a ray, find the closest point of intersection with the scene, and return the color of that point
     *
     * @param ray The ray that we're tracing.
     * @return The color of the closest point.
     */
    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersections = this.scene.geometries.findGeoIntersections(ray);
        if (intersections == null)
            return scene.background;

        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return this.calcColor(closestPoint, ray);
    }

    /**
     * Calculate the color of the point
     *
     * @param intersection The point in 3D space where the ray is originating from.
     * @return The color of the ambient light.
     */
    private Color calcColor(GeoPoint intersection, Ray ray) {
        return this.scene.ambientLight.getIntensity()
                .add(intersection.geometry.getEmission())
                .add(calcLocalEffects(intersection, ray));
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
                Color iL = lightSource.getIntensity(gp.point);
                color = color.add(iL.scale(calcDiffusive(material, nl)))
                        .add(iL.scale(calcSpecular(material, n, l, nl, v)));
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

    private boolean unshaded(GeoPoint gp, LightSource lightSource, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(gp.point, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

        if (intersections == null)
            return true;

        double ktr = 1.0;
        double lightDistance = lightSource.getDistance(gp.point);

        for (GeoPoint geoPoint : intersections) {

            if (alignZero(geoPoint.point.distance(gp.point) - lightDistance) <= 0) {
                return false;
            }
        }
        return true;
    }
}
