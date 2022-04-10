package geometries;

import primitives.*;

/**
 * Geometry interface represents all the shapes we work with
 *
 */
public abstract class Geometry extends Intersectable{
    protected Color emission = Color.BLACK;


    /**
     * returns the normal vector (vertical) to the body at the point.
     *
     * @param point one point-type parameter [across the geometric body]
     * @return the normal vector (vertical) to the body at this point.
     */
    public abstract Vector getNormal(Point point);

    /**
     * returns the normal vector (vertical) to the body at the point.
     *
     * @return the emission color
     */
    public Color getEmission() {
        return this.emission;
    }

    /**
     * set the emission color.
     *
     * @param emission color to the geometry
     * @return the emission color that created.
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }


}
