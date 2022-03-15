package primitives;

import static primitives.Util.*;

/**
 * This class will serve all primitive classes based on rays
 * A ray is defined by a point and a vector
 */
public class Ray {
    private final Point p0;
    private final Vector dir;

    /**
     * Constructor to initialize Ray based point and vector
     *
     * @param p0  the point in Ray
     * @param dir the vector in Ray
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = new Point(p0.xyz.d1, p0.xyz.d2, p0.xyz.d3);
        this.dir = dir.normalize();
    }

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }

    public Point getPoint(double t) {
        //P= P0 + t*v
        if(isZero(t))
            return p0;

        return p0.add(dir.scale(t));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0.toString() +
                ", dir=" + dir.toString() +
                '}';
    }
}
