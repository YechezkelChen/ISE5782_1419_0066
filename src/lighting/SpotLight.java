package lighting;

import primitives.*;

public class SpotLight extends PointLight {

    /**
     * direction vector of the spot light
     */
    private Vector direction;


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

        return super.getIntensity(p).scale(dirL);
    }
}
