import org.junit.jupiter.api.Test;

import static java.awt.Color.*;

import lighting.*;
import geometries.*;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

public class FirstImageTest {
    private Scene scene = new Scene("FirstImageTest scene");
    private Camera camera = new Camera(new Point(10.0, 1000.0, 0.0), new Vector(0.0, -5.0, 0.0), new Vector(0.0, 0.0, 10.0)) //
            .setVPSize(200, 200).setVPDistance(600) //
            .setRayTracer(new RayTracerBasic(scene));

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void FirstImg() {
        scene.geometries.add(
                new Triangle(new Point(-100.0, 0.0, 0.0), new Point(0.0, 100.0, 0.0), new Point(0.0, 0.0, -100.0)).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(new Double3(0.4)).setKs(new Double3(0.3)).setShininess(100).setKt(new Double3(5.0))),
                new Triangle(new Point(0.0, 100.0, 0.0), new Point(100.0, 0.0, 0.0), new Point(0.0, 0.0, -100.0)).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(new Double3(0.4)).setKs(new Double3(0.3)).setShininess(100).setKt(new Double3(5.0))),
                new Triangle(new Point(100.0, 0.0, 0.0), new Point(0.0, -100.0, 0.0), new Point(0.0, 0.0, -100.0)).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(new Double3(0.4)).setKs(new Double3(0.3)).setShininess(100).setKt(new Double3(5.0))),
                new Triangle(new Point(0.0, -100.0, 0.0), new Point(-100.0, 0.0, 0.0), new Point(0.0, 0.0, -100.0)).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(new Double3(0.4)).setKs(new Double3(0.3)).setShininess(100).setKt(new Double3(5.0))),

                new Sphere(new Point(0.0, 0.0, 50.0), 50).setEmission(new Color(YELLOW)) //
                        .setMaterial(new Material().setKd(new Double3(0.4)).setKs(new Double3(0.3)).setShininess(100).setKt(new Double3(0.3))),

                new Triangle(new Point(-50.0, -50.0, 100.0), new Point(50.0, -50.0, 100.0), new Point(0.0, 0.0, 150.0)).setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKd(new Double3(0.4)).setKs(new Double3(0.3)).setShininess(100).setKt(new Double3(5.0))),
                new Triangle(new Point(50.0, -50.0, 100.0), new Point(0.0, 75.0, 100.0), new Point(0.0, 0.0, 150.0)).setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKd(new Double3(0.4)).setKs(new Double3(0.3)).setShininess(100).setKt(new Double3(5.0))),
                new Triangle(new Point(0.0, 75.0, 100.0), new Point(-50.0, -50.0, 100.0), new Point(0.0, 0.0, 150.0)).setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKd(new Double3(0.4)).setKs(new Double3(0.3)).setShininess(100).setKt(new Double3(5.0)))
        );

        scene.lights.add( //
                new SpotLight(new Color(100, 150, 200), new Point(0.0, 100.0, 400.0), new Vector(-1.0, -1.0, -4.0)) //
                        .setKl(4E-4).setKq(2E-5));

        camera.setImageWriter(new ImageWriter("FirstImage", 600, 600)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void FirstImg2() {
        scene.geometries.add(
                new Sphere(new Point(30.0, 0.0, 0.0), 10).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(new Double3(0.4)).setKs(new Double3(0.3)).setShininess(100).setKt(new Double3(0.3))),
                new Sphere(new Point(30.0, 0.0, 0.0), 20).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKd(new Double3(0.4)).setKs(new Double3(0.3)).setShininess(100).setKt(new Double3(0.3))),
                new Sphere(new Point(-30.0, 0.0, 0.0), 10).setEmission(new Color(GREEN)) //
                        .setMaterial(new Material().setKd(new Double3(0.4)).setKs(new Double3(0.3)).setShininess(100).setKt(new Double3(0.3))),
                new Sphere(new Point(-30.0, 0.0, 0.0), 20).setEmission(new Color(YELLOW)) //
                        .setMaterial(new Material().setKd(new Double3(0.4)).setKs(new Double3(0.3)).setShininess(100).setKt(new Double3(0.3))),
                new Sphere(new Point(0.0, 0.0, 0.0), 80).setEmission(new Color(GRAY)) //
                        .setMaterial(new Material().setKd(new Double3(0.4)).setKs(new Double3(0.3)).setShininess(100).setKt(new Double3(0.3))),


                new Triangle(new Point(-80.0, 0.0, 30.0), new Point(-80.0, 0.0, -30.0), new Point(-100.0, 0.0, 0.0)).setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKd(new Double3(0.4)).setKs(new Double3(0.3)).setShininess(100).setKt(new Double3(5.0))),
                new Triangle(new Point(80.0, 0.0, 30.0), new Point(80.0, 0.0, -30.0), new Point(100.0, 0.0, 0.0)).setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKd(new Double3(0.4)).setKs(new Double3(0.3)).setShininess(100).setKt(new Double3(5.0))),
                new Triangle(new Point(50.0, -80.0, -40.0), new Point(-50.0, -80.0, -40.0), new Point(0.0, -80.0, -70.0)).setEmission(new Color(YELLOW))
                        .setMaterial(new Material().setKd(new Double3(0.4)).setKs(new Double3(0.3)).setShininess(100).setKt(new Double3(5.0)))
        );

        scene.lights.add( //
                new SpotLight(new Color(10, 10, 10), new Point(0.0, 100.0, 400.0), new Vector(-1.0, -1.0, -4.0)) //
                        .setKl(4E-4).setKq(2E-5));

        camera.setImageWriter(new ImageWriter("FirstImage2", 600, 600)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }
}