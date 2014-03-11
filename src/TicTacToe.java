import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	private static final String player1TokenString = "O";
	private static final String player2TokenString = "X";
	Board myBoard;
	boolean gameOver;
	Player player1;
	Player player2;
	boolean player1Move;
	Scanner consoleReader;

	public TicTacToe() {
		gameOver = false;
		myBoard = new Board(3);
		consoleReader = new Scanner(System.in);
		player1 = new HumanPlayer(player1TokenString, consoleReader, myBoard);
		player2 = new ComputerPlayer(player2TokenString, consoleReader, myBoard);
		chooseFirstMove();
		playGame();
	}

	private void chooseFirstMove() {
		player1Move = (new Random()).nextBoolean();
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
		if(player1Move) {
			player1.makeMove();
		} else {
			player2.makeMove();
		}
		player1Move = !player1Move;
	}
	
	private boolean checkWinner() {
		boolean frontDiagWin = myBoard.isEmpty(1, 1);
		String frontDiagToken = myBoard.getPiece(1, 1);
		boolean backDiagWin = myBoard.isEmpty(1, myBoard.size());
		String backDiagToken = myBoard.getPiece(1, myBoard.size());
		for (int i = 1; i <= myBoard.size(); i++) {
			//check Diagonals
			frontDiagWin = myBoard.getPiece(i, i).equals(frontDiagToken) ? frontDiagWin : false;
			backDiagWin = myBoard.getPiece(i, myBoard.size()-(i-1)).equals(backDiagToken) ? backDiagWin : false;
			//check Rows and Columns
			boolean rowWin = myBoard.isEmpty(i, 1);
			String startRowToken = myBoard.getPiece(i, 1);
			boolean colWin = myBoard.isEmpty(1, i);
			String startColToken = myBoard.getPiece(1, i);
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

	public static void main(String args[]) {
		new TicTacToe();
	}
}
