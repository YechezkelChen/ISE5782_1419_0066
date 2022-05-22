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

        Material triangleMat = new Material().setKt(new Double3(0.2));

        Intersectable
                Triangle1 = new Triangle(
                        new Point(-coordinate, -coordinate, -coordinate),
                        new Point(-coordinate, -coordinate, coordinate),
                        new Point(-coordinate, coordinate, coordinate))
                        .setEmission(pink)
                        .setMaterial(triangleMat),
                Triangle2 = new Triangle(
                        new Point(coordinate, coordinate, -coordinate),
                        new Point(-coordinate, -coordinate, -coordinate),
                        new Point(-coordinate, coordinate, -coordinate))
                        .setEmission(skyBlue)
                        .setMaterial(triangleMat),
                Triangle3 = new Triangle(
                        new Point(coordinate, -coordinate, coordinate),
                        new Point(-coordinate, -coordinate, -coordinate),
                        new Point(coordinate, -coordinate, -coordinate))
                        .setEmission(gold)
                        .setMaterial(triangleMat),
                Triangle4 = new Triangle(
                        new Point(coordinate, coordinate, -coordinate),
                        new Point(coordinate, -coordinate, -coordinate),
                        new Point(-coordinate, -coordinate, -coordinate))
                        .setEmission(peach)
                        .setMaterial(triangleMat),
                Triangle5 = new Triangle(
                        new Point(-coordinate, -coordinate, -coordinate),
                        new Point(-coordinate, coordinate, coordinate),
                        new Point(-coordinate, coordinate, -coordinate))
                        .setEmission(brightGreen)
                        .setMaterial(triangleMat),
                Triangle6 = new Triangle(
                        new Point(coordinate, -coordinate, coordinate),
                        new Point(-coordinate, -coordinate, coordinate),
                        new Point(-coordinate, -coordinate, -coordinate))
                        .setEmission(purple)
                        .setMaterial(triangleMat),
                Triangle7 = new Triangle(
                        new Point(-coordinate, coordinate, coordinate),
                        new Point(-coordinate, -coordinate, coordinate),
                        new Point(coordinate, -coordinate, coordinate))
                        .setEmission(strongPink)
                        .setMaterial(triangleMat),
                Triangle8 = new Triangle(
                        new Point(coordinate, coordinate, coordinate),
                        new Point(coordinate, -coordinate, -coordinate),
                        new Point(coordinate, coordinate, -coordinate))
                        .setEmission(brightYellow)
                        .setMaterial(triangleMat),
                Triangle9 = new Triangle(
                        new Point(coordinate, -coordinate, -coordinate),
                        new Point(coordinate, coordinate, coordinate),
                        new Point(coordinate, -coordinate, coordinate))
                        .setEmission(blue)
                        .setMaterial(triangleMat),
                Triangle10 = new Triangle(
                        new Point(coordinate, coordinate, coordinate),
                        new Point(coordinate, coordinate, -coordinate),
                        new Point(-coordinate, coordinate, -coordinate))
                        .setEmission(brightRed)
                        .setMaterial(triangleMat),
                Triangle11 = new Triangle(
                        new Point(coordinate, coordinate, coordinate),
                        new Point(-coordinate, coordinate, -coordinate),
                        new Point(-coordinate, coordinate, coordinate))
                        .setEmission(silver)
                        .setMaterial(triangleMat),
                Triangle12 = new Triangle(
                        new Point(coordinate, coordinate, coordinate),
                        new Point(-coordinate, coordinate, coordinate),
                        new Point(coordinate, -coordinate, coordinate))
                        .setEmission(brightOrange)
                        .setMaterial(triangleMat);


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
                        new Color(255, 255, 255),
                        new Point(0.0, 0.0, 0.0))
                        .setKl(4E-4).setKq(2E-5));
        scene.lights.add(
                new PointLight(
                        new Color(255, 255, 255),
                        new Point(0.0, 0.0, 0.0))
                        .setKl(4E-4).setKq(2E-5));
        scene.lights.add(
                new DirectionalLight(
                        new Color(300, 300, 300),
                        new Vector(1.0, -1.0, -2.0)));

        scene.background = new Color(0, 25, 51);

        camera1.setImageWriter(new ImageWriter("cube", 600, 600)) //
                .renderImage();
        camera1.writeToImage();
    }

}
