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
public class WordList {
    
    private static final String[] list = {
        "HELLO",
        "HISTORY",
        "MUSIC",
        "PARTY",
        "CAKE",
        "BIRD",
        "COUNTRY",
        "ACTION",
        "WOLF",
        "SPIDER",
        "VENOM",
        "GAME",
        "ICE",
        "SATISFACTION",
        "MUG",
        "ELEPHANT",
        "LION",
        "SCHOOL",
        "ANT"
    };
    
    public static String[] getAllWords() {
        return list;
    }
    
    public static String[] getWords(int n) {
        String[] array = list.clone();
        shuffle(array, n);
        String[] retArr = new String[n];
        System.arraycopy(array, 0, retArr, 0, n);
        return retArr;
    }
    
    public static String[] getWords(int n, int length) {
        String[] array = list.clone();
        shuffle(array, n);
        String[] retArr = new String[n];
        int i=0, c=0;
        while(i<n) {
            if(array[i].length() <= length) {
                retArr[i] = array[i];
                c++;
            }
            i++;
        }
        return retArr;
    }
    
    private static void shuffle(String[] array, int n) {
        for(int i=0;i<n;i++) {
            String tmp = array[i];
            int random = Utils.random(array.length);
            array[i] = array[random];
            array[random] = tmp;
        }
    }
    
}
