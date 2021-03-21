import java.util.List;
import java.util.concurrent.TimeUnit;

public class Game { 
	private Player[] players; 
	private Board board; 
	private Player currentTurn; 
	private Move.GameStatus status; 
	private List<Move> movesPlayed; 

	public Game(Player[] players, Board board, Player currentTurn, Move.GameStatus status, List<Move> movesPlayed) {
		this.players = players;
		this.board = board;
		this.currentTurn = currentTurn;
		this.status = status;
		this.movesPlayed = movesPlayed;
	}
	
	public void initialize() 
	{
		board.resetBoard();

		if (this.players[0].isWhiteSide()) { 
			this.currentTurn = this.players[0];
			System.out.println("\n\nTURN: PLAYER 1.");
		} 
		else { 
			this.currentTurn = this.players[1]; 
			System.out.println("\n\nTURN: PLAYER 2.");
		} 

		movesPlayed.clear(); 
	} 

	public boolean isEnd() 
	{ 
		return this.getStatus() != Move.GameStatus.ACTIVE; 
	} 

	public Move.GameStatus getStatus() 
	{ 
		return this.status; 
	} 

	public void setStatus(Move.GameStatus status) 
	{ 
		this.status = status; 
	} 

	public Player getTurn() {
		return this.currentTurn;
	}
	
	public boolean playerMove(Player player, int startX, int startY, int endX, int endY) 
	{ 
		try {
			Spot startBox = board.getBox(startX, startY); 
			Spot endBox = board.getBox(endX, endY); 
			Move move = new Move(player, startBox, endBox);
			
			if(this.makeMove(move, player)) {
				this.board.boxes[endX][endY].setPiece(this.board.boxes[startX][startY].getPiece());
				this.board.boxes[startX][startY].setPiece(null);
				
				if(player.isWhiteSide())
					Board.indexes[endX][endY] = 1;
				else
					Board.indexes[endX][endY] = 2;
				
				Board.indexes[startX][startY] = 0;
				
				return true;
			} else {
				System.out.println("\nINVALID CHOICE FOR PLACEMENT.");
				return false;
			}
 
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return false;
	} 

	private boolean makeMove(Move move, Player player) 
	{ 
		Piece sourcePiece = move.getStart().getPiece(); 
		if (sourcePiece == null) {
			System.out.println("\nINVALID CHOICE.\nNo friendly piece there.");
			return false; 
		} 

		// valid player 
		if (player != currentTurn) { 
			return false; 
		} 

		if (sourcePiece.isWhite() != player.isWhiteSide()) { 
			System.out.println("\nINVALID CHOICE FOR PLACEMENT.\nFriendly piece on that square.");
			return false; 
		} 

		// valid move? 
		if (!sourcePiece.canMove(move.getStart(), move.getEnd())) {
			System.out.println("\nINVALID CHOICE FOR PLACEMENT.");
			return false; 
		} 

		// kill? 
		Piece destPiece = move.getEnd().getPiece(); 
		if (destPiece != null) { 
			System.out.printf("\nCAPTURED PIECE ON: [%d, %d]\n", move.getStart().getX(), move.getStart().getY());
			destPiece.setKilled(true); 
			move.setPieceKilled(destPiece); 
		} 

		// store the move 
		movesPlayed.add(move); 

		// move piece from the start box to end box 
		move.getEnd().setPiece(move.getStart().getPiece()); 
		move.getStart().setPiece(null); 

		if (destPiece != null && destPiece instanceof PromotedOne) { 
			if (player.isWhiteSide()) { 
				this.setStatus(Move.GameStatus.WHITE_WIN); 
				try {
					TimeUnit.SECONDS.sleep(1);
					initialize();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} 
			else { 
				this.setStatus(Move.GameStatus.BLACK_WIN); 
				try {
					TimeUnit.SECONDS.sleep(1);
					initialize();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} 
		}

		// set the current turn to the other player 
		if (this.currentTurn == players[0]) { 
			this.currentTurn = players[1];
		} 
		else { 
			this.currentTurn = players[0]; 
		} 
		
		if(getTurn() == players[0]) {
			System.out.println("\nTURN: PLAYER 1");
		} else {
			System.out.println("\nTURN: PLAYER 2");
		}

		return true; 
	} 
} 
