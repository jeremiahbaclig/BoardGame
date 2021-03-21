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
		return this.start;
	}
	
	public void setStart(Spot st) {
		this.start = st;
	}
	
	public Spot getEnd() {
		return this.end;
	}
	
	public void setEnd(Spot end) {
		this.end = end;
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