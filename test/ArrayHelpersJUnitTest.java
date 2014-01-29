
import static Cryptolexo.ArrayHelpers.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Periklis Ntanasis <pntanasis@gmail.com>
 */
public class ArrayHelpersJUnitTest {

    public ArrayHelpersJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testEmpty() {
        String[][] s = new String[2][2];
        s[1][1] = "t";
        assertTrue("Failed to recognize empty row as empty", isRowEmpty(s, 0));
        assertTrue("Failed to recognize empty colum as empty", isColEmpty(s, 0));
        assertFalse("Failed to recognize occupied row as occupied", isRowEmpty(s, 1));
        assertFalse("Failed to recognize occupied colum as occupied", isColEmpty(s, 1));
        System.out.println("testEmpty: PASSED");
    }

    @Test
    public void testFindEmpty() {
        String[][] s = new String[3][6];
        // "ttt   "
        s[0][0] = "t";
        s[0][1] = "t";
        s[0][2] = "t";
        // "   ttt"
        s[1][3] = "t";
        s[1][4] = "t";
        s[1][5] = "t";
        // " tt t "
        s[2][1] = "t";
        s[2][2] = "t";
        s[2][4] = "t";
        assertEquals("Failed to recognize that word doesn't fit", -1, getRowEmptySpace(s, 12, 0));
        assertEquals("Failed to recognize that word doesn't fit", -1, getRowEmptySpace(s, 4, 0));
        assertEquals("Failed to find the correct place for the new word", 3, getRowEmptySpace(s, 3, 0));
        assertEquals("Failed to recognize that word doesn't fit", -1, getRowEmptySpace(s, 12, 1));
        assertEquals("Failed to recognize that word doesn't fit", -1, getRowEmptySpace(s, 4, 1));
        assertEquals("Failed to find the correct place for the new word", 0, getRowEmptySpace(s, 3, 1));
        assertEquals("Failed to recognize that word doesn't fit", -1, getRowEmptySpace(s, 12, 2));
        assertEquals("Failed to recognize that word doesn't fit", -1, getRowEmptySpace(s, 4, 2));
        assertEquals("Failed to recognize that word doesn't fit", -1, getRowEmptySpace(s, 2, 2));
        assertEquals("Failed to find the correct place for the new word", 0, getRowEmptySpace(s, 1, 2));

        s = new String[8][3];
        // think vertical! "ttt     "
        s[0][0] = "t";
        s[1][0] = "t";
        s[2][0] = "t";
        // "    ttt "
        s[4][1] = "t";
        s[5][1] = "t";
        s[6][1] = "t";
        // "  tt  t "
        s[2][2] = "t";
        s[3][2] = "t";
        s[6][2] = "t";
        assertEquals("Failed to recognize that word doesn't fit", -1, getColEmptySpace(s, 12, 0));
        assertEquals("Failed to recognize that word doesn't fit", -1, getColEmptySpace(s, 6, 0));
        assertEquals("Failed to find the correct place for the new word", 3, getColEmptySpace(s, 5, 0));
        assertEquals("Failed to recognize that word doesn't fit", -1, getColEmptySpace(s, 12, 1));
        assertEquals("Failed to recognize that word doesn't fit", -1, getColEmptySpace(s, 6, 1));
        assertEquals("Failed to recognize that word doesn't fit", -1, getColEmptySpace(s, 5, 1));
        assertEquals("Failed to find the correct place for the new word", 0, getColEmptySpace(s, 4, 1));
        assertEquals("Failed to recognize that word doesn't fit", -1, getColEmptySpace(s, 12, 2));
        assertEquals("Failed to recognize that word doesn't fit", -1, getColEmptySpace(s, 6, 2));
        assertEquals("Failed to recognize that word doesn't fit", -1, getColEmptySpace(s, 5, 2));
        assertEquals("Failed to recognize that word doesn't fit", -1, getColEmptySpace(s, 4, 2));
        assertEquals("Failed to find the correct place for the new word", 0, getColEmptySpace(s, 2, 2));

        System.out.println("testFindEmpty: PASSED");
    }

