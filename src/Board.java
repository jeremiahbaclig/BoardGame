public class Board { 
	Spot[][] boxes = new Spot[11][11];
	public static int[][] indexes = new int[11][11];

	public Board() 
	{ 
		this.resetBoard(); 
	} 

	public Spot getBox(int x, int y) throws Exception 
	{ 

		if (x < 0 || x > 10 || y < 0 || y > 10) { 
			throw new Exception("Index out of bound"); 
		} 

		return this.boxes[x][y]; 
	} 


	public void resetBoard() 
	{ 
		// initialize black pieces 
		for(int i=0; i<2; i++) {
			for(int j=0; j<11; j++) {
				Piece currentPiece = new BasePiece(false);
				boxes[i][j] = new Spot(i, j, currentPiece);
		        boxes[i][j].setPiece(currentPiece);
		        indexes[i][j] = 2;
			}
		}

		// initialize white pieces 
		for(int i=9; i<11; i++) {
			for(int j=0; j<11; j++) {
				Piece currentPiece = new BasePiece(true);
				boxes[i][j] = new Spot(i, j, currentPiece);
		        boxes[i][j].setPiece(currentPiece);
		        indexes[i][j] = 1;
			}
		}

		// initialize remaining boxes without any piece 
		for (int i = 2; i < 9; i++) { 
			for (int j = 0; j < 11; j++) { 
				boxes[i][j] = new Spot(i, j, null);
				indexes[i][j] = 0;
			}
		} 
	} 
} 