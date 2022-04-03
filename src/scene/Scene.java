package scene;

import lighting.*;
import geometries.*;
import primitives.*;

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

    public Scene(String name) {
        this.name = name;
        //this.geometries=new Geometries();        to ask Yair!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }

    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}
