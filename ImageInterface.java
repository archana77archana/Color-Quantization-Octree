/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quantization;

import java.awt.image.BufferedImage;

/**
 *
 * @author CB.EN.P2CSE16001
 */
public interface ImageInterface {
    int[][] convertTo2DUsingGetRGB(BufferedImage image);
    void writeImage(int r, int c, int[][] res);
}
