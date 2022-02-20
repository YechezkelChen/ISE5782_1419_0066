package geometries;

import primitives.*;

/**
 * Triangle class represents two-dimensional shape contained in a plane, i.e. a polygon with 3 sides
 *
 */
public class Triangle extends Polygon{

    /**
     * Constructor to initialize Triangle based on Polygon with 3 points
     *
     * @param vertex1 first point value
     * @param vertex2 second point value
     * @param vertex3 third point value
     */
    public Triangle(Point vertex1, Point vertex2, Point vertex3) {
        super(vertex1, vertex2, vertex3);
    }
}
