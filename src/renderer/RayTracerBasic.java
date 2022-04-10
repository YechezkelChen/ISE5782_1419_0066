package renderer;

import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

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
        List<GeoPoint> intersections = this.scene.geometries.findGeoIntersections(ray);
        if (intersections == null)
            return scene.background;

        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return this.calcColor(closestPoint);
    }

    /**
     * Calculate the color of the point
     *
     * @param intersection The point in 3D space where the ray is originating from.
     * @return The color of the ambient light.
     */
    private Color calcColor(GeoPoint intersection) {
        return this.scene.ambientLight.getIntensity().add(intersection.geometry.getEmission());
    }
}
