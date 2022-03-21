import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import renderer.Camera;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * Unit tests for integration
 *
 */
public class IntegrationTests {
    static final Point ZERO_POINT = new Point(0.0, 0.0, 0.0);

    /**
     * Integration test for {@link geometries.Sphere}
     */
    @Test
    void testCameraSphereIntersections() {
        Camera camera = new Camera(ZERO_POINT, new Vector(0.0, 0.0, -1.0), new Vector(0.0, -1.0, 0.0))
                .setVPDistance(1).setVPSize(3, 3);
        String badRay = "Bad ray";

        // 01: 3X3 Center (1,1)
        assertEquals(new Ray(ZERO_POINT, new Vector(0.0, 0.0, -10.0)),
                camera.setVPSize(6, 6).constructRay(3, 3, 1, 1), badRay);
    }

    /**
     * Integration test for {@link geometries.Plane}
     */
    @Test
    void testCameraPlaneIntersections() {

    }

    /**
     * Integration test for {@link geometries.Triangle}
     */
    @Test
    void testCameraTriangleIntersections() {

    }
}
