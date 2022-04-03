package renderer;

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
        List<Point> intersectionPoints = this.scene.geometries.findIntersections(ray);
        if (intersectionPoints == null)
            return scene.background;
        else {
            Point closestPoint = ray.findClosestPoint(intersectionPoints);
            return this.calcColor(closestPoint);
        }
    }

    /**
     * Calculate the color of the point
     *
     * @param point The point in 3D space where the ray is originating from.
     * @return The color of the ambient light.
     */
    private Color calcColor(Point point) {
        return this.scene.ambientLight.getIntensity();
    }
}
