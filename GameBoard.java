
/* Rachel Markowitz
 * 
 * This class represents the board used in connect 4. It is represented by a 2d array 
 * of type Circle;
 * 
 */
import java.awt.*;

public class GameBoard {

	private Circle[][] board;
	private int width = 7;
	private int height = 6;

	public GameBoard() {
		this.board = new Circle[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				board[i][j] = new Circle(Color.WHITE, 100 * i, 100 * j);
			}
		}
	}

	// Update board with new piece
	public void update(int column, Circle piece) {
		if (column >= width || column < 0) {
			return;
		}
		// if it's in a column, place in farthest empty column
		int row = getNextFreeRow(column);
		board[column][row] = piece;
	}

	// This function gets the row where the piece should be added
	public int getNextFreeRow(int column) {
		int freeRow = 0;
		Circle[] correctCol = new Circle[6];

		// fill correctCol with the values of the column we want
		for (int i = 0; i < height; i++) {
			correctCol[i] = board[column][i];
		}

		// find the row we need
		for (int i = correctCol.length - 1; i >= 0; i--) {
			if (correctCol[i] == null) {
				continue;
			}
			if (correctCol[i].getColor() != Color.WHITE) {
				continue;
			} else {
				freeRow = i;
				break;
			}
		}
		if (correctCol[0].getColor() != Color.WHITE) {
			return -1;
		} else {
			return freeRow;
		}
	}

	public void erasePiece(int x, int y) {
		board[x][y].setColor(Color.WHITE);
	}

	// Check every direction of win condition
	public boolean hasWon() {
		return horizontalWin() || verticalWin() || upDiagWin() || downDiagWin();
	}

	// Check horizontal wins
	public boolean horizontalWin() {
		boolean player1win = false;
		boolean player2win = false;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (board[i][j].getColor() == Color.WHITE) {
					continue;
				} else {
					Color thisColor = board[i][j].getColor();
					if (i + 3 < width) {
						if (thisColor == board[i + 1][j].getColor()) {
							if (thisColor == board[i + 2][j].getColor()) {
								if (thisColor == board[i + 3][j].getColor()) {
									if (thisColor == Color.RED) {
										player1win = true;
									} else if (thisColor == Color.BLACK) {
										player2win = true;
									}
								}
							}

						}
					}
				}
			}
		}
		// If a player won, return true. Else, return false
		return player1win || player2win;
	}

	// Check vertical wins
	public boolean verticalWin() {
		boolean player1win = false;
		boolean player2win = false;

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (board[i][j].getColor() == Color.WHITE) {
					continue;
				} else {
					Color thisColor = board[i][j].getColor();
					if (j + 3 < height) {
						if (thisColor == board[i][j + 1].getColor()) {
							if (thisColor == board[i][j + 2].getColor()) {
								if (thisColor == board[i][j + 3].getColor()) {
									if (thisColor == Color.RED) {
										player1win = true;
									} else if (thisColor == Color.BLACK) {
										player2win = true;
									}
								}
							}
						}
					}
				}

			}
		}
		return player1win || player2win;
	}

	// Check upwards diagonal win
	public boolean upDiagWin() {
		boolean player1win = false;
		boolean player2win = false;

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (board[i][j].getColor() == Color.WHITE) {
					continue;
				} else {
					Color thisColor = board[i][j].getColor();
					if (j - 3 >= 0 && i + 3 < width) {
						if (thisColor == board[i + 1][j - 1].getColor()) {
							if (thisColor == board[i + 2][j - 2].getColor()) {
								if (thisColor == board[i + 3][j - 3].getColor()) {
									if (thisColor == Color.RED) {
										player1win = true;
									} else if (thisColor == Color.BLACK) {
										player2win = true;
									}
								}
							}
						}
					}
				}

			}
		}
		return player1win || player2win;
	}

	// Check downwards diagonal win
	public boolean downDiagWin() {
		boolean player1win = false;
		boolean player2win = false;

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (board[i][j].getColor() == Color.WHITE) {
					continue;
				} else {
					Color thisColor = board[i][j].getColor();
					if (j + 3 < height && i + 3 < width) {
						if (thisColor == board[i + 1][j + 1].getColor()) {
							if (thisColor == board[i + 2][j + 2].getColor()) {
								if (thisColor == board[i + 3][j + 3].getColor()) {
									if (thisColor == Color.RED) {
										player1win = true;
									} else if (thisColor == Color.BLACK) {
										player2win = true;
									}
								}
							}
						}
					}
				}

			}
		}
		return player1win || player2win;
	}

	// This method counts if there are no moves left (a draw)
	public boolean isFull() {
		int count = 0;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (board[i][j].getColor() != Color.WHITE) {
					count++;
				}
			}
		}
		return count == 42;
	}

	// Get a piece from the board
	public Circle get(int xcoord, int ycoord) {
		Circle toReturn = null;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (i == xcoord && j == ycoord) {
					toReturn = board[i][j];
				}
			}
		}
		return toReturn;
	}

	// testing method
	public void addTestPiece(Circle piece, int x, int y) {
		board[x][y] = piece;
	}

	// testing method
	public void fillBoard() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				board[i][j].setColor(Color.RED);
			}
		}
	}

	// getter methods
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Circle[][] getBoard() {
		return board;
	}

	// draws the game board
	public void draw(Graphics g) {
		// draw the board of circles
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				board[i][j].draw(g);
			}
		}

		// set pen color to black
		g.setColor(Color.BLACK);

		// Draw Vertical lines of grid
		g.drawLine(100, 0, 100, 600);
		g.drawLine(200, 0, 200, 600);
		g.drawLine(300, 0, 300, 600);
		g.drawLine(400, 0, 400, 600);
		g.drawLine(500, 0, 500, 600);
		g.drawLine(600, 0, 600, 600);

		// Draw Horizontal lines of grid
		g.drawLine(0, 100, 700, 100);
		g.drawLine(0, 200, 700, 200);
		g.drawLine(0, 300, 700, 300);
		g.drawLine(0, 400, 700, 400);
		g.drawLine(0, 500, 700, 500);
	}

}
