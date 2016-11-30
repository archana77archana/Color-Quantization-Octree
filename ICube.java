/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quantization;

/**
 *
 * @author CB.EN.P2CSE16001
 */
public interface ICube {
    void classification();
    void reduction();
    void assignment();
    int[] colorMap();
    int[][] pixels();
}
