/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package breakthrough;

import javax.swing.JPanel;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author v5inla
 */
public class BoardGUIIT {
    public BoardGUIIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getBoardPanel method, of class BoardGUI.
     */
    @Test
    public void testGetBoardPanel() {
        System.out.println("getBoardPanel");
        BoardGUI instance = null;
        JPanel expResult = null;
        JPanel result = instance.getBoardPanel();
        assertEquals(expResult, result);
    }
    
}
