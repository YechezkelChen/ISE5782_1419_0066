package renderer;

import org.junit.jupiter.api.Test;

import static java.awt.Color.*;

import lighting.*;
import geometries.*;
import primitives.*;
import scene.Scene;

/**
 * Testing basic shadows
 * 
 * @author Dan
 */
public class ShadowTests {
	private Intersectable sphere = new Sphere(new Point(0.0, 0.0, -200.0), 60d) //
			.setEmission(new Color(BLUE)) //
			.setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.50)).setNShininess(30));
	private Material trMaterial = new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setNShininess(30);

	private Scene scene = new Scene("Test scene");
	private Camera camera = new Camera(new Point(0.0, 0.0, 1000.0), new Vector(0.0, 0.0, -1.0), new Vector(0.0, 1.0, 0.0)) //
			.setVPSize(200, 200).setVPDistance(1000) //
			.setRayTracer(new RayTracerBasic(scene));

	/**
	 * Helper function for the tests in this module
	 */
	void sphereTriangleHelper(String pictName, Triangle triangle, Point spotLocation) {
		scene.geometries.add(sphere, triangle.setEmission(new Color(BLUE)).setMaterial(trMaterial));
		scene.lights.add( //
				new SpotLight(new Color(400, 240, 0), spotLocation, new Vector(1.0, 1.0, -3.0)) //
						.setKl(1E-5).setKq(1.5E-7));
		camera.setImageWriter(new ImageWriter(pictName, 400, 400)) //
				.renderImage() //
				.writeToImage();
	}

	/**
	 * Produce a picture of a sphere and triangle with point light and shade
	 */
	@Test
	public void sphereTriangleInitial() {
		sphereTriangleHelper("shadowSphereTriangleInitial", //
				new Triangle(new Point(-70.0, -40.0, 0.0), new Point(-40.0, -70.0, 0.0), new Point(-68.0, -68.0, -4.0)), //
				new Point(-100.0, -100.0, 200.0));
	}

	/**
	 * Sphere-Triangle shading - move triangle up-right
	 */
	@Test
	public void sphereTriangleMove1() {
		sphereTriangleHelper("shadowSphereTriangleMove2", //
				new Triangle(new Point(-62.0, -32.0, 0.0), new Point(-32.0, -62.0, 0.0), new Point(-60.0, -60.0, -4.0)), //
				new Point(-100.0, -100.0, 200.0));
	}

	/**
	 * Sphere-Triangle shading - move triangle upper-righter
	 */
	@Test
	public void sphereTriangleMove2() {
		sphereTriangleHelper("shadowSphereTriangleMove1", //
				new Triangle(new Point(-49.0, -19.0, 0.0), new Point(-19.0, -49.0, 0.0), new Point(-47.0, -47.0, -4.0)), //
				new Point(-100.0, -100.0, 200.0));
	}

	/**
	 * Sphere-Triangle shading - move spot closer
	 */
	@Test
	public void sphereTriangleSpot1() {
		sphereTriangleHelper("shadowSphereTriangleSpot1", //
				new Triangle(new Point(-70.0, -40.0, 0.0), new Point(-40.0, -70.0, 0.0), new Point(-68.0, -68.0, -4.0)), //
				new Point(-88.0, -88.0, 120.0));
	}

	/**
	 * Sphere-Triangle shading - move spot even more close
	 */
	@Test
	public void sphereTriangleSpot2() {
		sphereTriangleHelper("shadowSphereTriangleSpot2", //
				new Triangle(new Point(-70.0, -40.0, 0.0), new Point(-40.0, -70.0, 0.0), new Point(-68.0, -68.0, -4.0)), //
				new Point(-76.0, -76.0, 70.0));
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light with a Sphere
	 * producing a shading
	 */
	@Test
	public void trianglesSphere() {
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.15)));

		scene.geometries.add( //
				new Triangle(new Point(-150.0, -150.0, -115.0), new Point(150.0, -150.0, -135.0), new Point(75.0, 75.0, -150.0)) //
						.setMaterial(new Material().setKs(new Double3(0.8)).setNShininess(60)), //
				new Triangle(new Point(-150.0, -150.0, -115.0), new Point(-70.0, 70.0, -140.0), new Point(75.0, 75.0, -150.0)) //
						.setMaterial(new Material().setKs(new Double3(0.8)).setNShininess(60)), //
				new Sphere(new Point(0.0, 0.0, -11.0), 30d) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKd(new Double3(0.5)).setKs(new Double3(0.5)).setNShininess(30)) //
		);
		scene.lights.add( //
				new SpotLight(new Color(700, 400, 400), new Point(40.0, 40.0, 115.0), new Vector(-1.0, -1.0, -4.0)) //
						.setKl(4E-4).setKq(2E-5));

		camera.setImageWriter(new ImageWriter("shadowTrianglesSphere", 600, 600)) //
				.renderImage() //
				.writeToImage();
	}

}
