package primitives;

public class Material {

    /**
     * A public variable of type Double3, and it is initialized to the value of Double3.ZERO.
     */
    public Double3 kD = Double3.ZERO;

    /**
     * A public variable of type Double3, and it is being initialized to the value of Double3.ZERO.
     */
    public Double3 kS = Double3.ZERO;

    /**
     * A public variable of type Double3, and it is being initialized to the value of Double3.ZERO.
     */
    public Double3 kR = Double3.ZERO;

    /**
     * A public variable of type Double3, and it is being initialized to the value of Double3.ZERO.
     */
    public Double3 kT = Double3.ZERO;

    /**
     * A public variable of type int, and it is initialized to the value of 0.
     */
    public int nShininess = 0;

    /**
     * Set the diffuse color of the material to the given color and return the material.
     *
     * @param kD The diffuse color of the material.
     * @return The material itself.
     */
    public Material setKd(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * "Set the specular reflectivity of this material to the given value."
     *
     * The first line of the function is a JavaDoc comment. This is a special comment that is used to document the
     * function. The comment is written in HTML, and the JavaDoc tool will generate a web page from it
     *
     * @param kS The specular color of the material.
     * @return The material itself.
     */
    public Material setKs(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Set the reflection coefficient for this material.
     *
     * @param kR The color of the reflected light.
     * @return The material itself.
     */
    public Material setKr(Double3 kR) {
        this.kR = kR;
        return this;
    }

    /**
     * Set the material's Kt (transmission coefficient) to the given value.
     *
     * @param kT The color of the material.
     * @return The material itself.
     */
    public Material setKt(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * Sets the shininess of the material and returns the material.
     *
     * @param nShininess The shininess of the material.
     * @return The material object itself.
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
