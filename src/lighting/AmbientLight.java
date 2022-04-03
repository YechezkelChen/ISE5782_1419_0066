package lighting;
import primitives.*;

public class AmbientLight {
    /**
     * Creating a private final field called `intensity` that is of type `Color`.
     */
    private final Color intensity;

    public AmbientLight() {
        this.intensity = Color.BLACK;
    }

    public AmbientLight(Color iA, Double3 kA) {
        this.intensity = iA.scale(kA);
    }

    public Color getIntensity() {
        return intensity;
    }
}
