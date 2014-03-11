
public class Board {
	private static final String BLANK_TOKEN = "-";
	private class Token {
		private String myName;
		
		public Token () {
			this.myName = "-";
		}
		
		public boolean setName(String val) {
			if(BLANK_TOKEN.equals(getName())) {
				myName = val;
				return true;
			}
			return false;
		}
		
		public String getName() {
			return myName;
		}
	}
	
	private final int BOARD_SIZE;	
	Token[][] myBoardLayout;
	int numTokens;
	
	public Board() {
		this(3);
	}
	
	public Board(int size) {
		BOARD_SIZE = size;
		myBoardLayout = new Token[BOARD_SIZE][BOARD_SIZE];
		numTokens = 0;
		for (int r = 0; r < BOARD_SIZE; r++ ) {
			for (int c = 0; c < BOARD_SIZE; c++ ) {
				myBoardLayout[r][c] = new Token();
			}
		}
	}
	
	public boolean setPiece(int idx, String name) {
		idx -= 1;
		return setPiece(idx/BOARD_SIZE + 1, idx%BOARD_SIZE + 1, name);
	}
	
	public boolean setPiece(int row, int col, String name) {
		if( myBoardLayout[--row][--col].setName(name) ) {
			numTokens++;
			return true;
		}
		return false;
	}
	
	public String getPiece(int idx) {
		idx--;
		return getPiece(idx/BOARD_SIZE + 1, idx%BOARD_SIZE +1);
	}
	
	public String getPiece(int row, int col) {
		return myBoardLayout[--row][--col].getName();
	}
	
	public boolean isEmpty(int idx) {
		return BLANK_TOKEN.equals(getPiece(idx));
	}
	
	public boolean isEmpty(int row, int col) {
		return BLANK_TOKEN.equals(getPiece(row, col));
	}
	
	public void printBoard () {
		for (Token[] row : myBoardLayout) {
			for (Token i : row) {
				System.out.print(i.getName());
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public int size () {
		return BOARD_SIZE;
	}
	
	public boolean isFull () {
		return (numTokens == BOARD_SIZE * BOARD_SIZE);
	}
	
}
