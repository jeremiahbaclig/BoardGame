import java.util.*;

class Main {
	
	private static void printBoard(Board currentBoard) {
	    int A = 65;
	    int counter = 0;
	
	    try{
	      for(int i=0; i<9; i++){
	        System.out.println();  
	        for(int j=0; j<10; j++){
	          if(j == 9){
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
		for(int i=0; i<9; i++){
		  System.out.printf("%c\t", A+i);
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
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		game.initialize();
		
		while(true) {
			try {
				System.out.print("\n\nEnter piece (ie. A8 A6): ");
				String coords[] = scanner.nextLine().split(" ");
				
				int y = coords[0].charAt(0) - 'A'; 
				int x = coords[0].charAt(1) - '0';
				int toY = coords[1].charAt(0) - 'A';
				int toX = coords[1].charAt(1) - '0';
				
				// Spot currentChoice = new Spot(x, y, board.boxes[x][y].getPiece());
				
				System.out.print("\nEnter move [x, y]: ");
				
				game.playerMove(game.getTurn(), x, y, toX, toY);
				
				printBoard(board);
			} catch (Exception e) {
				System.out.println("\nINPUT ERROR!! Try again.");
			}	
		}
		
	  }
}