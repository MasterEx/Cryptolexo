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
public class Cryprtolexo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Cryptolexo crypt = new Cryptolexo(12);
        crypt.createCryptolexo();
        crypt.getCryptolexo();
    }
    
}
