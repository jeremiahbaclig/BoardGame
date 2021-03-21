import java.util.*;

class Main {
	
	private static void printBoard(Board currentBoard) {
	    int A = 65;
	    int counter = 0;
	
	    try{
	      for(int i=0; i<11; i++){
	        System.out.println();  
	        for(int j=0; j<12; j++){
	          if(j == 11){
	            System.out.print("\t" + counter + "\n");
	            counter++;
	          }
		      else{
		        System.out.print(currentBoard.indexes[i][j] + "\t");
		      }
	        }
	      }
	    } catch(Exception e){
	    	System.out.println(e);
	    }
	
		System.out.println("\n");
		for(int i=0; i<11; i++){
		  System.out.printf("%c(%d)\t", A+i, i);
		}
	}
	public static void main(String[] args) {
		Board board = new Board();
		printBoard(board);

		Human player1 = new Human(true);
		Human player2 = new Human(false);
		Human players[] = new Human[2];
		
		players[0] = player1;
		players[1] = player2;
		
		List<Move> moves = new ArrayList<Move>();
		
		Game game = new Game(players, board, player1, Move.GameStatus.ACTIVE, moves);
		Scanner scanner = new Scanner(System.in);
		game.initialize();
		while(true) {
			
			try {
				System.out.print("\n\nEnter piece [x, y]: ");
				String coords[] = scanner.nextLine().split(" ");
				
				int x = Integer.parseInt(coords[1]);
				int y = Integer.parseInt(coords[0]);
				
				// Spot currentChoice = new Spot(x, y, board.boxes[x][y].getPiece());
				
				System.out.print("\nEnter move [x, y]: ");
				String toCoords[] = scanner.nextLine().split(" ");
				
				int toX = Integer.parseInt(toCoords[1]);
				int toY = Integer.parseInt(toCoords[0]);
				
				game.playerMove(game.getTurn(), x, y, toX, toY);
				
				printBoard(board);
			} catch (Exception e) {
				System.out.println("\nINPUT ERROR!! Try again.");
			}
			
		}
		
	  }
}