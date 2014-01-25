/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cryprtolexo;

/**
 *
 * @author periklis
 */
public class Utils {
    
    public static int random(int A) {
        return (int)(Math.random() * A);
    }
    
    public static int random(int A, int B) {
        return (int)(Math.random() * (B-A)) + A;
    }
    
}
