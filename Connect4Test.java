/* Rachel Markowitz
 * 
 * This file contains testing on my Connect 4 game logic
 */

import org.junit.*;
import static org.junit.Assert.*;

import java.awt.Color;

import javax.swing.JLabel;

public class Connect4Test {
	
	final JLabel status = new JLabel("Running...");
	GameBoard board = new GameBoard();

	
	//Test the board starts as blank
	@Test 
	public void testStartingBoard() {
		Circle[][] test = new Circle[7][6];
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				test[i][j] = new Circle(Color.WHITE, 100 * i, 100 * j);
			}
		}
		
		assertArrayEquals(board.getBoard(), test);
	}
	
	//test adding a piece
	@Test
	public void testAddPiece() {
		Circle[][] test = new Circle[7][6];
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				test[i][j] = new Circle(Color.WHITE, 100 * i, 100 * j);
			}
		}
		test[0][5].setColor(Color.RED);
		
		Circle newCircle = new Circle(Color.RED, 0, 5);
		board.update(0, newCircle);
		
		assertArrayEquals(board.getBoard(), test);
	}
	
	//test update upper bound
	@Test 
	public void testUpdateUpperBound() {
		Circle[][] test = new Circle[7][6];
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				test[i][j] = new Circle(Color.WHITE, 100 * i, 100 * j);
			}
		}
		
		//adding a piece out of bounds should not change board
		Circle newCircle = new Circle(Color.RED, 0, 6);
		board.update(10, newCircle);
		
		assertArrayEquals(board.getBoard(), test);
		
	}
	
	//test update lower bound
	@Test 
	public void testUpdateLowerBound() {
		Circle[][] test = new Circle[7][6];
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				test[i][j] = new Circle(Color.WHITE, 100 * i, 100 * j);
			}
		}
		
		//adding a piece out of bounds should not change board
		Circle newCircle = new Circle(Color.RED, 0, 6);
		board.update(-2, newCircle);
		
		assertArrayEquals(board.getBoard(), test);
		
	}
	
	//test horizontal win 
	@Test 
	public void testHorizontalWin() {
		Circle circle1 = new Circle(Color.RED, 0, 6);
		board.update(0, circle1);
		
		Circle circle2 = new Circle(Color.RED, 1, 6);
		board.update(1, circle2);
		Circle circle3 = new Circle(Color.RED, 2, 6);
		board.update(2, circle3);
		
		assertFalse(board.horizontalWin());
		
		Circle circle4 = new Circle(Color.RED, 3, 6);
		board.update(3, circle4);
		
		assertTrue(board.horizontalWin());
	}
	
	//test vertical win 
	@Test 
	public void testVerticalWin() {
		Circle circle1 = new Circle(Color.RED, 0, 6);
		board.update(0, circle1);
		
		Circle circle2 = new Circle(Color.RED, 0, 5);
		board.update(0, circle2);
		Circle circle3 = new Circle(Color.RED, 0, 4);
		board.update(0, circle3);
		
		assertFalse(board.verticalWin());
		
		Circle circle4 = new Circle(Color.RED, 0, 3);
		board.update(0, circle4);
		
		assertTrue(board.verticalWin());
	}
	
	
	//test upwards diagonal win 
	@Test 
	public void testUpDiagWin() { // i feel like this should fail 
		Circle circle1 = new Circle(Color.RED, 0, 5);
		board.addTestPiece(circle1, 0, 5);
		Circle circle2 = new Circle(Color.RED, 1, 4);
		board.addTestPiece(circle2, 1, 4);		
		Circle circle3 = new Circle(Color.RED, 2, 3);
		board.addTestPiece(circle3, 2, 3);
		
		assertFalse(board.upDiagWin());
		
		Circle circle4 = new Circle(Color.RED, 3, 2);
		board.addTestPiece(circle4, 3, 2);
		
		assertTrue(board.upDiagWin());
	}
	
	//test downwards diagonal win 
	@Test 
	public void testDownDiagWin() { //shouldn't this fail
		Circle circle1 = new Circle(Color.RED, 3, 5);
		board.addTestPiece(circle1,  3,  5);
		Circle circle2 = new Circle(Color.RED, 2, 4);
		board.addTestPiece(circle2,  2,  4);
		Circle circle3 = new Circle(Color.RED, 1, 3);
		board.addTestPiece(circle3,  1,  3);
		
		assertFalse(board.downDiagWin());
		
		Circle circle4 = new Circle(Color.RED, 0, 2);
		board.addTestPiece(circle4,  0,  2);
		
		assertTrue(board.downDiagWin());
		
	}
	
	//test full board
	@Test
	public void testIsFullTrue() {
		board.fillBoard();
		assertTrue(board.isFull());
	}
	
	@Test
	public void testIsFullFalse() {
		assertFalse(board.isFull());
	}
	
	//test place a piece in a full spot
	@Test 
	public void cannotUpdate() {
		board.fillBoard();
		
	}
	
	//test width 
	@Test
	public void testWidth() {
		assertEquals(7, board.getWidth());
	}
	
	//test height 
	@Test
	public void testHeight() {
		assertEquals(6, board.getHeight());
	}
	
	//test get next free row
	@Test
	public void testGetNextFreeRow1() {
		assertEquals(5, board.getNextFreeRow(0));
	}
	
	@Test
	public void testGetNextFreeRow2() {
		Circle toAdd = new Circle(Color.RED, 0, 5);
		board.update(0, toAdd);
		assertEquals(4, board.getNextFreeRow(0));
	}
	
}
