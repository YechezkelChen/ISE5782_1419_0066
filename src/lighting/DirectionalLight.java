package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * A directional light is a light that has a direction and an intensity
 */
public class DirectionalLight extends Light implements LightSource {

    /**
     * vector of direction of light
     */
    private Vector direction;

    /**
     * A constructor for the class Light.
     *
     * @param intensity - of the light
     * @param direction - of the light
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    /**
     * Given a point, return the color of the light at that point.
     *
     * @param p The point in the scene to get the color of.
     * @return A color object.
     */
    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity();
    }

    /**
     * Given a point, return a vector that points from the origin to the point.
     *
     * @param p The point at which the light is being evaluated.
     * @return A vector
     */
    @Override
    public Vector getL(Point p) {
        return this.direction;
    }
}
