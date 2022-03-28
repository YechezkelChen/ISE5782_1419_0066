package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {

    @Test
    /**
     * Test method for {@link renderer.ImageWriter#writeToImage()}
     */
    void testWriteToImage() {
        ImageWriter image = new ImageWriter("test", 500, 800);

    }

    @Test
    /**
     * Test method for {@link renderer.ImageWriter#writePixel(int, int, Color)}
     */
    void testWritePixel() {
    }
}