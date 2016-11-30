/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quantization;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author casey
 */

public class Quantize {
    final boolean QUICK = true;
    
    final int MAX_RGB = 255;
    final int MAX_NODES = 266817;
    final int MAX_TREE_DEPTH = 8;

    // these are precomputed in advance
    int SQUARES[];
    int SHIFT[];

    Quantize() {
        SQUARES = new int[MAX_RGB + MAX_RGB + 1];
        for (int i= -MAX_RGB; i <= MAX_RGB; i++) {
            SQUARES[i + MAX_RGB] = i * i;
        }

        SHIFT = new int[MAX_TREE_DEPTH + 1];
        for (int i = 0; i < MAX_TREE_DEPTH + 1; ++i) {
            SHIFT[i] = 1 << (15 - i);
        }
    }

    /**
     * Reduce the image to the given number of colors. The pixels are
     * reduced in place.
     * @param pixels
     * @param max_colors
     * @return The new color palette.
     */
    public int[][] quantizeImage(int pixels[][], int max_colors) {
        Octree cube = new Octree(pixels, max_colors);
        cube.classification();
        cube.reduction();
        cube.assignment();
        return cube.result(cube.pixels, cube.colormap);
    }
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        //Image image =  new ImageIcon("large.jpg").getImage();
        
        BufferedImage hugeImage = ImageIO.read(Quantize.class.getResource("full-color.jpg"));
        ImageOperations i = new ImageOperations();
        
        int[][] result = i.convertTo2DUsingGetRGB(hugeImage);
        
        Quantize q = new Quantize();
        int res[][] = q.quantizeImage(result, q.MAX_RGB);

        i.writeImage(res.length, res[0].length, res);
        
    }
    
}
