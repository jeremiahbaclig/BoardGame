public class PromotedOne extends Piece { 
	public PromotedOne (boolean white) 
	{ 
		super(white); 
	} 

	@Override
	public boolean canMove(Spot start, Spot end) // Board board,  
	{ 
		// Can't move the piece to spot with same color piece 
		if (end.getPiece().isWhite() == this.isWhite()) { 
			return false; 
		}

		int x = Math.abs(start.getX() - end.getX()); 
		int y = Math.abs(start.getY() - end.getY()); 
		return x * y == 2; 
	} 
}
