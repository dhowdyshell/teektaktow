public class Board {

	public static final char BLANK_TOKEN = '-';
	public static final int BOARD_SIZE = 3;
	private static char[][] myBoardLayout;

	public Board() {
		myBoardLayout = new char[][] { {BLANK_TOKEN,BLANK_TOKEN,BLANK_TOKEN},
				{BLANK_TOKEN,BLANK_TOKEN,BLANK_TOKEN},{BLANK_TOKEN,BLANK_TOKEN,BLANK_TOKEN}};
	}

	public boolean setCell(int idx, char token) {
		return setCell(idx / BOARD_SIZE, idx % BOARD_SIZE, token);
	}

	public boolean setCell(int row, int col, char token) {
		if (BLANK_TOKEN == myBoardLayout[row][col] ) {
			myBoardLayout[row][col] = token;
			return true;
		}
		return false;
	}

	public char getCell(int idx) {
		idx--;
		return getCell(idx / BOARD_SIZE + 1, idx % BOARD_SIZE + 1);
	}

	public char getCell(int row, int col) {
		return myBoardLayout[row][col];
	}

	public boolean isEmpty(int idx) {
		return (getCell(idx) == BLANK_TOKEN);
	}

	public boolean isEmpty(int row, int col) {
		return (getCell(row, col) == BLANK_TOKEN);
	}

	public boolean isMyCell(int idx, String name) {
		return name.equals(getCell(idx));
	}

	public boolean isMyCell(int row, int col, String name) {
		return name.equals(getCell(row, col));
	}

	public void printBoard() {
		for (char[] row : myBoardLayout) {
			for (char i : row) {
				System.out.print(i);
			}
			System.out.println();
		}
		System.out.println();
	}

}
