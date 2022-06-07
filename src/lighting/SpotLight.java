package lighting;

import primitives.*;

/**
 * It's a point light that has a direction and an angle
 */
public class SpotLight extends PointLight {

    /**
     * direction vector of the spot light
     */
    private Vector direction;
    private int angle = 1;


    /**
     * create the intensity, direction and position of the light
     *
     * @param intensity of the light
     * @param position  of the light
     * @param direction of the light
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
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
        double dirL = this.direction.dotProduct(super.getL(p));
        if(dirL <= 0)
            return Color.BLACK;

        return super.getIntensity(p).scale(Math.pow(dirL,angle));
    }

    /**
     * This function sets the angle of the light beam to the given angle and returns the light object.
     *
     * @param angle The angle of the spotlight's beam.
     * @return The SpotLight object.
     */
    public SpotLight setNarrowBeam(int angle){
        this.angle = angle;
        return this;
    }
}
