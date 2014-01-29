package Cryptolexo;

/**
 *
 * @author Periklis Ntanasis <pntanasis@gmail.com>
 */
public class Utils {

    public static int random(int A) {
        return (int) (Math.random() * A);
    }

    public static int random(int A, int B) {
        return (int) (Math.random() * (B - A)) + A;
    }

}
