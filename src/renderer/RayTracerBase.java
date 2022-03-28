package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * Defining a class that is abstract, meaning that it cannot be instantiated.
 */
public abstract class RayTracerBase {
    /**
     * Making the `scene` variable accessible to all subclasses.
     */
    protected Scene scene;

    /**
     * A constructor. It is a method that is called when an object is created.
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * Given a ray, return the color of the object that the ray hits
     *
     * @param ray The ray that is being traced.
     * @return The color of the object that the ray hits.
     */
    public abstract Color traceRay(Ray ray);
}
