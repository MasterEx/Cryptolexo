/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Cryptolexo;

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
            if(isColEmpty(col)) {
                int row = Utils.random(N-word.length());
                addVerticalWord(row,col,word);
                return true;
            } else {
                //TRY TO FILL THE SPACES - FIRST AVAILABLE WILL DO
                int row = getColEmptySpace(word.length(), col);
                if(row >= 0) {
                    System.out.println("HERE YES!!! "+row+" "+col+" "+word);
                    addVerticalWord(row,col,word);
                    return true;
                } else {
                    // TRY TO FILL IT WITH COLLISIONS!
                    System.out.println("Trying colllision col");
                    row = getColWithCollision(word, col);
                    if(row > 0) {
                        System.out.println("7777 YES!!! "+row+" "+col+" "+word);
                        addVerticalWord(row,col,word);
                        return true;
                    }
                }
            }
        } else {
            // Horizontal
            int row = Utils.random(N);
            if(isRowEmpty(row)) {
                int col = Utils.random(M-word.length());
                addHorizontalWord(row,col,word);
                return true;
            } else {
                //TRY TO FILL THE SPACES - FIRST AVAILABLE WILL DO
                int col = getRowEmptySpace(word.length(), row);
                System.out.println("Trying empy row"+row);
                if(col >= 0) {
                    System.out.println("HERE222 YES!!! "+row+" "+col+" "+word);
                    addHorizontalWord(row,col,word);
                    return true;
                } else {
                    // TRY TO FILL IT WITH COLLISIONS!
                    System.out.println("Trying colllision row");
                    col = getRowWithCollision(word, row);
                    if(col > 0) {
                        System.out.println("3333 YES!!! "+row+" "+col+" "+word);
                        addHorizontalWord(row,col,word);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private int getRowWithCollision(String word, int r) {
        System.out.println("getRowWithCollision "+word+" "+r);
        for(int i=0;i<cryptolexo[0].length-word.length();i++) {
            // start from all possible places
            int arrayPointer = i;
            int wordPointer = 0;
            while(wordPointer < word.length() && arrayPointer < cryptolexo[0].length) {
                if(cryptolexo[r][arrayPointer] == null || 
                        cryptolexo[r][arrayPointer].equals(String.valueOf(word.charAt(wordPointer)))) {
                    wordPointer++;
                    if(wordPointer==word.length()) {
                        return arrayPointer - word.length() +1;//because ???
                    }
                } else {
                    wordPointer = 0;
                }
                arrayPointer++;
            }            
        }
        return -1;
    }
    
    private int getColWithCollision(String word, int c) {
        System.out.println("getColWithCollision "+word+" "+c);
        // what is this for for? can't remmeber and so sleepy :/
        for(int i=0;i<cryptolexo.length-word.length();i++) {
            // start from all possible places
            int arrayPointer = i;
            int wordPointer = 0;
            System.out.println("LOOP");
            while(wordPointer < word.length() && arrayPointer < cryptolexo.length) {
                System.out.println("Arr Point : "+arrayPointer);
                System.out.println("c : "+c);
                if(cryptolexo[arrayPointer][c] == null || 
                        cryptolexo[arrayPointer][c].equals(String.valueOf(word.charAt(wordPointer)))) { // redo this with substring
                    wordPointer++;
                    if(wordPointer==word.length()) {
                        return arrayPointer - word.length() +1;
                    }
                } else {
                    wordPointer = 0;
                }
                arrayPointer++;
                System.out.println("wp : "+wordPointer);
            }            
        }
        return -1;
    }
    
    private int getColEmptySpace(int length, int c) {
        System.out.println("getColEmptySpace "+c+" "+length);
        int count = 0;
        for(int i=0;i<cryptolexo[0].length;i++) {
            if(cryptolexo[i][c] == null) {
                System.out.println(">>> i="+i+" cryptolexo[i][c]"+cryptolexo[i][c]+" count"+count);
                count++;
                if(count == length) {
                    return (i-count+1); // WHY???
                }
            } else {
                count = 0;
            }
        }
        return -1;
    }
    
    private int getRowEmptySpace(int length, int r) {
        System.out.println("getRowEmptySpace "+r+" "+length);
        int count = 0;
        for(int i=0;i<cryptolexo[0].length;i++) {
            if(cryptolexo[r][i] == null) {
                count++;
                if(count == length) {
                    return (i-count+1);
                }
            } else {
                count = 0;
            }
        }
        return -1;
    }
    
    private boolean isColEmpty(int c) {
        for(int i=0;i<cryptolexo.length;i++) {
            if(cryptolexo[i][c] != null) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isRowEmpty(int r) {
        for(int i=0;i<cryptolexo.length;i++) {
            if(cryptolexo[r][i] != null) {
                return false;
            }
        }
        return true;
    }
    
    private void addVerticalWord(int n,int m,String word) {
        for(int i=0;i<word.length();i++) {
            cryptolexo[i+n][m] = ""+word.charAt(i);
        }
    }
    
    private void addHorizontalWord(int n,int m,String word) {
        for(int i=0;i<word.length();i++) {
            cryptolexo[n][i+m] = ""+word.charAt(i);
        }
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
