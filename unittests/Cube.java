import org.junit.jupiter.api.Test;

import lighting.*;
import geometries.*;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

public class Cube {
    public static final double coordinate = 300.0;

    @Test
    public void cube() {
        Color pink = new Color(100, 75.3, 79.6);
        Color skyBlue = new Color(52.94, 80.78, 92.16);
        Color gold = new Color(255, 215, 0);
        Color peach = new Color(100, 79.61, 64.31);
        Color brightGreen = new Color(128, 255, 0);
        Color purple = new Color(153, 51, 255);
        Color strongPink = new Color(255, 0, 127);
        Color brightYellow = new Color(255, 255, 51);
        Color blue = new Color(51, 51, 255);
        Color brightRed = new Color(255, 102, 102);
        Color silver = new Color(192, 192, 192);
        Color brightOrange = new Color(255, 178, 102);
        Intersectable
                Triangle1 = new Triangle(
                        new Point(-coordinate, -coordinate, -coordinate),
                        new Point(-coordinate, -coordinate, coordinate),
                        new Point(-coordinate, coordinate, coordinate))
                .setEmission(pink),
                Triangle2 = new Triangle(
                        new Point(coordinate, coordinate, -coordinate),
                        new Point(-coordinate, -coordinate, -coordinate),
                        new Point(-coordinate, coordinate, -coordinate))
                        .setEmission(skyBlue),
                Triangle3 = new Triangle(
                        new Point(coordinate, -coordinate, coordinate),
                        new Point(-coordinate, -coordinate, -coordinate),
                        new Point(coordinate, -coordinate, -coordinate))
                        .setEmission(gold),
                Triangle4 = new Triangle(
                        new Point(coordinate, coordinate, -coordinate),
                        new Point(coordinate, -coordinate, -coordinate),
                        new Point(-coordinate, -coordinate, -coordinate))
                        .setEmission(peach),
                Triangle5 = new Triangle(
                        new Point(-coordinate, -coordinate, -coordinate),
                        new Point(-coordinate, coordinate, coordinate),
                        new Point(-coordinate, coordinate, -coordinate))
                        .setEmission(brightGreen),
                Triangle6 = new Triangle(
                        new Point(coordinate, -coordinate, coordinate),
                        new Point(-coordinate, -coordinate, coordinate),
                        new Point(-coordinate, -coordinate, -coordinate))
                        .setEmission(purple)
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setnShininess(30)),
                Triangle7 = new Triangle(
                        new Point(-coordinate, coordinate, coordinate),
                        new Point(-coordinate, -coordinate, coordinate),
                        new Point(coordinate, -coordinate, coordinate))
                        .setEmission(strongPink),
                Triangle8 = new Triangle(
                        new Point(coordinate, coordinate, coordinate),
                        new Point(coordinate, -coordinate, -coordinate),
                        new Point(coordinate, coordinate, -coordinate))
                        .setEmission(brightYellow),
                Triangle9 = new Triangle(
                        new Point(coordinate, -coordinate, -coordinate),
                        new Point(coordinate, coordinate, coordinate),
                        new Point(coordinate, -coordinate, coordinate))
                        .setEmission(blue),
                Triangle10 = new Triangle(
                        new Point(coordinate, coordinate, coordinate),
                        new Point(coordinate, coordinate, -coordinate),
                        new Point(-coordinate, coordinate, -coordinate))
                        .setEmission(brightRed),
                Triangle11 = new Triangle(
                        new Point(coordinate, coordinate, coordinate),
                        new Point(-coordinate, coordinate, -coordinate),
                        new Point(-coordinate, coordinate, coordinate))
                        .setEmission(silver),
                Triangle12 = new Triangle(
                        new Point(coordinate, coordinate, coordinate),
                        new Point(-coordinate, coordinate, coordinate),
                        new Point(coordinate, -coordinate, coordinate))
                        .setEmission(brightOrange);


        Scene scene = new Scene("Test scene cube");
        Camera camera1 = new Camera(
                new Point(-5900.0,-6000.0,6150.0),
                new Vector(1.0,1.0,-1.0),
                new Vector(1.0,1.0,2.0))
                .setVPSize(200, 200).setVPDistance(1500)
                .setRayTracer(new RayTracerBasic(scene));

        scene.geometries.add(Triangle1, Triangle2, Triangle3, Triangle4, Triangle5, Triangle6,
                Triangle7, Triangle8, Triangle9, Triangle10, Triangle11, Triangle12);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.01)));
        scene.lights.add(
                new PointLight(
                        new Color(1800, 1600, 1600),
                        new Point(-400.0, -150.0, 350.0))
                        .setKl(4E-4).setKq(2E-5));
        scene.lights.add(
                new PointLight(
                        new Color(1800, 1600, 1600),
                        new Point(150.0, -300.0, 750.0))
                        .setKl(4E-4).setKq(2E-5));
        scene.lights.add(
                new DirectionalLight(
                        new Color(1200, 1200, 1200),
                        new Vector(1.0, -1.0, -2.0)));

        scene.background = new Color(0, 25, 51);

        camera1.setImageWriter(new ImageWriter("cube", 600, 600)) //
                .renderImage();
        camera1.writeToImage();
    }

}
