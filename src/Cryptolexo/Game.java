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
public class Game {
    
    Cryptolexo crypt;
    int N, M, numberOfWords;
    
    public Game(int N,int M,int numberOfWords) {
        // Start a game
        this.N = N;
        this.M = M;
        this.numberOfWords = numberOfWords;
    }
    
    public Game(int N,int numberOfWords) {
        this(N, N, numberOfWords);
    }
    
    public void newGame() {
        crypt = new Cryptolexo(N, M, numberOfWords);
        
    }
    
    public void newGame(int numberOfWords) {
        this.numberOfWords = numberOfWords;
        crypt = new Cryptolexo(N, M, numberOfWords);
    }
    
    public void printAsciiCryptolexo() {
        crypt.printCryptolexo();
    }
    
    public boolean getWord(int x1, int y1, int x2, int y2) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
    
    public boolean isWordIncluded(String word) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
    
}
