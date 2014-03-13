import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	private static final char player1TokenString = 'O';
	private static final char player2TokenString = 'X';
	Board myBoard;
	boolean gameOver;
	Player player1;
	Player player2;
	boolean player1Move;
	Scanner consoleReader;

	public TicTacToe() {
		gameOver = false;
		myBoard = new Board();
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
			gameOver = checkWinner();
		}
	}

	private void nextMove() {
		if (player1Move) {
			player1.makeMove();
		} else {
			player2.makeMove();
		}
		player1Move = !player1Move;
	}

	private boolean checkWinner() {
		// Oh, it's easier to just check the 8 possible rows? Yea... probably...
		boolean frontDiagWin = !myBoard.isEmpty(0, 0);
		char frontDiagToken = myBoard.getCell(0, 0);
		boolean backDiagWin = !myBoard.isEmpty(0, Board.BOARD_SIZE - 1);
		char backDiagToken = myBoard.getCell(0, Board.BOARD_SIZE - 1);
		for (int i = 0; i < Board.BOARD_SIZE; i++) {
			// check Diagonals
			frontDiagWin = myBoard.getCell(i, i) == frontDiagToken ? frontDiagWin
					: false;
			backDiagWin = myBoard.getCell(i, (Board.BOARD_SIZE - 1) - i) == backDiagToken ? backDiagWin
					: false;
			// check Rows and Columns
			boolean rowWin = !myBoard.isEmpty(i, 0);
			char startRowToken = myBoard.getCell(i, 0);
			boolean colWin = !myBoard.isEmpty(0, i);
			char startColToken = myBoard.getCell(0, i);
			for (int j = 0; j < Board.BOARD_SIZE; j++) {
				rowWin = myBoard.getCell(i, j) == startRowToken ? rowWin
						: false;
				colWin = myBoard.getCell(j, i) == startColToken ? colWin
						: false;
			}
			if (rowWin || colWin) {
				System.out.println("\""
						+ (rowWin ? startRowToken : startColToken)
						+ "\" wins the game!");
				return true;
			}
		}
		if (frontDiagWin || backDiagWin) {
			System.out.println("\""
					+ (frontDiagWin ? frontDiagToken : backDiagToken)
					+ "\" wins the game!");
			return true;
		}
		return false;
	}

	public static void main(String args[]) {
		new TicTacToe();
	}
}
