public class Board { 
	Spot[][] boxes = new Spot[9][9];
	public static int[][] indexes = new int[9][9];

	public Board() 
	{ 
		this.resetBoard(); 
	} 

	public Spot getBox(int x, int y) throws Exception 
	{ 

		if (x < 0 || x > 8 || y < 0 || y > 8) { 
			throw new Exception("Index out of bound"); 
		} 

		return this.boxes[x][y]; 
	} 


	public void resetBoard() 
	{ 
		// initialize black pieces 
		for(int i=0; i<2; i++) {
			for(int j=0; j<9; j++) {
				Piece currentPiece = new BasePiece(false);
				boxes[i][j] = new Spot(i, j, currentPiece);
		        boxes[i][j].setPiece(currentPiece);
		        indexes[i][j] = 2;
			}
		}

		// initialize white pieces 
		for(int i=7; i<9; i++) {
			for(int j=0; j<9; j++) {
				Piece currentPiece = new BasePiece(true);
				boxes[i][j] = new Spot(i, j, currentPiece);
		        boxes[i][j].setPiece(currentPiece);
		        indexes[i][j] = 1;
			}
		}

		// initialize remaining boxes without any piece 
		for (int i = 2; i < 7; i++) { 
			for (int j = 0; j < 9; j++) { 
				boxes[i][j] = new Spot(i, j, null);
				indexes[i][j] = 0;
			}
		} 
	} 
} 