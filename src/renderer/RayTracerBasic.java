package renderer;

import geometries.Intersectable.*;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;

public class RayTracerBasic extends RayTracerBase {
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
        List<GeoPoint> intersectionGeoPoints = this.scene.geometries.findGeoIntersections(ray);
        if (intersectionGeoPoints == null)
            return scene.background;
        else {
            GeoPoint closestPoint = ray.findClosestGeoPoint(intersectionGeoPoints);
            return this.calcColor(closestPoint);
        }
    }

    /**
     * Calculate the color of the geometric Point
     *
     * @param geoPoint The point in 3D space where the ray is originating from.
     * @return The color of the ambient light.
     */
    private Color calcColor(GeoPoint geoPoint) {
        return this.scene.ambientLight.getIntensity().add(geoPoint.geometry.getEmission());
    }
}
