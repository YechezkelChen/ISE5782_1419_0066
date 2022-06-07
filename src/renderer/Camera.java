package renderer;

import geometries.Intersectable;
import geometries.Plane;
import primitives.*;
import primitives.Vector;

import java.util.*;
import java.util.stream.IntStream;

import static java.lang.Math.sqrt;
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


    /** Anti Aliasing properties. **/

    /**
     * A boolean variable that determines whether to use anti-aliasing.
     */
    private boolean antiAliasing = false;

    /**
     * It's a variable that is used to store the size of the grid.
     */
    private int gridSize = 3;


    /** Depth Of Filed properties. **/

    /**
     * A boolean variable that determines whether to use depth of filed.
     */
    private boolean depthOfFiled = false;

    /** Aperture properties. **/

    /**
     * number with integer square for the matrix of points.
     */
    private int APERTURE_NUMBER_OF_POINTS = 100;

    /**
     * Declaring a variable called apertureSize of type double.
     */
    private double apertureSize;

    /**
     * Creating an array of Point objects.
     */
    private Point[] aperturePoints;

    /** Focal plane parameters. **/

    /**
     * as instructed it is a constant value of the class.
     */
    private double FP_distance;

    /**
     * Declaring a variable called FOCAL_PLANE of type Plane.
     */
    private Plane FOCAL_PLANE;

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

        this.apertureSize = 0; //initialize DoF parameters.
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
     * The function iterates over the pixels of the image, and for each pixel it casts a ray through the center of the
     * pixel and checks if the ray intersects with any of the scene's geometries. If it does, it calculates the color of
     * the pixel
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

        final int nX = this.imageWriter.getNx();
        final int nY = this.imageWriter.getNy();

        for (int i = 0; i < nX; i++)
            for (int j = 0; j < nY; j++) {
                if (antiAliasing && adaptiveGrid(nX, nY, j, i))
                    this.imageWriter.writePixel(j, i, this.fragmentPixelToGrid(i, j));
                else
                    this.imageWriter.writePixel(j, i, this.castRay(this.imageWriter.getNx(), this.imageWriter.getNy(), j, i));
            }

        return this;
    }

    /**
     * We're going to iterate over the pixels in the image, and for each pixel we're going to create thread and cast a ray
     * from the camera through the pixel, and then we're going to check if the ray intersects with any of the objects in
     * the scene. If it does, we're going to calculate the color of the pixel based on the intersection point. If it
     * doesn't, we're going to set the color of the pixel to the background color
     *
     * @return The camera itself.
     */
    public Camera renderImageThreaded() {
        try {
            this.checkImgWriter();
            this.checkRayTracer();
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Render didn't receive " + e.getClassName());
        }

        final int nX = this.imageWriter.getNx();
        final int nY = this.imageWriter.getNy();

        IntStream.range(0, nX).parallel().forEach(i -> {
            IntStream.range(0, nY).parallel().forEach(j -> {
                if (antiAliasing && adaptiveGrid(nX, nY, j, i))
                    this.imageWriter.writePixel(j, i, this.fragmentPixelToGrid(i, j));
                else
                    this.imageWriter.writePixel(j, i, this.castRay(this.imageWriter.getNx(), this.imageWriter.getNy(), j, i));
            });
        });

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
     * The function checks if the color of the pixel is the same as the color of the pixel in the top left, top right,
     * bottom left and bottom right corners of the pixel
     *
     * @param nX number of pixels in the view plane
     * @param nY number of pixels in the view plane
     * @param j  the current column
     * @param i  the row of the pixel
     * @return a boolean value.
     */
    private boolean adaptiveGrid(int nX, int nY, int j, int i) {

        Point pc = this.p0.add(this.vTo.scale(this.distance));
        Point pij = getCenterPoint(nX, nY, j, i, pc);
        Color topLeft, topRight, bottomLeft, bottomRight;
        double ry = alignZero(this.height / nY);
        double rx = alignZero(this.width / nX);

        topLeft = castRay((int) (pij.getX() + -rx / 2), (int) (pij.getY() + ry / 2), j, i);
        topRight = castRay((int) (pij.getX() + rx / 2), (int) (pij.getY() + ry / 2), j, i);
        bottomLeft = castRay((int) (pij.getX() + -rx / 2), (int) (pij.getY() + -ry / 2), j, i);
        bottomRight = castRay((int) (pij.getX() + rx / 2), (int) (pij.getY() + -ry / 2), j, i);
        return topLeft.equals(topRight) && topLeft.equals(bottomLeft) && topLeft.equals(bottomRight);
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
        if (depthOfFiled) // if there is the improvement of depth of filed
            return averagedBeamColor(ray);

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

        Point pIJ = getCenterPoint(nX, nY, j, i, Pc);
        Vector vIJ = pIJ.subtract(this.p0);

        return new Ray(this.p0, vIJ);
    }

    /**
     * > Given the pixel's coordinates, the number of pixels in the image, and the camera's center point, return the
     * pixel's center point
     *
     * @param nX number of pixels in the horizontal direction
     * @param nY number of pixels in the vertical direction
     * @param j  the x-coordinate of the pixel in the image
     * @param i  the row of the pixel
     * @param pc The center of the image plane
     * @return The center point of the pixel.
     */
    private Point getCenterPoint(int nX, int nY, float j, float i, Point pc) {
        //Ratio (pixel width & height)
        double Ry = this.height / nY;
        double Rx = this.width / nX;

        //Pixel[i,j] center
        double yI = -(i - (nY - 1) / 2.0) * Ry;
        double xJ = (j - (nX - 1) / 2.0) * Rx;

        Point pIJ = pc;
        if (xJ != 0) pIJ = pIJ.add(vRight.scale(xJ));
        if (yI != 0) pIJ = pIJ.add(vUp.scale(yI));

        return pIJ;
    }

    /**
     * "Paint only the grid lines, leaving the background as it was."
     * <p>
     * The function is called with two parameters:
     * <p>
     * * interval - the interval between the grid lines
     * * color - the color of the grid lines
     * <p>
     * The function is called on a Camera object, and returns the same Camera object
     *
     * @param interval the interval between the grid lines
     * @param color    The color of the grid lines
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

    /** Anti Aliasing improvements **/

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

        return pixelColor.reduce(this.gridSize * this.gridSize);
    }


    /** Depth Of Filed improvements **/

    /**
     * This function sets the depth of field to the value of the parameter.
     *
     * @param depthOfFiled If true, the camera will have a depth of field effect.
     */
    public Camera setDepthOfFiled(boolean depthOfFiled) {
        this.depthOfFiled = depthOfFiled;
        return this;
    }

    /**
     * The function sets the distance of the focal plane from the camera's position
     *
     * @param distance The distance from the camera to the focal plane.
     * @return The camera itself.
     */
    public Camera setFPDistance(double distance) {
        this.FP_distance = distance;
        this.FOCAL_PLANE = new Plane(this.p0.add(this.vTo.scale(FP_distance)), this.vTo);
        return this;
    }

    /**
     * This function sets the aperture size of the camera and initializes the points of the aperture.
     *
     * @param size the size of the aperture.
     * @return The camera object itself.
     */
    public Camera setApertureSize(double size) {
        this.apertureSize = size;

        //initializing the points of the aperture.
        if (size != 0) initializeAperturePoint();

        return this;
    }

    /**
     * The function initializes the aperture points array by calculating the distance between the points and the initial
     * point, and then initializing the array with the points
     */
    private void initializeAperturePoint() {
        //the number of points in a row
        int pointsInRow = (int) sqrt(this.APERTURE_NUMBER_OF_POINTS);

        //the array of point saved as an array
        this.aperturePoints = new Point[pointsInRow * pointsInRow];

        //calculating the initial values.
        double pointsDistance = (this.apertureSize * 2) / pointsInRow;
        //calculate the initial point to be the point with coordinates outside the aperture in the down left point, so we won`t have to deal with illegal vectors.
        Point initialPoint = this.p0
                .add(this.vUp.scale(-this.apertureSize - pointsDistance / 2)
                        .add(this.vRight.scale(-this.apertureSize - pointsDistance / 2)));

        //initializing the points array
        for (int i = 1; i <= pointsInRow; i++) {
            for (int j = 1; j <= pointsInRow; j++) {
                this.aperturePoints[(i - 1) + (j - 1) * pointsInRow] = initialPoint
                        .add(this.vUp.scale(i * pointsDistance).add(this.vRight.scale(j * pointsDistance)));
            }
        }
    }

    /**
     * It takes a ray, finds the point where it intersects the focal plane, and then shoots rays from the aperture points
     * to that point. It then averages the colors of all the rays
     *
     * @param ray The ray that is being traced.
     * @return The average color of the image.
     */
    private Color averagedBeamColor(Ray ray) {
        Color averageColor = Color.BLACK, apertureColor;
        int numOfPoints = this.aperturePoints.length;
        Ray apertureRay;
        Point focalPoint = this.FOCAL_PLANE.findGeoIntersections(ray).get(0).point;
        for (Point aperturePoint : this.aperturePoints) {
            apertureRay = new Ray(aperturePoint, focalPoint.subtract(aperturePoint));
            apertureColor = rayTracer.traceRay(apertureRay);
            averageColor = averageColor.add(apertureColor.reduce(numOfPoints));
        }
        return averageColor;
    }
}