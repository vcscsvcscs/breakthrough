package breakthrough;

import java.awt.Color;
import java.awt.Point;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    private static Board instance;

    @BeforeClass
    public static void setUpClass() {
        instance = new Board(8); // Adjust the board size as needed
    }

    @AfterClass
    public static void tearDownClass() {
        instance = null;
    }

    @Test
    public void testIsOver() {
        System.out.println("isOver");
        boolean expResult = false;
        boolean result = instance.isOver();
        assertEquals(expResult, result);
    }

    @Test
    public void testGet_int_int() {
        System.out.println("get");
        int x = 0;
        int y = 0;
        Field expResult = instance.get(x, y);
        assertNotNull(expResult);
    }

    @Test
    public void testGet_Point() {
        System.out.println("get");
        Point point = new Point(0, 0);
        Field expResult = instance.get(point);
        assertNotNull(expResult);
    }

    @Test
    public void testGetBoardSize() {
        System.out.println("getBoardSize");
        int expResult = 8; // Change this to match the board size used in setUpClass
        int result = instance.getBoardSize();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetCurrentPlayer() {
        System.out.println("setCurrentPlayer");
        Color currentPlayer = Color.BLACK; // Change this to the desired color
        instance.setCurrentPlayer(currentPlayer);
        assertEquals(currentPlayer, instance.getCurrentPlayer());
    }

    @Test
    public void testMovePiece() {
        System.out.println("movePiece");
        Point src = new Point( 0, 0); // Adjust the source and destination points based on your board setup
        Point dst = new Point(1, 0);
        Boolean expResult = true; // Adjust this based on the expected result of your movePiece method
        Boolean result = instance.movePiece(src, dst);
        assertEquals(expResult, result);
    }
}
