package renderer;

import primitives.*;
import primitives.Vector;

import java.util.*;

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

    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;

    public Point getP0() {
        return p0;
    }

    public Vector getvTo() {
        return vTo;
    }

    public Vector getvUp() {
        return vUp;
    }

    public Vector getvRight() {
        return vRight;
    }

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
        if (width <= 0)
            throw new IllegalArgumentException("width must have > 0");
        if (height <= 0)
            throw new IllegalArgumentException("height must have > 0");

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
        if (distance <= 0)
            throw new IllegalArgumentException("distance must have > 0");

        this.distance = distance;
        return this;
    }

    /**
     * This function sets the image writer of the camera and returns the camera.
     *
     * @param imageWriter The ImageWriter object that will be used to write the image to a file.
     * @return The camera itself.
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * This function sets the ray tracer for this camera.
     *
     * @param rayTracer The ray tracer to use.
     * @return The camera object itself.
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    /**
     * Construct a ray from the image center to the pixel at (i,j)
     *
     * @param nX The amount of columns (row width) of the pixel in the image.
     * @param nY The amount of rows (column height) of the pixel in the image.
     * @param j  The column of the pixel in the image.
     * @param i  The row of the pixel in the image.
     * @return A construct ray.
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

    /**
     * If the image writer is null, throw an exception
     */
    private void checkImgWriter() {
        if (this.imageWriter == null)
            throw new MissingResourceException("Missing resource", ImageWriter.class.getName(), "");
    }

    /**
     * If the rayTracer is null, throw an exception
     */
    private void checkRayTracer() {
        if (this.rayTracer == null)
            throw new MissingResourceException("Missing resource", ImageWriter.class.getName(), "");
    }

    /**
     * Given a pixel's coordinates, construct a ray and trace it through the scene
     *
     * @param nX The amount of columns (row width) of the pixel in the image.
     * @param nY The amount of rows (column height) of the pixel in the image.
     * @param j  The column of the pixel in the image.
     * @param i  The row of the pixel in the image.
     * @return The color of the pixel.
     */
    private Color castRay(int nX, int nY, int j, int i) {
        Ray ray = this.constructRay(nX, nY, j, i);
        return this.rayTracer.traceRay(ray);
    }


    /**
     * For every pixel in the image, we cast a ray from the camera through the pixel, and then we color the pixel according
     * to the color of the closest intersection
     *
     * @return The camera itself.
     */
    public Camera renderImage() {
        try {
            this.checkImgWriter();
            this.checkRayTracer();
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Render didn't receive " + e.getClassName());
        }
        for (int i = 0; i < this.imageWriter.getNx(); i++)
            for (int j = 0; j < this.imageWriter.getNy(); j++) {
                //build for every pixel is
                Color pixelColor = this.castRay(this.imageWriter.getNx(), this.imageWriter.getNy(), j, i);
                this.imageWriter.writePixel(j, i, pixelColor);
            }

        return this;
    }

    /**
     * "Paint only the grid lines, leaving the background as it was."
     *
     * The function is called with two parameters:
     *
     * * interval - the interval between the grid lines
     * * color - the color of the grid lines
     *
     * The function is called on a Camera object, and returns the same Camera object
     *
     * @param interval the interval between the grid lines
     * @param color The color of the grid lines
     * @return The camera itself.
     */
    public Camera printGrid(int interval, Color color) {
        this.checkImgWriter();
        for (int i = 0; i < this.imageWriter.getNy(); i++)
            for (int j = 0; j < this.imageWriter.getNx(); j++) {
                //Paint only the grid lines
                //Leaves the background as it was
                if (i % interval == 0 || j % interval == 0)
                    this.imageWriter.writePixel(j, i, color);
            }

        return this;
    }

    /**
     * This function checks if the image writer is null, and if it is, it creates a new image writer. Then, it writes the
     * image to the image writer.
     *
     * @return The camera itself.
     */
    public Camera writeToImage() {
        this.checkImgWriter();
        this.imageWriter.writeToImage();
        return this;
    }
}