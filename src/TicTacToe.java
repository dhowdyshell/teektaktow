import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	static Board myBoard;
	static boolean gameOver;
	boolean myMove;
	String myTokenString;
	String opponentTokenString;
	Scanner consoleReader;

	public TicTacToe() {
		gameOver = false;
		myBoard = new Board();
		consoleReader = new Scanner(System.in);
		chooseFirstMove();
		playGame();
	}

	private void chooseFirstMove() {
		// Randomize fist move
		myMove = (new Random()).nextBoolean();
		// "O" always goes first
		if (myMove) {
			myTokenString = "O";
			opponentTokenString = "X";
		} else {
			myTokenString = "X";
			opponentTokenString = "O";
		}
	}

	private void playGame() {
		myBoard.printBoard();
		while (!gameOver) {
			nextMove();
			myBoard.printBoard();
			if ( checkWinner() ) {
				gameOver = true;
			} else if (myBoard.isFull()) {
				gameOver = true;
				System.out.println ("Game is a draw.");
			}
		}
	}

	private void nextMove() {
		if (myMove) {
			int input = 0;
			for (;;) {
				do {
					while (!consoleReader.hasNextInt()) {
						System.out
								.println("Invalid input, integer value only.");
						consoleReader.next();
					}
					input = consoleReader.nextInt();
					if (input < 1 || input > myBoard.size() * myBoard.size()) {
						System.out.println("Invalid input, out of range.");
						input = 0;
					}
				} while (input == 0);
				if (myBoard.setPiece(input, myTokenString)) {
					break;
				} else {
					System.out.println("Invalid move. Try again.");
				}
			}
		} else {
			for(;;) {
				int myChoice = computerMove();
				if ( myBoard.setPiece(myChoice, opponentTokenString) ) {
					break;
				}
			}
		}
		myMove = !myMove;
	}

	private int computerMove() {
//		int myChoice = (new Random()).nextInt(9)+1;
//		System.out.println("chosen val "+ myChoice);
//		return myChoice;
	return 1;
	}
	
	private boolean checkWinner() {
		String frontDiagToken = myBoard.getPiece(1, 1);
		String backDiagToken = myBoard.getPiece(1, myBoard.size());
		boolean frontDiagWin = validToken(frontDiagToken);
		boolean backDiagWin = validToken(backDiagToken);
		for (int i = 1; i <= myBoard.size(); i++) {
			//check Diagonals
			frontDiagWin = myBoard.getPiece(i, i).equals(frontDiagToken) ? frontDiagWin : false;
			backDiagWin = myBoard.getPiece(i, myBoard.size()-(i-1)).equals(backDiagToken) ? backDiagWin : false;
			//check Rows and Columns
			String startRowToken = myBoard.getPiece(i, 1);
			String startColToken = myBoard.getPiece(1, i);
			boolean rowWin = validToken(startRowToken);
			boolean colWin = validToken(startColToken);
			for(int j = 1; j <= myBoard.size(); j++) {
				rowWin = myBoard.getPiece(i, j).equals(startRowToken) ? rowWin : false;
				colWin = myBoard.getPiece(j, i).equals(startColToken) ? colWin : false;
			}
			if (rowWin || colWin) {
				System.out.println("\"" + (rowWin ? startRowToken : startColToken) + "\" wins the game!");
				return true;
			}
		}
		if (frontDiagWin || backDiagWin) {
			System.out.println("\"" + (frontDiagWin ? frontDiagToken : backDiagToken) + "\" wins the game!");
			return true;
		}
		return false;
	}
	
	private boolean validToken(String val) {
		return !"-".equals(val) ? true : false;
	}

	public static void main(String args[]) {
		new TicTacToe();
	}
}
