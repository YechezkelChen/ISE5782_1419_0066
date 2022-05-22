package renderer;

import org.junit.jupiter.api.Test;

import static java.awt.Color.*;

import lighting.*;
import geometries.*;
import primitives.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 */
public class ReflectionRefractionTests {
    private Scene scene = new Scene("Test scene");

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheres() {
        Camera camera = new Camera(new Point(0.0, 0.0, 1000.0), new Vector(0.0, 0.0, -1.0), new Vector(0.0, 1.0, 0.0)) //
                .setVPSize(150, 150).setVPDistance(1000);

        scene.geometries.add( //
                new Sphere(new Point(0.0, 0.0, -50.0), 50).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(new Double3(0.4)).setKs(new Double3(0.3)).setNShininess(100).setKt(new Double3(0.3))),
                new Sphere(new Point(0.0, 0.0, -50.0), 25).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setNShininess(100)));
        scene.lights.add( //
                new SpotLight(new Color(1000, 600, 0), new Point(-100.0, -100.0, 500.0), new Vector(-1.0, -1.0, -2.0)) //
                        .setKl(0.0004).setKq(0.0000006));

        camera.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheresOnMirrors() {
        Camera camera = new Camera(new Point(0.0, 0.0, 10000.0), new Vector(0.0, 0.0, -1.0), new Vector(0.0, 1.0, 0.0)) //
                .setVPSize(2500, 2500).setVPDistance(10000); //

        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));

        scene.geometries.add( //
                new Sphere(new Point(-950.0, -900.0, -1000.0), 400).setEmission(new Color(0, 0, 100)) //
                        .setMaterial(new Material().setKd(new Double3(0.25)).setKs(new Double3(0.25)).setNShininess(20).setKt(new Double3(0.5))),
                                new Sphere(new Point(-950.0, -900.0, -1000.0), 200).setEmission(new Color(100, 20, 20)) //
                                        .setMaterial(new Material().setKd(new Double3(0.25)).setKs(new Double3(0.25)).setNShininess(20)),
                                new Triangle(new Point(1500.0, -1500.0, -1500.0), new Point(-1500.0, 1500.0, -1500.0), new Point(670.0, 670.0, 3000.0)) //
                                        .setEmission(new Color(20, 20, 20)) //
                                        .setMaterial(new Material().setKr(new Double3(1))),
                                                new Triangle(new Point(1500.0, -1500.0, -1500.0), new Point(-1500.0, 1500.0, -1500.0),
                                                        new Point(-1500.0, -1500.0, -2000.0)) //
                                                        .setEmission(new Color(20, 20, 20)) //
                                                        .setMaterial(new Material().setKr(new Double3(0.5))));

        scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750.0, -750.0, -150.0), new Vector(-1.0, -1.0, -4.0)) //
                .setKl(0.00001).setKq(0.000005));

        ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a spot light with a partially
     * transparent Sphere producing partial shadow
     */
    @Test
    public void trianglesTransparentSphere() {
        Camera camera = new Camera(new Point(0.0, 0.0, 1000.0), new Vector(0.0, 0.0, -1.0), new Vector(0.0, 1.0, 0.0)) //
                .setVPSize(200, 200).setVPDistance(1000);

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

        scene.geometries.add( //
                new Triangle(new Point(-150.0, -150.0, -115.0), new Point(150.0, -150.0, -135.0), new Point(75.0, 75.0, -150.0)) //
                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setNShininess(60)), //
                new Triangle(new Point(-150.0, -150.0, -115.0), new Point(-70.0, 70.0, -140.0), new Point(75.0, 75.0, -150.0)) //
                        .setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setNShininess(60)), //
                new Sphere(new Point(60.0, 50.0, -50.0), 30d).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(new Double3(0.2)).setKs(new Double3(0.2)).setNShininess(30).setKt(new Double3(0.6))));

        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60.0, 50.0, 0.0), new Vector(0.0, 0.0, -1.0)) //
                .setKl(4E-5).setKq(2E-7));

        ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }
}
