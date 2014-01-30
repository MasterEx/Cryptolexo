/*
 *  Methods to traverse String/char arrays and find empty spots for words
 *  It is the essence of Cryptolexo.
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

/**
 *
 * @author Periklis Ntanasis <pntanasis@gmail.com>
 */
public class ArrayHelpers {
    
    public static int getColInRowWithCollision(char[][] array, String word, int r) {
        // start from all possible places
        for(int i=0;i<array[0].length-word.length();i++) {
            int arrayPointer = i;
            int wordPointer = 0;
            while(wordPointer < word.length() && arrayPointer < array[0].length) {
                if(array[r][arrayPointer] == 0 || 
                        array[r][arrayPointer] == word.charAt(wordPointer)) {
                    wordPointer++;
                    if(wordPointer==word.length()) {
                        return arrayPointer - word.length() + 1;
                    }
                } else {
                    wordPointer = 0;
                }
                arrayPointer++;
            }
        }
        return -1;
    }
    
    public static int getRowInColWithCollision(char[][] array, String word, int c) {
        for(int i=0;i<array.length-word.length();i++) {
            // start from all possible places
            int arrayPointer = i;
            int wordPointer = 0;
            while(wordPointer < word.length() && arrayPointer < array.length) {
                if(array[arrayPointer][c] == 0 || 
                        array[arrayPointer][c] == word.charAt(wordPointer)) {
                    wordPointer++;
                    if(wordPointer==word.length()) {
                        return arrayPointer - word.length() +1;
                    }
                } else {
                    wordPointer = 0;
                }
                arrayPointer++;
            }            
        }
        return -1;
    }
    
    public static int getColEmptySpace(char[][] array, int length, int c) {
        int count = 0;
        for(int i=0;i<array.length;i++) {
            if(array[i][c] == 0) {
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
    
    public static int getRowEmptySpace(char[][] array, int length, int r) {
        int count = 0;
        for(int i=0;i<array[0].length;i++) {
            if(array[r][i] == 0) {
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
    
    public static boolean isColEmpty(char[][] array, int c) {
        for(int i=0;i<array.length;i++) {
            if(array[i][c] != 0) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isRowEmpty(char[][] array, int r) {
        for(int i=0;i<array.length;i++) {
            if(array[r][i] != 0) {
                return false;
            }
        }
        return true;
    }
    
    public static void addVerticalWord(char[][] array, int n,int m,String word) {
        for(int i=0;i<word.length();i++) {
            array[i+n][m] = word.charAt(i);
        }
    }
    
    public static void addHorizontalWord(char[][] array, int n,int m,String word) {
        for(int i=0;i<word.length();i++) {
            array[n][i+m] = word.charAt(i);
        }
    }
    
    public static String getSelectedLetters(char[][] array, int x1, int y1, int x2, int y2) {
        String s = "";
        if(x1 == x2 && x1 >= 0 && x1 < array.length) {
            for(int i=Math.min(y1, y2);i<=Math.max(y1, y2);i++) {
                s = s + array[x1][i];
            }
        } else if(y1==y2 && y1 >= 0 && y1 < array[0].length) {
            for(int i=Math.min(x1, x2);i<=Math.max(x1, x2);i++) {
                s = s + array[i][y1];
            }
        }
        return s;
    }
    
}
