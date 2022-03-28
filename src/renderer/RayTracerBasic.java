package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

public class RayTracerBasic extends RayTracerBase{
    /**
     * This is the constructor for the RayTracerBasic class. It calls the constructor for the RayTracerBase class.
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * Given a ray, return the color of the object that the ray hits
     *
     * @param ray The ray that is being traced.
     * @return The color of the object that the ray hits.
     */
    @Override
    public Color traceRay(Ray ray) {
        return null;
    }

    public Color calcColor(Point point){
        return null;
    }
}
