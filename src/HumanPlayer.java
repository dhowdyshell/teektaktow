import java.util.Scanner;


public class HumanPlayer extends Player{
	
	public HumanPlayer(String name, Scanner consoleReader, Board myBoard) {
		super(name, consoleReader, myBoard);
	}
	
	public void makeMove() {
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
			if (myBoard.setPiece(input, this.tokenString)) {
				break;
			} else {
				System.out.println("Invalid move. Try again.");
			}
		}		
	}

}
