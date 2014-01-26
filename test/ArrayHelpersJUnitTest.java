/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Cryptolexo.ArrayHelpers;
import static Cryptolexo.ArrayHelpers.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author periklis
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
        
        // do the same for the columns...
        
        System.out.println("testFindEmpty: PASSED");
    }
}
