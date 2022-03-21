package renderer;

import primitives.*;

import static primitives.Util.*;


/**
 * Camera class represents the camera and her distance form the view plane
 */
public class Camera {

    private Point p0;

    private Vector vTo;
    private Vector vUp;
    private Vector vRight;

    private double width;
    private double height;
    private double distance;

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getDistance() {
        return distance;
    }

    /**
     * Constructs a camera with location, to and up vectors.
     * The right vector is being calculated by the "to" and "up" vectors.
     *
     * @param p0  The camera's location.
     * @param vTo The direction to where the camera is looking.
     * @param vUp The direction of the camera's upper direction.
     * @throws IllegalArgumentException When to and up vectors aren't orthogonal.
     */
    public Camera(Point p0, Vector vTo, Vector vUp) {
        if (!isZero(vTo.dotProduct(vUp)))
            throw new IllegalArgumentException("vectorTo and vectorUp are not orthogonal");

        this.p0 = p0;
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();
        this.vRight = vTo.crossProduct(vUp).normalize();
    }

    /**
     * This function sets the width and height of the view-plane.
     *
     * @param width  The width of the view-plane.
     * @param height the height of the view-plane.
     * @return the view-plane.
     */
    public Camera setVPSize(double width, double height) {
        if (width < 0)
            throw new IllegalArgumentException("width must have >= 0");
        if (height < 0)
            throw new IllegalArgumentException("height must have >= 0");

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
     * @param nX The amount of columns (row width) of the pixel in the image.
     * @param nY The amount of rows (column height) of the pixel in the image.
     * @param j  The column of the pixel in the image.
     * @param i  The row of the pixel in the image.
     * @return Nothing
     */
    public Ray constructRay(int nX, int nY, int j, int i) {

        // Image center
        Point Pc = this.p0.add(this.vTo.scale(this.distance));

        //Ratio (pixel width & height)
        double Ry = this.height / nY;
        double Rx = this.width / nX;

        //Pixel[i,j] center
        double yI = -(i - (nY - 1) / 2.0) * Ry;
        double xJ = (j - (nX - 1) / 2.0) * Rx;

        Point pIJ = Pc;
        if (xJ != 0) pIJ = pIJ.add(vRight.scale(xJ));
        if (yI != 0) pIJ = pIJ.add(vUp.scale(yI));

        Vector vIJ = pIJ.subtract(this.p0);

        return new Ray(this.p0, vIJ);
    }


}
