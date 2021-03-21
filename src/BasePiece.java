public class BasePiece extends Piece { 
	public BasePiece (boolean white) 
	{ 
		super(white); 
	} 

	@Override
	public boolean canMove(Spot start, Spot end) // Board board,  
	{ 
		try {
			if (end.getPiece().isWhite() == this.isWhite()) { // Can't move the piece to spot with same color piece 
				return false; 
			}
		} catch (NullPointerException ex) { // space is empty
			int x = Math.abs(start.getX() - end.getX());
			int y = Math.abs(start.getY() - end.getY());
			return Math.abs(x) == 2 && y == 0;
		}
		
		// falls through if not empty and not same side
		int x = Math.abs(start.getX() - end.getX());
		int y = Math.abs(start.getY() - end.getY());
		if(Math.abs(x) == 2 && y == 0) {
			end.getPiece().setKilled(true);
			return true;
		} else {
			return false;
		}
	} 
}
