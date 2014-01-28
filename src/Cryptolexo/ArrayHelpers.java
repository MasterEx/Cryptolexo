/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Cryptolexo;

/**
 *
 * @author periklis
 */
public class ArrayHelpers {
    
    public static int getRowWithCollision(String[][] array, String word, int r) {
        // start from all possible places
        for(int i=0;i<array[0].length-word.length();i++) {
            int arrayPointer = i;
            int wordPointer = 0;
            while(wordPointer < word.length() && arrayPointer < array[0].length) {
                if(array[r][arrayPointer] == null || 
                        array[r][arrayPointer].equals(String.valueOf(word.charAt(wordPointer)))) {
                    wordPointer++;
                    if(wordPointer==word.length()) {
                        return arrayPointer - word.length() + 1;//because ???
                    }
                } else {
                    wordPointer = 0;
                }
                arrayPointer++;
            }
        }
        return -1;
    }
    
    public static int getColWithCollision(String[][] array, String word, int c) {
        for(int i=0;i<array.length-word.length();i++) {
            // start from all possible places
            int arrayPointer = i;
            int wordPointer = 0;
            while(wordPointer < word.length() && arrayPointer < array.length) {
                if(array[arrayPointer][c] == null || 
                        array[arrayPointer][c].equals(String.valueOf(word.charAt(wordPointer)))) { // redo this with substring, maybe faster?
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
    
    public static int getColEmptySpace(String[][] array, int length, int c) {
        int count = 0;
        for(int i=0;i<array.length;i++) {
            if(array[i][c] == null) {
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
    
    public static int getRowEmptySpace(String[][] array, int length, int r) {
        int count = 0;
        for(int i=0;i<array[0].length;i++) {
            if(array[r][i] == null) {
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
    
    public static boolean isColEmpty(String[][] array, int c) {
        for(int i=0;i<array.length;i++) {
            if(array[i][c] != null) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isRowEmpty(String[][] array, int r) {
        for(int i=0;i<array.length;i++) {
            if(array[r][i] != null) {
                return false;
            }
        }
        return true;
    }
    
    public static void addVerticalWord(String[][] array, int n,int m,String word) {
        for(int i=0;i<word.length();i++) {
            array[i+n][m] = ""+word.charAt(i);
        }
    }
    
    public static void addHorizontalWord(String[][] array, int n,int m,String word) {
        for(int i=0;i<word.length();i++) {
            array[n][i+m] = ""+word.charAt(i);
        }
    }
    
}
