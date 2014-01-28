/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Cryptolexo;

import static Cryptolexo.ArrayHelpers.addHorizontalWord;
import static Cryptolexo.ArrayHelpers.addVerticalWord;
import static Cryptolexo.ArrayHelpers.getRowInColWithCollision;
import static Cryptolexo.ArrayHelpers.getColInRowWithCollision;
import static Cryptolexo.ArrayHelpers.isColEmpty;
import static Cryptolexo.ArrayHelpers.isRowEmpty;
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author periklis
 */
public class Cryptolexo {
    
    /*
    NxM array
        MMMMMMMMMMMMM   
    N  |-------------|
    N  |             |
    N  |             |
    N  |             |
    N  |-------------|
    
    */
    private int N,M;
    private String[][] cryptolexoBase;
    private String[][] cryptolexo;
    private int nwords = 5;
    private String[] words;
    
    public Cryptolexo(int N, int M, int nwords) {
        this.N = N;
        this.M = M;
        this.nwords = nwords;
        cryptolexoBase = new String[N][M];
        cryptolexo = new String[N][M];
        createCryptolexo();
    }
    
    public Cryptolexo(int N, int nwords) {
        this(N, N, nwords);
    }
    
    public String[] getWords() {
        return words;
    }
    
    public String[][] getCryptolexo() {
        return cryptolexo;
    }
    
    private void createCryptolexo() {
        words = WordList.getWords(nwords, Math.max(N,M));
        Arrays.sort(words, new CompareStringLength());
        
        int w = 0;
        while(w<nwords) {
            int tries = 0;
            while(tries<3) {
                tries++;
                if(addWordRandomly(words[w])) {
                    tries = 3;
                    //w++;
                } else {
                    // try to fill it!
                }
            }
            w++;
        }
        createFilledCryptolexo();
    }
    
    private boolean addWordRandomly(String word) {
        if(Utils.random(2) == 0) {
            // Vertical
            int col = Utils.random(M);
            if(isColEmpty(cryptolexoBase, col)) {
                int row = Utils.random(N-word.length());
                addVerticalWord(cryptolexoBase, row,col,word);
                return true;
            } else {
                //TRY TO FILL THE SPACES - FIRST AVAILABLE WILL DO
                int row = getRowInColWithCollision(cryptolexoBase, word, col);
                if(row >= 0) {
                    addVerticalWord(cryptolexoBase, row,col,word);
                    return true;
                }
            }
        } else {
            // Horizontal
            int row = Utils.random(N);
            if(isRowEmpty(cryptolexoBase, row)) {
                int col = Utils.random(M-word.length());
                addHorizontalWord(cryptolexoBase, row,col,word);
                return true;
            } else {
                //TRY TO FILL THE SPACES - FIRST AVAILABLE WILL DO
                int col = getColInRowWithCollision(cryptolexoBase, word, row);
                if(col >= 0) {
                    addHorizontalWord(cryptolexoBase, row,col,word);
                    return true;
                }
            }
        }
        return false;
    }
    
    public void printCryptolexo() {
        printCryptolexo(PrintTypes.RANDOM);
    }
    
    private void createFilledCryptolexo() {
        for(int i=0;i<cryptolexoBase.length;i++) {
            for(int j=0;j<cryptolexoBase[0].length;j++){
                if(cryptolexoBase[i][j] == null) {
                    cryptolexo[i][j] = String.valueOf((char)Utils.random(65, 91));
                } else {
                    cryptolexo[i][j] = cryptolexoBase[i][j];
                }
            }
        }
    }
    
    private void printCryptolexo(PrintTypes pt) {
        for(int i=0;i<cryptolexoBase.length;i++) {
            for(int j=0;j<cryptolexoBase[0].length;j++){
                if(cryptolexoBase[i][j] == null) {
                    switch(pt) {
                        case EMPTY: System.out.print(" ");break;
                        case RANDOM: System.out.print(cryptolexo[i][j]);break;
                        case NUMBER: System.out.print(j);break;
                    }
                } else {
                    System.out.print(cryptolexoBase[i][j]);
                }
            }
            System.out.println();
        }        
    }
    
    private enum PrintTypes {
        EMPTY, RANDOM, NUMBER
    }
    
    private class CompareStringLength implements Comparator<String> {
        // bigger words come first in sort

        @Override
        public int compare(String t, String t1) {
            if (t.length() < t1.length()) {
                return 1;
            } else if  (t.length() > t1.length()) {
                return -1;
            } else {
                return 0;
            }
        }
        
    }
    
}