    @Test
    public void testFindCollisions() {
        String[][] s = new String[1][12];
        // "  note      "
        //  012345678912
        s[0][2] = "n";
        s[0][3] = "o";
        s[0][4] = "t";
        s[0][5] = "e";
        assertEquals("Failed to recognize that word doesn't fit", -1, getColInRowWithCollision(s, "highlander", 0));
        assertEquals("Failed to recognize that word doesn't fit", -1, getColInRowWithCollision(s, "bookstore", 0));
        assertEquals("Failed to find the correct place for the new word", 6, getColInRowWithCollision(s, "and", 0));
        assertEquals("Failed to find the correct place for the new word", 6, getColInRowWithCollision(s, "book", 0));
        assertEquals("Failed to find the correct place for the new word", 2, getColInRowWithCollision(s, "notebook", 0));
        // "     note   "
        //  012345678912
        s = new String[1][12];
        s[0][5] = "n";
        s[0][6] = "o";
        s[0][7] = "t";
        s[0][8] = "e";
        assertEquals("Failed to find the correct place for the new word", 0, getColInRowWithCollision(s, "and", 0));
        assertEquals("Failed to recognize that word doesn't fit", -1, getColInRowWithCollision(s, "notebook", 0));
        // "     clip   "
        //  012345678912
        s = new String[1][12];
        s[0][5] = "c";
        s[0][6] = "l";
        s[0][7] = "i";
        s[0][8] = "p";
        assertEquals("Failed to find the correct place for the new word", 0, getColInRowWithCollision(s, "paperclip", 0));
        // " rockandroll"
        //  012345678901
        s = new String[1][12];
        s[0][1] = "r";
        s[0][2] = "o";
        s[0][3] = "c";
        s[0][4] = "k";
        s[0][5] = "a";
        s[0][6] = "n";
        s[0][7] = "d";
        s[0][8] = "r";
        s[0][9] = "o";
        s[0][10] = "l";
        s[0][11] = "l";
        assertEquals("Failed to find the correct place for the new word", 5, getColInRowWithCollision(s, "and", 0));

        s = new String[12][1];
        // "  note      "  think vertical!
        //  012345678912
        s[2][0] = "n";
        s[3][0] = "o";
        s[4][0] = "t";
        s[5][0] = "e";
        assertEquals("Failed to recognize that word doesn't fit", -1, getRowInColWithCollision(s, "highlander", 0));
        assertEquals("Failed to recognize that word doesn't fit", -1, getRowInColWithCollision(s, "bookstore", 0));
        assertEquals("Failed to find the correct place for the new word", 6, getRowInColWithCollision(s, "and", 0));
        assertEquals("Failed to find the correct place for the new word", 6, getRowInColWithCollision(s, "book", 0));
        assertEquals("Failed to find the correct place for the new word", 2, getRowInColWithCollision(s, "notebook", 0));
        // "     note   "
        //  012345678912
        s = new String[12][1];
        s[5][0] = "n";
        s[6][0] = "o";
        s[7][0] = "t";
        s[8][0] = "e";
        assertEquals("Failed to find the correct place for the new word", 0, getRowInColWithCollision(s, "and", 0));
        assertEquals("Failed to recognize that word doesn't fit", -1, getRowInColWithCollision(s, "notebook", 0));
        // "     clip   "
        //  012345678912
        s = new String[12][1];
        s[5][0] = "c";
        s[6][0] = "l";
        s[7][0] = "i";
        s[8][0] = "p";
        assertEquals("Failed to find the correct place for the new word", 0, getRowInColWithCollision(s, "paperclip", 0));
        // " rockandroll"
        //  012345678901
        s = new String[12][1];
        s[1][0] = "r";
        s[2][0] = "o";
        s[3][0] = "c";
        s[4][0] = "k";
        s[5][0] = "a";
        s[6][0] = "n";
        s[7][0] = "d";
        s[8][0] = "r";
        s[9][0] = "o";
        s[10][0] = "l";
        s[11][0] = "l";
        assertEquals("Failed to find the correct place for the new word", 5, getRowInColWithCollision(s, "and", 0));

        System.out.println("testFindCollisions: PASSED");
    }

    @Test
    public void testGetSelectedLetters() {
        String[][] s = new String[5][5];
        s[0][0] = "t";
        s[0][1] = "r";
        s[0][2] = "a";
        s[0][3] = "i";
        s[0][4] = "n";
        s[1][0] = "o";
        s[2][0] = "s";
        s[3][0] = "t";
        assertEquals("Failed to return the correct word", "t", getSelectedLetters(s, 0, 0, 0, 0));
        assertEquals("Failed to return the correct word", "train", getSelectedLetters(s, 0, 0, 0, 4));
        assertEquals("Failed to return the correct word", "tost", getSelectedLetters(s, 0, 0, 3, 0));
        assertEquals("Failed to return the correct word", "ain", getSelectedLetters(s, 0, 2, 0, 4));
        assertEquals("Failed to return the correct word", "ain", getSelectedLetters(s, 0, 4, 0, 2));
        assertNotSame("Failed to return the correct word", "tata", getSelectedLetters(s, 0, 4, 0, 2));

        System.out.println("testGetSelectedLetters: PASSED");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetSelectedLetters2() {
        String[][] s = new String[5][5];
        getSelectedLetters(s, 0, 4, 0, 5);
    }
}
