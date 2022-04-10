package lighting;
import primitives.*;

/**
 * AmbientLight is a subclass of Light that takes in a color and a double3 and then scales the color by the double3
 */
public class AmbientLight extends Light {

    /**
     * A default constructor that takes Black color and insert to intensity.
     */
    public AmbientLight() {
        super(Color.BLACK);
    }

    /**
     * A constructor that takes in a color and a double3 and then scales the color by the double3.
     */
    public AmbientLight(Color iA, Double3 kA) {
        super(iA.scale(kA));
    }
}
