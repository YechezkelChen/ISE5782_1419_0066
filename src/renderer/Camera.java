package renderer;

import primitives.*;
import primitives.Vector;

import static primitives.Util.*;


/**
 * Camera class represents the camera and her distance form the view plane
 */
public class Camera {

    private Point location;

    private Vector vTo;

    private Vector vUp;
    private Vector vRight;

    private double height;
    private double width;
    private double distance;

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getDistance() {
        return distance;
    }

    /**
     * Constructs a camera with location, to and up vectors.
     * The right vector is being calculated by the "to" and "up" vectors.
     *
     * @param location The camera's location.
     * @param vTo         The direction to where the camera is looking.
     * @param vUp         The direction of the camera's upper direction.
     * @throws IllegalArgumentException When to and up vectors aren't orthogonal.
     */
    public Camera(Point location, Vector vTo, Vector vUp) {
        if (!isZero(vTo.dotProduct(vUp))) {
            throw new IllegalArgumentException("Up vector is not Orthogonal with To vector");
        }
        this.location = location;
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();
        this.vRight = vTo.crossProduct(vUp).normalize();
    }


    /**
     * This function sets the width and height of the viewport
     *
     * @param width  The width of the viewport in pixels.
     * @param height the height of the viewport in pixels
     * @return Nothing.
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Set the vertical field of view distance
     *
     * @param distance The distance from the camera to the center of the scene.
     * @return Nothing.
     */
    public Camera setVPDistance(double distance) {
        if (distance < 0)
            throw new IllegalArgumentException("distance must have >= 0");

        this.distance = distance;
        return this;
    }


    /**
     * Construct a ray from the given pixel to the center of the image
     *
     * @param nX The x-coordinate of the pixel in the image.
     * @param nY The y-coordinate of the pixel in the image.
     * @param j The y-coordinate of the pixel in the image.
     * @param i The row of the pixel in the image.
     * @return Nothing
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        return null;
    }


}
