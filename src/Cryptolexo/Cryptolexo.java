/*
 *  Cryptolexo Game Generator
 *  Copyright (C) 2014  Periklis Ntanasis <pntanasis@gmail.com>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package Cryptolexo;

import static Cryptolexo.ArrayHelpers.addHorizontalWord;
import static Cryptolexo.ArrayHelpers.addVerticalWord;
import static Cryptolexo.ArrayHelpers.getRowInColWithCollision;
import static Cryptolexo.ArrayHelpers.getColInRowWithCollision;
import static Cryptolexo.ArrayHelpers.isColEmpty;
import static Cryptolexo.ArrayHelpers.isRowEmpty;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author Periklis Ntanasis <pntanasis@gmail.com>
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
    private char[][] cryptolexoBase;
    private char[][] cryptolexo;
    private int nwords = 5;
    private String[] words;
    private final int ROW = 0, COLUMN = 1;
    private ArrayList<String> removedWords;
    private ArrayList<String> includedWords;
    private final int MAX_RANDOM_TRIES = 3;
    
    public Cryptolexo(int N, int M, int nwords) {
        this.N = N;
        this.M = M;
        this.nwords = nwords;
        cryptolexoBase = new char[N][M];
        cryptolexo = new char[N][M];
        removedWords = new ArrayList<>();
        includedWords = new ArrayList<>();
        createCryptolexo();
    }
    
    public Cryptolexo(int N, int nwords) {
        this(N, N, nwords);
    }
    
    public Cryptolexo(int N, String[] words) {
        this(N, N, words);
    }
    
    public Cryptolexo(int N, int M, String[] words) {
        this.N = N;
        this.M = M;
        this.nwords = words.length;
        this.words = words;
        cryptolexoBase = new char[N][M];
        cryptolexo = new char[N][M];
        removedWords = new ArrayList<>();
        includedWords = new ArrayList<>();
        createCryptolexo();
    }
    
    public String[] getWords() {
        return includedWords.toArray(words);
    }
    
    public String[] getRemovedWords() {
        return removedWords.toArray(words);
    }
    
    public char[][] getCryptolexo() {
        return cryptolexo.clone(); // no cryptolexo manipulation allowed outside of Cryptolexo
    }
    
    private void createCryptolexo() {
        // use random words from the WordList if words aren't set
        if(words == null) {
            words = WordList.getWords(nwords, Math.max(N,M));
        }
        Arrays.sort(words, new CompareStringLength());
        
        int w = 0;
        while(w<nwords) {
            boolean success = false;
            int tries = 0;
            while(tries<MAX_RANDOM_TRIES) {
                tries++;
                if(addWordRandomly(words[w])) {
                    tries = MAX_RANDOM_TRIES;
                    success = true;
                    //w++;
                }
            }
            if(!success) {
                if(addWordBruteForceSequentially(words[w])) {
                    success = true;
                }
            }
            if(success) {
                includedWords.add(words[w]); 
            } else {
                removedWords.add(words[w]);                
            }
            w++;
        }
        createFilledCryptolexo();
    }
    
    private boolean addWordRandomly(String word) {
        if(Utils.random(2) == COLUMN) {
            // Vertical
            int col = Utils.random(M);
            return addWordInCol(word, col);
        } else {
            // Horizontal
            int row = Utils.random(N);
            return addWordInRow(word, row);
        }
    }
    
    private boolean addWordBruteForceSequentially(String word) {
        for(int i=0;i<N;i++) {
            if(addWordInRow(word, i)) {
                return true;
            }
        }
        for(int i=0;i<M;i++) {
            if(addWordInCol(word, i)) {
                return true;
            }
        }
        return false;
    }
    
    // NOT TESTED!
    private boolean addWordBruteForceRandomStart(String word) {
        // TO IMPLEMENT
        int arrayPointer = Utils.random(N);
        for(int i=0;i<N;i++) {
            if(addWordInRow(word, arrayPointer)) {
                return true;
            }
            arrayPointer = (arrayPointer+1)%N;
        }
        arrayPointer = Utils.random(M);
        for(int i=0;i<M;i++) {
            if(addWordInCol(word, i)) {
                return true;
            }
            arrayPointer = (arrayPointer+1)%M;
        }
        return false;
    }
    
    private boolean addWordInCol(String word, int col) {
        // Vertical
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
        return false;
    }
    
    private boolean addWordInRow(String word, int row) {
        // Horizontal
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
        return false;
    }
    
    public void printCryptolexo() {
        printCryptolexo(PrintTypes.RANDOM);
    }
    
    private void createFilledCryptolexo() {
        for(int i=0;i<cryptolexoBase.length;i++) {
            for(int j=0;j<cryptolexoBase[0].length;j++){
                if(cryptolexoBase[i][j] == 0) {
                    cryptolexo[i][j] = (char)Utils.random(65, 91);
                } else {
                    cryptolexo[i][j] = cryptolexoBase[i][j];
                }
            }
        }
    }
    
    private void printCryptolexo(PrintTypes pt) {
        for(int i=0;i<cryptolexoBase.length;i++) {
            for(int j=0;j<cryptolexoBase[0].length;j++){
                if(cryptolexoBase[i][j] == 0) {
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
