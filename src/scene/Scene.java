package scene;

import lighting.*;
import geometries.*;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

public class Scene {
    /**
     * Declaring a variable called `name` that is a `String` type.
     */
    public String name;
    /**
     * Setting the default value of the background to black.
     */
    public Color background = Color.BLACK;
    /**
     * Creating a new instance of the AmbientLight class and setting it to the `ambientLight` variable.
     */
    public AmbientLight ambientLight = new AmbientLight(); // the default constructor is black.
    /**
     * This is creating a new instance of the Geometries class and setting it to the `geometries` variable.
     */
    public Geometries geometries = new Geometries();

    /**
     * Creating a new instance of the LinkedList class and setting it to the `lights` variable.
     */
    public List<LightSource> lights = new LinkedList<>();

    /**
     * This is a constructor that sets the name of the scene to the given name.
      */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * This function sets the background color of the scene and returns the scene.
     *
     * @param background The background color of the scene.
     * @return The Scene object itself.
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * Sets the ambient light of the scene.
     *
     * @param ambientLight The ambient light of the scene.
     * @return The Scene object itself.
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * This function sets the geometries of the scene to the given geometries.
     *
     * @param geometries The geometries of the scene.
     * @return The scene itself.
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * This function sets the lights of the scene to the given list of lights and returns the scene.
     *
     * @param lights A list of LightSource objects.
     * @return The Scene object itself.
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }
}
