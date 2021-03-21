public class Move { 
	private Player player; 
	private Spot start; 
	private Spot end; 
	private Piece pieceMoved; 
	private Piece pieceKilled; 

	public Move(Player player, Spot start, Spot end) 
	{ 
		this.player = player; 
		this.start = start; 
		this.end = end; 
		this.pieceMoved = start.getPiece(); 
	}

	public Spot getStart() {
		return start;
	}
	
	public Spot getEnd() {
		return end;
	}
	
	public void setPieceKilled(Piece toKill) {
		toKill.setKilled(true);
	}
	
	public enum GameStatus { 
	    ACTIVE, 
	    BLACK_WIN, 
	    WHITE_WIN, 
	    FORFEIT
	} 
} 