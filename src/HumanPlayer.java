import java.util.Scanner;


public class HumanPlayer extends Player{
	
	public HumanPlayer(char token, Scanner consoleReader, Board myBoard) {
		super(token, consoleReader, myBoard);
	}
	
	public void makeMove() {
		int input = -1;
		for (;;) {
			do {
				while (!consoleReader.hasNextInt()) {
					System.out
							.println("Invalid input, integer value only.");
					consoleReader.next();
				}
				input = consoleReader.nextInt();
				if (input < 0 || input >= Board.BOARD_SIZE * Board.BOARD_SIZE) {
					System.out.println("Invalid input, out of range.");
					input = -1;
				}
			} while (input == -1);
			if (myBoard.setCell(input, this.token)) {
				break;
			} else {
				System.out.println("Invalid move. Try again.");
			}
		}		
	}

}
