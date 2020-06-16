import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Game implements Runnable {
	
	//fields
	public static String player1;
	public static String player2;
	
	private static final String INSTRUCTIONS = ("You're playing Connect 4! \n"
			+ "Connect 4 is where you place pieces on a 7 x 6 board to try and "
			+ "get 4 in a row. \nYou can have 4 pieces in a row horizontally, vertically "
			+ "or diagnonally. \n"
			+ "In this version, you select the column you're placing a piece in using 'QWERTYU'. \n"
			+ "Red pieces represent player 1, black pieces represent player 2 \n"
			+ "You also have the ability to undo a move, which will reset the board as if \n"
			+ "you never played the prior move. Reset will erase the whole board to its \n"
			+ "starting position."
			+ "\nBest of luck!");
	
	public void run() {
		// Top-level frame in which game components live
		// Be sure to change "TOP LEVEL FRAME" to the name of your game
		final JFrame frame = new JFrame("Rachel's Connect 4");
		frame.setLocation(300, 300);

		// Status panel
		final JPanel status_panel = new JPanel();
		frame.add(status_panel, BorderLayout.SOUTH);
		final JLabel status = new JLabel("Running...");
		status_panel.add(status);

		// Main playing area
		final GameCourt court = new GameCourt(status);
		frame.add(court, BorderLayout.CENTER);

		// Reset button
		final JPanel control_panel = new JPanel();
		frame.add(control_panel, BorderLayout.NORTH);

		// Note here that when we add an action listener to the reset button, we define
		// it as an
		// anonymous inner class that is an instance of ActionListener with its
		// actionPerformed()
		// method overridden. When the button is pressed, actionPerformed() will be
		// called.
		final JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.reset();
			}
		});
		control_panel.add(reset);

		// Undo button
		final JButton undo = new JButton("Undo");
		undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.undo();
			}
		});
		control_panel.add(undo);

		// Low scores button 
		final JButton lowScores = new JButton("Leaderboard");
		lowScores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// write string of file
				String scores = readLowScore("LowScores");
				// Read the file onto the screen
				JOptionPane.showMessageDialog(frame, scores, "Low Scores", JOptionPane.PLAIN_MESSAGE);			
			}
		});
		control_panel.add(lowScores);
		
		// exiting the program
		final JButton quit = new JButton("Quit");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		control_panel.add(quit);
		
		// Opening message
		JOptionPane.showMessageDialog(frame, INSTRUCTIONS, "Welcome to Connect 4!", 
				JOptionPane.INFORMATION_MESSAGE);

		
		// Adding player 1 user name		
		player1 = JOptionPane.showInputDialog("Player 1: Choose username");
		System.out.println(player1);
		
		player2 = JOptionPane.showInputDialog("Player 2: Choose username");
		System.out.println(player2);

		// Put the frame on the screen
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		// Start game
		court.reset();
	}

	//getter methods
	public String getPlayer1() { return player1; }
	public String getPlayer2() { return player2; }
	
	// Method for writing into file
	public static void writeLowScore(String filepath, Circle winner, int leastMoves) {
		String winningUser = "";
		// how to get the name of the winner
		if (winner.getColor() == Color.RED) {
			winningUser = player1; 
		} else {
			winningUser = player2;
		}
		
		if (filepath == null) {
			throw new IllegalArgumentException();
		}
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			//read score 1
			String user1 = br.readLine();
			int score1 = Integer.parseInt(br.readLine());
			//read score 2
			String user2 = br.readLine();
			int score2 = Integer.parseInt(br.readLine());
			//read score 3 
			String user3 = br.readLine();
			int score3 = Integer.parseInt(br.readLine());
			
			br.close();
			
			//if the user is in 1st place
			BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, false));
			System.out.println();
			if (leastMoves < score1 && leastMoves < score2 && leastMoves < score3) {
				bw.write(winningUser + " ");
				bw.newLine();
				bw.write(Integer.toString(leastMoves));
				bw.newLine();
				bw.write(user1 + " ");
				bw.newLine();
				bw.write(Integer.toString(score1));
				bw.newLine();
				bw.write(user2 + " ");
				bw.newLine();
				bw.write(Integer.toString(score2));
			} else if (leastMoves < score2 && leastMoves < score3) {
				bw.write(user1 + " ");
				bw.newLine();
				bw.write(Integer.toString(score1));
				bw.newLine();
				bw.write(winningUser + " ");
				bw.newLine();
				bw.write(Integer.toString(leastMoves));
				bw.newLine();
				bw.write(user2 + " ");
				bw.newLine();
				bw.write(Integer.toString(score2));
			} else if (leastMoves < score3) {
				bw.write(user1 + " ");
				bw.newLine();
				bw.write(Integer.toString(score1));
				bw.newLine();
				bw.write(user2 + " ");
				bw.newLine();
				bw.write(Integer.toString(score2));
				bw.newLine();
				bw.write(winningUser + " ");
				bw.newLine();
				bw.write(Integer.toString(leastMoves));
			}
			bw.close();
		}
		catch (FileNotFoundException e) {
			throw new IllegalArgumentException();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String readLowScore(String filepath) {
		String toRead = "";
		if (filepath == null) {
			throw new IllegalArgumentException();
		}
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			//read first score
			toRead += br.readLine();
			toRead += br.readLine();
			toRead += "\n";
			//read second score
			toRead += br.readLine();
			toRead += br.readLine();
			toRead += "\n";
			//read third score
			toRead += br.readLine();
			toRead += br.readLine();
			toRead += "\n";
			br.close();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return toRead;
		
	}
	
	/**
	 * Main method run to start and run the game. Initializes the GUI elements
	 * specified in Game and runs it. IMPORTANT: Do NOT delete! You MUST include
	 * this in your final submission.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}
	
	
}
