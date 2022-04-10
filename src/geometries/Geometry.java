package geometries;

import primitives.*;

/**
 * Geometry is an abstract class that implements the Intersectable interface and has a protected Color emission field and
 * an abstract getNormal method.
 */
public abstract class Geometry extends Intersectable{

    /**
     * A default value for the emission color of the geometry.
     */
    protected Color emission  = Color.BLACK;

    /**
     * This function returns the emission color of the material
     *
     * @return The emission color of the material.
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * Sets the emission color of the geometry and returns the geometry.
     *
     * @param emission The color of the light emitted by the material.
     * @return The Geometry object itself.
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * returns the normal vector (vertical) to the body at the point.
     *
     * @param point one point-type parameter [across the geometric body]
     * @return the normal vector (vertical) to the body at this point.
     */
    public abstract Vector getNormal(Point point);
}
