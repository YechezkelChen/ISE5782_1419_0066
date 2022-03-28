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
        int nX = 800, nY = 500;
        ImageWriter imageWriter = new ImageWriter("test", nX, nY);

        for (int i = 0; i < nX; i++)
            for (int j = 0; j < nY; j++) {
                // 800/16=50, 500/10=50
                // we do this calculates because we want to get 10x16 squares
                if (i % 50 == 0 || j % 50 == 0)
                    imageWriter.writePixel(i, j, Color.BLACK);
                else
                    imageWriter.writePixel(i, j, new Color(10, 1000, 10));
            }

        imageWriter.writeToImage();
    }

    @Test
    /**
     * Test method for {@link renderer.ImageWriter#writePixel(int, int, Color)}
     */
    void testWritePixel() {
    }
}