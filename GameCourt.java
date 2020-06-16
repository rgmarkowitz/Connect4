
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameCourt extends JPanel {

	// game objects
	private GameBoard board;

	private boolean isPlaying = true;
	private JLabel status;
	private int column;
	
	
	// Every game starts with Player 1's turn
	private boolean isPlayer1 = true;
	private boolean isPlayer2 = false;

	// Collection to store previous moves
	private LinkedList<Circle> moves = new LinkedList<Circle>();

	// Constants
	public static final int COURT_WIDTH = 700;
	public static final int COURT_HEIGHT = 600;

	public GameCourt(JLabel status) {
		// creates border
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// Enable focus on one part of the keyboard
		setFocusable(true);
		
		this.status = status;
		
		reset();

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (isPlaying) {
					// set column in every key event
					if (e.getKeyCode() == KeyEvent.VK_Q) {
						column = 0;
					} else if (e.getKeyCode() == KeyEvent.VK_W) {
						column = 1;
					} else if (e.getKeyCode() == KeyEvent.VK_E) {
						column = 2;
					} else if (e.getKeyCode() == KeyEvent.VK_R) {
						column = 3;
					} else if (e.getKeyCode() == KeyEvent.VK_T) {
						column = 4;
					} else if (e.getKeyCode() == KeyEvent.VK_Y) {
						column = 5;
					} else if (e.getKeyCode() == KeyEvent.VK_U) {
						column = 6;
					} else {
						return;
					}
				}
			}

			public void keyReleased(KeyEvent e) {
				
				
				Circle newCircle = null;
				
				if (!isPlaying) {
					return;
				}
				
				// check which player it is to see which color the piece should be
				if (isPlayer1) {
					newCircle = new Circle(Color.RED, column * 100, board.getNextFreeRow(column) * 100);
					
				} else {
					newCircle = new Circle(Color.BLACK, column * 100, board.getNextFreeRow(column) * 100);
				}
				
				if (board.getNextFreeRow(column) == -1) {
					//how do you do something but not make it change the player
					return;
				}
				

				// update the board with new circle
				board.update(column, newCircle);
				
				//add most recent move to linked list
				moves.add(newCircle);
				
				//if there is a winner, write the low scores
				if (board.hasWon()) {
					Circle winningPiece = getLastMove();
					Game.writeLowScore("LowScores", winningPiece, getScore());
				}
				
				//repaint the board
				repaint();
				
				// change the player
				switchPlayer();

				if (board.hasWon() || board.isFull()) {
					isPlaying = false;
				} else {
					isPlaying = true;
				}
			}

		});

		this.status = status;
	}

	// Set game back to initial state
	public void reset() {
		//clear the linked list 
		moves.clear();
		
		//make a new game board
		board = new GameBoard();

		// reset different statuses
		isPlaying = true;
		status.setText("Running...");
		
		repaint();

		// reset player
		isPlayer1 = true;
		isPlayer2 = false;

		// Make sure that this component has the keyboard focus
		requestFocusInWindow();
	}

	// Undo one move of the game
	public void undo() {
		// remove the most recent addition to the linked list
		Circle removed = moves.removeLast();
		
		//remove the piece from the board
		int xcoord = removed.getXcoord() / 100;
		int ycoord = removed.getYcoord() / 100;
		board.erasePiece(xcoord, ycoord);
		
		// reset different statuses
		isPlaying = true;
		status.setText("Running...");
		
		repaint();
		
		// Make sure that this component has the keyboard focus
		requestFocusInWindow();
		
		//switch player back 
		switchPlayer();
	}
	
	
	public Circle getLastMove() {
		return moves.getLast();
	}
	
	public int getScore() {
		if (isPlayer1) {
			return moves.size() / 2 + 1;
		} else {
			return moves.size() / 2;
		}
	}
	
	// Helper function to switch which player's turn it is
	public void switchPlayer() {
		isPlayer1 = !isPlayer1;
		isPlayer2 = !isPlayer2;
	}
	
	public boolean isPlaying() {
		return isPlaying;
	}

	@Override
	public void paintComponent(Graphics g) {
		// if the board if full, change the status
		if (board.isFull()) {
			status.setText("Sorry! It's a draw!");

		}

		if (board.hasWon()) {
			Circle c = moves.getLast();
			Color thisColor = c.getColor();
			if (thisColor == Color.RED) {
				status.setText("Player 1 wins!");
			} else if (thisColor == Color.BLACK) {
				status.setText("Player 2 wins!");
			}
		}
		
		
		super.paintComponent(g);
		board.draw(g);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}

}
