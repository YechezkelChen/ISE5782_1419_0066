package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * A point light is a light source that has a position in space and a color
 */
public class PointLight extends Light implements LightSource{

    /**
     * point of the position of the light
     */
    private Point position;

    /**
     * Discount coefficients
     */
    private double kC = 1;
    private double kL = 0;
    private double kQ = 0;

    /**
     * A constructor for the class Light.
     *
     * @param intensity - of the light
     * @param position - of the light
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     * Sets the constant attenuation factor of the light.
     *
     * @param kC Constant attenuation factor
     * @return The object itself.
     */
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * Sets the constant attenuation factor of the light source.
     *
     * @param kL The constant attenuation factor.
     * @return The object itself.
     */
    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * Sets the constant attenuation factor of the light source.
     *
     * @param kQ Constant attenuation factor
     * @return The object itself.
     */
    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }

    /**
     * Given a point, return the color of the light at that point.
     *
     * @param p The point in the scene to get the color of.
     * @return A color object.
     */
    @Override
    public Color getIntensity(Point p) {
        double d = this.getDistance(p);
        return super.getIntensity().reduce(this.kC + this.kL * d + this.kQ * d * d);
    }

    /**
     * Given a point, return a vector that points from the origin to the point.
     *
     * @param p The point at which the light is being evaluated.
     * @return A vector
     */
    @Override
    public Vector getL(Point p) {
        return p.subtract(this.position).normalize();
    }

    /**
     * Returns the distance from the origin to the given point.
     *
     * @param point The point to which the distance is to be calculated.
     * @return The distance between the point and the origin.
     */
    @Override
    public double getDistance(Point point) {
        return this.position.distance(point);
    }
}
