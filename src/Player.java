import java.util.Scanner;


public abstract class Player {
	Scanner consoleReader;
	Board myBoard;
	char token;
	
	public Player(char token, Scanner consoleReader, Board myBoard) {
		this.token = token;
		this.consoleReader = consoleReader;
		this.myBoard = myBoard;
	}
	
	public abstract void makeMove();	
}