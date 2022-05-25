package renderer;

import primitives.*;
import primitives.Vector;

import java.util.*;

import static primitives.Util.*;


/**
 * Camera class represents the camera and her distance form the view plane
 */
public class Camera {

    /**
     * The location of the camera.
      */
    private Point p0;

    /**
     * A vector that points to the direction the camera is looking at.
     */
    private Vector vTo;

    /**
     * A vector that points up.
     */
    private Vector vUp;

    /**
     * The vector that points to the right.
     */
    private Vector vRight;

    /**
     * A private variable that is used to store the width of the view plane.
     */
    private double width;

    /**
     * A private variable that is used to store the height of the view plane.
     */
    private double height;

    /**
     * The distance from the camera to the view plane.
     */
    private double distance;

    /**
     * A private variable that is used to store the image writer.
     */
    private ImageWriter imageWriter;

    /**
     * A pointer to the ray tracer that will be used to trace the rays.
     */
    private RayTracerBase rayTracer;

    /**
     * A boolean variable that determines whether to use anti-aliasing.
      */
    private boolean antiAliasing = false;

    /**
     * It's a variable that is used to store the size of the grid.
      */
    private int gridSize;

    /**
     * This function returns the value of the private variable p0.
     *
     * @return The point p0.
     */
    public Point getP0() {
        return p0;
    }

    /**
     * This function returns the vector to the destination.
     *
     * @return The vector vTo is being returned.
     */
    public Vector getVTo() {
        return vTo;
    }

    /**
     * This function returns the value of the vUp variable.
     *
     * @return The vector vUp is being returned.
     */
    public Vector getVUp() {
        return vUp;
    }

    /**
     * This function returns the value of the vRight variable.
     *
     * @return The vector vRight is being returned.
     */
    public Vector getVRight() {
        return vRight;
    }

    /**
     * This function returns the width of the rectangle.
     *
     * @return The width of the rectangle.
     */
    public double getWidth() {
        return width;
    }

    /**
     * This function returns the height of the rectangle.
     *
     * @return The height of the rectangle.
     */
    public double getHeight() {
        return height;
    }

    /**
     * This function returns the distance between the current location and the destination.
     *
     * @return The distance between the two points.
     */
    public double getDistance() {
        return distance;
    }

    /**
     * > Returns whether or not anti-aliasing is enabled
     *
     * @return The boolean value of the variable antiAliasing.
     */
    public boolean isAntiAliasing() {
        return antiAliasing;
    }

    /**
     * This function returns the gridSize variable.
     *
     * @return The gridSize variable is being returned.
     */
    public int getGridSize() {
        return gridSize;
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
     * This function sets the antiAliasing variable to the value of the parameter antiAliasing and returns the camera
     * object.
     *
     * @param antiAliasing Whether or not to use anti-aliasing.
     * @return The camera object itself.
     */
    public Camera setAntiAliasing(boolean antiAliasing) {
        this.antiAliasing = antiAliasing;
        return this;
    }

    /**
     * > Sets the grid size of the camera
     *
     * @param gridSize The size of the grid in pixels.
     * @return The camera object itself.
     */
    public Camera setGridSize(int gridSize) {
        this.gridSize = gridSize;
        return this;
    }

    /**
     * The function receives a boolean value that determines whether or not to use anti-aliasing. If anti-aliasing is
     * enabled, the function will call the `fragmentPixelToGrid` function, which will return a color value for the pixel.
     * If anti-aliasing is disabled, the function will call the `castRay` function, which will return a color value for the
     * pixel
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
        Color pixelColor;

        for (int i = 0; i < this.imageWriter.getNx(); i++)
            for (int j = 0; j < this.imageWriter.getNy(); j++) {
                if(antiAliasing)
                    pixelColor = this.fragmentPixelToGrid(i, j);
                else
                    pixelColor = this.castRay(this.imageWriter.getNx(), this.imageWriter.getNy(), j, i);

                this.imageWriter.writePixel(j, i, pixelColor);
            }

        return this;
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
     * It takes a pixel and divides it into a grid of smaller pixels, and then casts a ray through each of the smaller
     * pixels and averages the color of the smaller pixels to get the color of the original pixel
     *
     * @param i the x coordinate of the pixel
     * @param j the x coordinate of the pixel
     * @return The color of the pixel.
     */
    private Color fragmentPixelToGrid(int i, int j) {
        double grid = 1.0 / this.gridSize;
        Color pixelColor = Color.BLACK;

        for (float fragmentI = i; fragmentI < i + 1.0f; fragmentI += grid)
            for (float fragmentJ = j; fragmentJ < j + 1.0f; fragmentJ += grid)
                pixelColor = pixelColor.add(this.castRay(this.imageWriter.getNx(), this.imageWriter.getNy(), fragmentJ, fragmentI));

        return pixelColor.reduce(this.gridSize*this.gridSize);
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
    private Color castRay(int nX, int nY, float j, float i) {
        Ray ray = this.constructRay(nX, nY, j, i);
        return this.rayTracer.traceRay(ray);
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
    public Ray constructRay(int nX, int nY, float j, float i) {
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