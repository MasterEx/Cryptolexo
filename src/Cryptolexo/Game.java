/*
 *  Cryptolexo Game Client
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
public class Game {

    Cryptolexo crypt;
    int N, M, numberOfWords;

    public Game(int N, int M, int numberOfWords) {
        // Start a game
        this.N = N;
        this.M = M;
        this.numberOfWords = numberOfWords;
    }

    public Game(int N, int numberOfWords) {
        this(N, N, numberOfWords);
    }

    public void newGame() {
        crypt = new Cryptolexo(N, M, numberOfWords);

    }

    public void newGame(int numberOfWords) {
        this.numberOfWords = numberOfWords;
        crypt = new Cryptolexo(N, M, numberOfWords);
    }

    public void newGame(String[] words) {
        this.numberOfWords = words.length;
        crypt = new Cryptolexo(N, M, words);
    }

    public void printAsciiCryptolexo() {
        crypt.printCryptolexo();
    }

    public String[] getWords() {
        return crypt.getWords();
    }

    public String[] getNotUsedWords() {
        return crypt.getRemovedWords();
    }

    public boolean getWord(int x1, int y1, int x2, int y2) {
        return isWordIncluded(ArrayHelpers.getSelectedLetters(crypt.getCryptolexo(), x1, y1, x2, y2));
    }

    public boolean isWordIncluded(String word) {
        for (String s : crypt.getWords()) {
            if (word.equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }

}
