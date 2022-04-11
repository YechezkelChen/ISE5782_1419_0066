package lighting;

import primitives.Color;

/**
 * A abstract class for the intensity of the light.
 */
abstract class Light {
    /**
     * A private variable that is only accessible within the class.
     */
    private Color intensity;

    /**
     * A constructor for the class Light.
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * This function returns the intensity of the light.
     *
     * @return The intensity of the light.
     */
    public Color getIntensity() {
        return this.intensity;
    }
}
