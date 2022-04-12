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
    private double Kc = 1;
    private double Kl = 0;
    private double Kq = 0;

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
     * @param kc Constant attenuation factor
     * @return The object itself.
     */
    public PointLight setKc(double kc) {
        this.Kc = kc;
        return this;
    }

    /**
     * Sets the constant attenuation factor of the light source.
     *
     * @param kl The constant attenuation factor.
     * @return The object itself.
     */
    public PointLight setKl(double kl) {
        this.Kl = kl;
        return this;
    }

    /**
     * Sets the constant attenuation factor of the light source.
     *
     * @param kq Constant attenuation factor
     * @return The object itself.
     */
    public PointLight setKq(double kq) {
        this.Kq = kq;
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
        double d = this.position.distance(p);
        return super.getIntensity().reduce(this.Kc + this.Kl * d + this.Kq * d * d);
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

    @Override
    public double getDistance(Point point) {
        return position.distance(point);
    }
}
