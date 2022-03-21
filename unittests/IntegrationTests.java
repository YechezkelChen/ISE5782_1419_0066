import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import renderer.Camera;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for integration
 */
public class IntegrationTests {
    static final Point ZERO_POINT = new Point(0.0, 0.0, 0.0);

    /**
     * Integration test for {@link geometries.Sphere}
     */
    @Test
    void testCameraSphereIntersections() {
        Sphere[] spheres = new Sphere[]{
                //TC01: the sphere is in front of the view plane(2).
                new Sphere(new Point(0.0, 0.0, -3.0), 1),
                //TC02: the view plane is inside the sphere, all rays should intersect twice(18).
                new Sphere(new Point(0.0, 0.0, -2.5), 2.5),
                //TC03: the view plane cross the sphere (10).
                new Sphere(new Point(0.0, 0.0, -2.0), 2),
                //TC04: the camera is inside the sphere,all rays should intersect only once(9).
                new Sphere(new Point(0.0, 0.0, -1.0), 4),
                //TC05: the sphere is behind the camera , no ray should intersect(0).
                new Sphere(new Point(0.0, 0.0, 1.0), 0.5)
        };

        int[] numOfIntersectionsExpected = new int[]{2, 18, 10, 9, 0};
        testIntersectsAndCamera(spheres, numOfIntersectionsExpected);
    }

    /**
     * Integration test for {@link geometries.Plane}
     */
    @Test
    void testCameraPlaneIntersections() {
        Plane[] planes = new Plane[]{
                //TC01: the plane is parallel with the view plane, all rays should intersect(9).
                new Plane(new Point(0.0, 0.0, -2.0), new Vector(0.0, 0.0, 1.0)),
                //TC02: the plane is in front of the view plane and cross, all rays should intersect(9).
                new Plane(new Point(0.0, 0.0, -1.5), new Vector(0.0, -0.5, 1.0)),
                //TC03: the plane is above the view plane's third row (6).
                new Plane(new Point(0.0, 0.0, -3.0), new Vector(0.0, -1.0, 1.0))
        };
        int[] numOfIntersectionsExpected = new int[]{9, 9, 6};
        testIntersectsAndCamera(planes, numOfIntersectionsExpected);
    }

    /**
     * Integration test for {@link geometries.Triangle}
     */
    @Test
    void testCameraTriangleIntersections() {
        Triangle[] triangles = new Triangle[]{
                //TC01:only the center ray should intersect(1).
                new Triangle(new Point(0.0, 1.0, -2.0), new Point(1.0, -1.0, -2.0), new Point(-1.0, -1.0, -2.0)),
                //TC02: only the center ray and the top-middle ray should intersect(2).
                new Triangle(new Point(0.0, 20.0, -2.0), new Point(1.0, -1.0, -2.0), new Point(-1.0, -1.0, -2.0))
        };

        int[] numOfIntersectionsExpected = new int[]{1, 2};
        testIntersectsAndCamera(triangles, numOfIntersectionsExpected);
    }

    /**
     * Given a list of intersectables, a camera, and a list of expected number of intersections,
     * this function checks the number of intersections for each intersectable and asserts the number of intersections
     *
     * @param intersectables        an array of intersectables
     * @param expectedIntersections an array of integers, where each integer is the number of intersections expected for
     *                              the corresponding intersectable.
     */
    private void testIntersectsAndCamera(Intersectable[] intersectables, int[] expectedIntersections) {
        int nX = 3, nY = 3;
        Camera cam = new Camera(new Point(0.0, 0.0, 0.5), new Vector(0.0, 0.0, -1.0), new Vector(0.0, 1.0, 0.0)).setVPDistance(1).setVPSize(3, 3);

        List<List<Point>> intersections = new ArrayList<>(Collections.nCopies(intersectables.length, null));

        for (int i = 0; i < nY; ++i) {
            for (int j = 0; j < nX; ++j) {

                Ray pixelRay = cam.constructRay(nX, nY, j, i);
                for (int id = 0; id < intersectables.length; id++) {
                    List<Point> list = intersectables[id].findIntersections(pixelRay);

                    if (list == null) {
                        continue;
                    }
                    if (intersections.get(id) == null) {
                        intersections.set(id, new ArrayList<>());
                    }

                    intersections.get(id).addAll(list);
                }

            }

        }

        // checking each intersectable to assert the number of intersections.
        for (int id = 0; id < intersectables.length; id++) {
            int sumOfIntersection = 0;

            if (intersections.get(id) != null) {
                sumOfIntersection = intersections.get(id).size();
            }
            assertEquals(sumOfIntersection, expectedIntersections[id], "Wrong number of intersectables");
        }

    }

}
