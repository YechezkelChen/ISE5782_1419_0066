package primitives;

public class Material {

    /**
     * A public variable of type Double3, and it is initialized to the value of Double3.ZERO.
     */
    public Double3 Kd = Double3.ZERO;

    /**
     * A public variable of type Double3, and it is being initialized to the value of Double3.ZERO.
     */
    public Double3 Ks = Double3.ZERO;

    /**
     * A public variable of type Double3, and it is being initialized to the value of Double3.ZERO.
     */
    public Double3 Kr = Double3.ZERO;

    /**
     * A public variable of type Double3, and it is being initialized to the value of Double3.ZERO.
     */
    public Double3 Kt = Double3.ZERO;

    /**
     * A public variable of type int, and it is initialized to the value of 0.
     */
    public int Shininess = 0;

    /**
     * Set the diffuse color of the material to the given color and return the material.
     *
     * @param kd The diffuse color of the material.
     * @return The material itself.
     */
    public Material setKd(Double3 kd) {
        this.Kd = kd;
        return this;
    }

    /**
     * Set the diffuse coefficient to the same value for all three color channels.
     *
     * @param kD The diffuse color of the material.
     * @return The material itself.
     */
    public Material setKd(double kD) {
        this.Kd = new Double3(kD);
        return this;
    }

    /**
     * "Set the specular reflectivity of this material to the given value."
     *
     * The first line of the function is a JavaDoc comment. This is a special comment that is used to document the
     * function. The comment is written in HTML, and the JavaDoc tool will generate a web page from it
     *
     * @param ks The specular color of the material.
     * @return The material itself.
     */
    public Material setKs(Double3 ks) {
        this.Ks = ks;
        return this;
    }

    /**
     * Set the specular coefficient to the same value for all three color channels.
     *
     * @param kS specular coefficient
     * @return The material itself.
     */
    public Material setKs(double kS) {
        this.Ks = new Double3(kS);
        return this;
    }

    /**
     * Set the reflection coefficient for this material.
     *
     * @param kr The color of the reflected light.
     * @return The material itself.
     */
    public Material setKr(Double3 kr) {
        Kr = kr;
        return this;
    }

    /**
     * Set the material's Kt (transmission coefficient) to the given value.
     *
     * @param kt The color of the material.
     * @return The material itself.
     */
    public Material setKt(Double3 kt) {
        Kt = kt;
        return this;
    }

    /**
     * Sets the shininess of the material and returns the material.
     *
     * @param shininess The shininess of the material.
     * @return The material object itself.
     */
    public Material setShininess(int shininess) {
        this.Shininess = shininess;
        return this;
    }
}
