package Cryptolexo;

/**
 *
 * @author Periklis Ntanasis <pntanasis@gmail.com>
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Game game = new Game(10, 5);
        game.newGame();
        game.printAsciiCryptolexo();

        System.out.println("The words are:");
        for (String s : game.getWords()) {
            System.out.print(s + " ");
        }
        System.out.println();
        System.out.println("Does it include MUSIC? " + game.isWordIncluded("MUSIC"));
        System.out.println("Does it include HISTORY? " + game.isWordIncluded("HISTORY"));
        System.out.println("Does it include PARTY? " + game.isWordIncluded("PARTY"));

        System.out.println("Check if 5 first letters in row 1(0) is a word");
        System.out.println("Is a word? " + game.getWord(3, 0, 3, 4));

        System.out.println("GAME 2");
        String[] s = {"HIPPO", "BERRY", "PIE", "TIE", "BOOMERANG"};
        game.newGame(s);
        game.printAsciiCryptolexo();
    }

}
