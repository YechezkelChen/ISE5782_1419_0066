import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import primitives.Vector;
import renderer.Camera;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for integration
 */
public class IntegrationTests {
    static final Point ZERO_POINT = new Point(0.0, 0.0, 0.0);
    Camera camera = new Camera(new Point(0.0, 0.0, 0.5),
            new Vector(0.0, 0.0, -1.0),
            new Vector(0.0, 1.0, 0.0))
            .setVPDistance(1)
            .setVPSize(3, 3);

    LinkedList<Ray> rayList = findRaysThroughVpPixels(camera, 3, 3);
    Sphere sphere;
    Plane plane;
    Triangle triangle;

    String sphereErrorMessage = "ERROR: Wrong number of intersections of camera rays with sphere";
    String planeErrorMessage = "ERROR: Wrong number of intersections of camera rays with plane";
    String triangleErrorMessage = "ERROR: Wrong number of intersections of camera rays with triangle";

    /**
     * Integration test for {@link geometries.Sphere}
     */
    @Test
    void testCameraSphereIntersections() {
        // **** Group: Sphere&Camera integration test cases ****//
        //TC01: the sphere is in front of the view plane(2).
        sphere = new Sphere(new Point(0.0, 0.0, -3.0), 1);
        assertEquals(2, countIntersections(rayList, sphere), sphereErrorMessage);

        //TC02: the view plane is inside the sphere, all rays should intersect twice(18).
        sphere = new Sphere(new Point(0.0, 0.0, -2.5), 2.5);
        assertEquals(18, countIntersections(rayList, sphere), sphereErrorMessage);

        //TC03: the view plane cross the sphere (10).
        sphere = new Sphere(new Point(0.0, 0.0, -2.0), 2);
        assertEquals(10, countIntersections(rayList, sphere), sphereErrorMessage);

        //TC04: the camera is inside the sphere,all rays should intersect only once(9).
        sphere = new Sphere(new Point(0.0, 0.0, -1.0), 4);
        assertEquals(9, countIntersections(rayList, sphere), sphereErrorMessage);

        //TC05: the sphere is behind the camera , no ray should intersect(0).
        sphere = new Sphere(new Point(0.0, 0.0, 1.0), 0.5);
        assertEquals(0, countIntersections(rayList, sphere), sphereErrorMessage);
    }

    /**
     * Integration test for {@link geometries.Plane}
     */
    @Test
    void testCameraPlaneIntersections() {
        // **** Group: Plane&Camera integration test cases ****//
        //TC01: the plane is parallel with the view plane, all rays should intersect(9).
        plane = new Plane(new Point(0.0, 0.0, -2.0), new Vector(0.0, 0.0, 1.0));
        assertEquals(9, countIntersections(rayList, plane), planeErrorMessage);

        //TC02: the plane is in front of the view plane and cross, all rays should intersect(9).
        plane = new Plane(new Point(0.0, 0.0, -1.5), new Vector(0.0, -0.5, 1.0));
        assertEquals(9, countIntersections(rayList, plane), planeErrorMessage);

        //TC03: the plane is above the view plane's third row (6).
        plane = new Plane(new Point(0.0, 0.0, -3.0), new Vector(0.0, -1.0, 1.0));
        assertEquals(6, countIntersections(rayList, plane), planeErrorMessage);
    }

    /**
     * Integration test for {@link geometries.Triangle}
     */
    @Test
    void testCameraTriangleIntersections() {
        //TC01:only the center ray should intersect(1).
        triangle = new Triangle(new Point(0.0, 1.0, -2.0), new Point(1.0, -1.0, -2.0), new Point(-1.0, -1.0, -2.0));
        assertEquals(1, countIntersections(rayList, triangle), triangleErrorMessage);

        //TC02: only the center ray and the top-middle ray should intersect(2).
        triangle = new Triangle(new Point(0.0, 20.0, -2.0), new Point(1.0, -1.0, -2.0), new Point(-1.0, -1.0, -2.0));
        assertEquals(2, countIntersections(rayList, triangle), triangleErrorMessage);
    }

    /**
     * Finds and sums up the number of camera rays that intersect with a given shape
     *
     * @param rayList
     * @param shape
     * @return Number of intersections of the camera rays with a given shape
     */
    private int countIntersections(LinkedList<Ray> rayList, Intersectable shape) {
        int counter = 0;
        for (Ray ray : rayList) {
            List<Point> result = shape.findIntersections(ray);
            if (result != null)
                counter += result.size();
        }
        return counter;
    }

    /**
     * Calculates with loop all the rays from camera through middle of each pixel
     *
     * @param camera
     * @param nX     number of pixels in x
     * @param nY     number of pixels in y
     * @return List of rays from camera through pixels
     */
    public LinkedList<Ray> findRaysThroughVpPixels(Camera camera, int nX, int nY) {
        LinkedList<Ray> raysList = new LinkedList<>();
        // For each pixel calls constructRay()
        for (int j = 0; j < nY; j++)
            for (int i = 0; i < nX; i++)
                raysList.add(camera.constructRay(nX, nY, j, i));

        return raysList;
    }
}
