import java.util.Scanner;


public abstract class Player {
	Scanner consoleReader;
	Board myBoard;
	String tokenString;
	
	public Player(String name, Scanner consoleReader, Board myBoard) {
		tokenString = name;
		this.consoleReader = consoleReader;
		this.myBoard = myBoard;
	}
	
	public abstract void makeMove();	
}