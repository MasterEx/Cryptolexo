/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Cryptolexo;

import static Cryptolexo.ArrayHelpers.addHorizontalWord;
import static Cryptolexo.ArrayHelpers.addVerticalWord;
import static Cryptolexo.ArrayHelpers.getColEmptySpace;
import static Cryptolexo.ArrayHelpers.getColWithCollision;
import static Cryptolexo.ArrayHelpers.getRowEmptySpace;
import static Cryptolexo.ArrayHelpers.getRowWithCollision;
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
    private String[][] cryptolexo;
    private int nwords = 5; //TODO get it changed
    
    public Cryptolexo(int N, int M, int nwords) {
        this.N = N;
        this.M = M;
        this.nwords = nwords;
        cryptolexo = new String[N][M];
        createCryptolexo();
    }
    
    public Cryptolexo(int N, int nwords) {
        this(N, N, nwords);
    }
    
    private void createCryptolexo() {
        String[] words = WordList.getWords(nwords, N);
        Arrays.sort(words, new CompareStringLength());        
        
        int w = 0;
        while(w<nwords) {
            System.out.println(">> word "+words[w]);
            int tries = 0;
            while(tries<3) {
                tries++;
                System.out.println("TRY");
                if(addWordRandomly(words[w])) {
                    System.out.println(words[w]);
                    tries = 3;
                    System.out.println("SUCCESS");
                    //w++;
                } else {
                    // try to fill it!
                    System.out.println("FALSE");
                }
            }
            w++;
        }
    }
    
    private boolean addWordRandomly(String word) {
        System.out.println("addWordRandomly "+word);
        if(Utils.random(2) == 0) {
            // Vertical
            int col = Utils.random(M);
            if(isColEmpty(cryptolexo, col)) {
                int row = Utils.random(N-word.length());
                addVerticalWord(cryptolexo, row,col,word);
                return true;
            } else {
                //TRY TO FILL THE SPACES - FIRST AVAILABLE WILL DO
                int row = getColEmptySpace(cryptolexo, word.length(), col);
                if(row >= 0) {
                    System.out.println("HERE YES!!! "+row+" "+col+" "+word);
                    addVerticalWord(cryptolexo, row,col,word);
                    return true;
                } else {
                    // TRY TO FILL IT WITH COLLISIONS!
                    System.out.println("Trying colllision col");
                    row = getColWithCollision(cryptolexo, word, col);
                    if(row > 0) {
                        System.out.println("7777 YES!!! "+row+" "+col+" "+word);
                        addVerticalWord(cryptolexo, row,col,word);
                        return true;
                    }
                }
            }
        } else {
            // Horizontal
            int row = Utils.random(N);
            if(isRowEmpty(cryptolexo, row)) {
                int col = Utils.random(M-word.length());
                addHorizontalWord(cryptolexo, row,col,word);
                return true;
            } else {
                //TRY TO FILL THE SPACES - FIRST AVAILABLE WILL DO
                int col = getRowEmptySpace(cryptolexo, word.length(), row);
                System.out.println("Trying empy row"+row);
                if(col >= 0) {
                    System.out.println("HERE222 YES!!! "+row+" "+col+" "+word);
                    addHorizontalWord(cryptolexo, row,col,word);
                    return true;
                } else {
                    // TRY TO FILL IT WITH COLLISIONS!
                    System.out.println("Trying colllision row");
                    col = getRowWithCollision(cryptolexo, word, row);
                    if(col > 0) {
                        System.out.println("3333 YES!!! "+row+" "+col+" "+word);
                        addHorizontalWord(cryptolexo, row,col,word);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public void printCryptolexo() {
        printCryptolexo(PrintTypes.RANDOM);
    }
    
    private void printCryptolexo(PrintTypes pt) {
        for(int i=0;i<cryptolexo.length;i++) {
            for(int j=0;j<cryptolexo[0].length;j++){
                if(cryptolexo[i][j] == null) {
                    switch(pt) {
                        case EMPTY: System.out.print(" ");break;
                        case RANDOM: System.out.print(String.valueOf((char)Utils.random(65, 91)));break;
                        case NUMBER: System.out.print(j);break;
                    }
                } else {
                    System.out.print(cryptolexo[i][j]);
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
