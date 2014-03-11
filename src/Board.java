
public class Board {
	private class Token {
		private static final String BLANK_TOKEN = "-";
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
	
	private static final int DEFAULT_SIZE = 3;	
	Token[][] myBoardLayout;
	int numTokens;
	
	public Board() {
		myBoardLayout = new Token[DEFAULT_SIZE][DEFAULT_SIZE];
		numTokens = 0;
		for (int r = 0; r < DEFAULT_SIZE; r++ ) {
			for (int c = 0; c < DEFAULT_SIZE; c++ ) {
				myBoardLayout[r][c] = new Token();
			}
		}
	}
	
	public boolean setPiece(int idx, String name) {
		idx -= 1;
		if( myBoardLayout[idx/DEFAULT_SIZE][idx%DEFAULT_SIZE].setName(name) ) {
			numTokens++;
			return true;
		}
		return false;
	}
	
	public String getPiece(int idx) {
		idx--;
		return myBoardLayout[idx/DEFAULT_SIZE][idx%DEFAULT_SIZE].getName();
	}
	
	public String getPiece(int row, int col) {
		return myBoardLayout[--row][--col].getName();
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
		return DEFAULT_SIZE;
	}
	
	public boolean isFull () {
		return (numTokens == DEFAULT_SIZE * DEFAULT_SIZE);
	}
	
}
