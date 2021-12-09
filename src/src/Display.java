package src;

/*
 * This class will display the board on the console.
 */
public class Display {

	private Card[][] board; // The board in its current state.
	private String[][] display; // This String array will be used to display the
								// cards in the console.

	// CONSTRUCTOR
	public Display(Card[][] b) {
		board = b;
		display = new String[board.length * 3][board.length * 3];
	}

	// Gets the state of the board for display. This will put all the necessary
	// values into
	// the display array.
	private void getState() {
		// Initialize row and column variables.
		int rowVar = 0;
		int colVar = 0;

		// Loop through all the cards on the board.
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[r].length; c++) {
				// If a card is not null, get the values for that card and the
				// ID for the
				// player that owns that card for display.

				if (board[r][c] != null) {
					// First we get the center of the card and put the player's
					// ID
					// for display in the designated space.
					rowVar = r * board.length + 1;
					colVar = c * board[0].length + 1;
					display[rowVar][colVar] = board[r][c].getPlayerID();

					// Using the center above, we get all of the values for the
					// card for display.
					display[rowVar - 1][colVar] = String.valueOf(board[r][c]
							.getUp());
					display[rowVar + 1][colVar] = String.valueOf(board[r][c]
							.getDown());
					display[rowVar][colVar + 1] = String.valueOf(board[r][c]
							.getRight());
					display[rowVar][colVar - 1] = String.valueOf(board[r][c]
							.getLeft());
				}
			}
		}
	}

	// After getting the state of the board; this method will display the board
	// in the
	// console using the String[][].
	public void displayBoard() {

		getState(); // Fills in the String[][]

		String s = ""; // String used to pass into console.

		// Loops over the display array and builds a string to be input into the
		// console.
		for (int r = 0; r < display.length; r++) {
			for (int c = 0; c < display[r].length; c++) {
				// Builds string if it is located in the display.
				if (display[r][c] != null) {
					s += display[r][c] + "\t"; // Adds a tab to the value for a
												// neater display.
				} else {
					s += (" \t"); // If no value is found than it adds an empty
									// space and a tab for a neater display.
				}
			}
			s += "\n\n"; // Skips to lines after each row. It's double spaced!
		}

		System.out.println(s); // Prints the built string.
		System.out.println("---- end turn ------------------------------------------------------" + "\n\n\n\n"); // Used to distinguish
														// player and opponent's
														// turns.
	}

	// Creates a new display, destroying the old one.
	public void clearDisplay() {
		display = new String[board.length * 3][board.length * 3];
	}
}
