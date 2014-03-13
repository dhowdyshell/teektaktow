import java.util.Scanner;

public class ComputerPlayer extends Player {

	int[][] boardScore;

	public ComputerPlayer(char token, Scanner consoleReader, Board myBoard) {
		super(token, consoleReader, myBoard);
		// Initialize score: center > corner > edge
		boardScore = new int[][] { { 1, 0, 1 }, { 0, 2, 0 }, { 1, 0, 1 } };
	}

	public void makeMove() {
		int myChoice = 0;
		int maxScore = -1;
		for (int row = 0; row < Board.BOARD_SIZE; row++) {
			for (int col = 0; col < Board.BOARD_SIZE; col++) {
				if (!myBoard.isEmpty(row, col)) {
					boardScore[row][col] = -1; // impossible move
				} else {
					int rowTokens = 0;
					int colTokens = 0;
					int forDiagTokens = 0;
					int backDiagTokens = 0;
					for (int i = 1; i <= 2; i++) {
						rowTokens += myBoard.getCell(row, (col + i)
								% Board.BOARD_SIZE) == this.token ? 1
								: myBoard.getCell(row, (col + i)
										% Board.BOARD_SIZE) == Board.BLANK_TOKEN ? 0
										: -1;
						colTokens += myBoard.getCell((row + i)
								% Board.BOARD_SIZE, col) == this.token ? 1
								: myBoard.getCell((row + i) % Board.BOARD_SIZE,
										col) == Board.BLANK_TOKEN ? 0 : -1;
						if (row == col) {
							forDiagTokens += myBoard.getCell((row + i)
									% Board.BOARD_SIZE, (col + i)
									% Board.BOARD_SIZE) == this.token ? 1
									: myBoard.getCell((row + i)
											% Board.BOARD_SIZE, (col + i)
											% Board.BOARD_SIZE) == Board.BLANK_TOKEN ? 0
											: -1;
						}
						if (row + col + 1 == Board.BOARD_SIZE) {
							forDiagTokens += myBoard.getCell((Board.BOARD_SIZE
									+ row - i)
									% Board.BOARD_SIZE, (col + i)
									% Board.BOARD_SIZE) == this.token ? 1
									: myBoard.getCell(
											(Board.BOARD_SIZE + row - i)
													% Board.BOARD_SIZE,
											(col + i) % Board.BOARD_SIZE) == Board.BLANK_TOKEN ? 0
											: -1;
						}
					}
					// winning move
					if (rowTokens == 2 || colTokens == 2 || forDiagTokens == 2
							|| backDiagTokens == 2) {
						myBoard.setCell(row, col, this.token);
						return;
						// blocking move
					} else if (rowTokens == -2 || colTokens == -2
							|| forDiagTokens == -2 || backDiagTokens == -2) {
						boardScore[row][col] = Integer.MAX_VALUE;
					} else {
						boardScore[row][col] += (rowTokens > 0 ? rowTokens : 0)
								+ (colTokens > 0 ? colTokens : 0)
								+ (forDiagTokens > 0 ? forDiagTokens : 0)
								+ (backDiagTokens > 0 ? backDiagTokens : 0);
					}
				}
				if (boardScore[row][col] > maxScore) {
					maxScore = boardScore[row][col];
					myChoice = row * 3 + col;
				}
			}
		}
		myBoard.setCell(myChoice, this.token);
	}
}
