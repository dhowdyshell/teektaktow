import java.util.Random;
import java.util.Scanner;


public class ComputerPlayer extends Player {
	public ComputerPlayer(String name, Scanner consoleReader, Board myBoard) {
		super(name, consoleReader, myBoard);
	}
	
	public void makeMove() {
		for(;;) {
			int myChoice = (new Random()).nextInt(myBoard.size()*myBoard.size())+1;
			if (myBoard.setPiece(myChoice, this.tokenString)) {
				break;
			}
		}
		//int[][] 
	}
}
