package lighting;

import primitives.Color;
import primitives.*;

/**
 * Defining an interface for a light source.
 */
public interface LightSource {

    /**
     * Given a point, return the color of the light at that point.
     *
     * @param p The point in the scene to get the color of.
     * @return A color object.
     */
    Color getIntensity(Point p);

    /**
     * Given a point, return a vector that points from the origin to the point.
     *
     * @param p The point at which the light is being evaluated.
     * @return A vector
     */
    Vector getL(Point p);
}
