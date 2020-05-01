import java.util.*;

public class Game {
    
	final static String ERROR = "Sorry, that is not a valid move, please re-enter a new move.";
	
	Player player1;
	Player player2;
	static Board board = new Board();
	
	Game(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		board = new Board();
		board.start();
		board.render();
	}
	
	public boolean isWon(char piece) { //checks to see if a player has won
		int i = 23;
		while (!board.stacks[i].contains(piece)) { //checks if there are no more pieces on the board 
			i--;
			if (i == -1) {
				return true;
			}
		}
		return false;
	}
	
	public static void takeTurn(Player player) {
		String name = player.name;
		char piece = player.piece;
		
		ArrayList<Integer> rolls = player.roll();
		System.out.printf("%s's (%c) turn, they rolled: %s\nPlease enter your moves one-by-one.\n", name, piece, rolls.toString());
		
		Scanner scanner = new Scanner(System.in);
		
		//check for case where a piece is taken and cannot be played
		int x = 0;
		if (board.stacks[24].contains(piece)) { //if there is a piece taken
			if (piece == 'O') {
				for (int i = 0; i < rolls.size(); i++) {
					if (board.stacks[24 - rolls.get(i)].size() > 1 && !board.stacks[24 - rolls.get(i)].contains(piece)) {
					    x++;	
					}
				}
				if (x == rolls.size()) { //if there are no available spikes
					while (rolls.size() > 0) { //end turn
						rolls.remove(0);
					}
					System.out.printf("%s is unable to play./n", name);
				}
			}
			else {
				for (int i = 0; i < rolls.size(); i++) {
					if (board.stacks[rolls.get(i)].size() > 1 && !board.stacks[rolls.get(i)].contains(piece)) {
					    x++;	
					}
				}
				if (x == rolls.size()) { //if there are no available spikes
					while (rolls.size() > 0) { //end turn
						rolls.remove(0);
					}
					System.out.printf("%s is unable to play.\n", name);
				}
			}	
		}
		
		while (!rolls.isEmpty()) {
			System.out.printf("Enter move:\n");
			String line = scanner.nextLine(); //user input
			
			// special case if game is won with rolls left over
			int i = 23;
			while (!board.stacks[i].contains(piece)) { //checks if there are no more pieces on the board 
				i--;
				if (i == -1) {
					break; // clear remaining rolls
				}
			}
			
			while(true) {
				
				if (line.length() != 2) { //check line contains enough chars
					System.out.println(ERROR);
					break;
				}
				
				char spike = line.charAt(0); //input parsing
				int move = line.charAt(1) - 48; //convert from ascii to decimal
				
				if (!rolls.contains(move)) { //check number is valid
					System.out.println(ERROR);
					break;
				}
				
				int c = (int) spike; 
				if (c < 65 || c > 88) { //check spike is a valid character
					System.out.println(ERROR);
					break;
				}
				
				int stack = 23 - (c-65); //stupid way to get stack index, cba changing board render
				
				if (piece == 'O') { //adjust direction of travel of 'O' player
					move = -move;
				}
				
				int dest = stack + move; //destination stack
				
				if (board.stacks[24].contains(piece)) { //case for returning a taken piece to play
					if (piece == 'O' && stack < 17) {
						System.out.println("Here");
						break;
					}
					else if (piece == 'X' && stack > 6) {
						System.out.println("Here");
						break;
					}
					else { //if move is valid
						dest = stack;  
						stack = 24; 
					}
				}
				
				//check target spike is not empty or contains opp stack
				if (board.stacks[stack].isEmpty() || !board.stacks[stack].contains(piece)) { //check the selected stack is valid for player
					System.out.println(ERROR);
					break;
				}
				
				//GENERAL PLAY
				if (dest > 0 && dest < 23) {
					if (board.stacks[dest].size() > 1 && !board.stacks[dest].contains(piece)) { //returns ERROR if player attempts to move to opp stack
						System.out.println(ERROR);
						break;
					}
					else if (board.stacks[dest].size() == 1 && !board.stacks[dest].contains(piece)) { //case for capturing an opp singleton
						board.stacks[24].add(board.stacks[dest].remove(0));
						board.stacks[dest].add(board.stacks[stack].remove(0));
					}
					else { //general case
						board.stacks[dest].add(board.stacks[stack].remove(0));
					}	
				}
				//FINISHING PLAY
				else {
					if (piece == 'X' && move > stack+1) { //check there are no available pieces above target spike (X)
						int count = 0;
						for (int k = stack+1; k < 6; k++) {
							if(board.stacks[k].contains(piece)) {
								count++;
							}
						}
						if (count > 0) { //if there are available pieces, break
							System.out.println(ERROR);
							break;
						}
						else {
							board.stacks[stack].remove(piece); //remove piece from board
						}
					}
					else if (piece == 'O' && move > 24-stack){ //check there are no available pieces above target spike (O)
						int count = 0;
						for (int k = stack-1; k > 17; k--) {
							if(board.stacks[k].contains(piece)) {
								count++;
							}
						}
						if (count > 0) { //if there are available pieces, break
							System.out.println(ERROR);
							break;
						}
						else {
							board.stacks[stack].remove(0); //remove piece from board
						}
					}
					else {
						board.stacks[stack].remove(0); //remove piece from board
					}
				}

				
				rolls.remove(rolls.indexOf(Math.abs(move)));
				board.render();
				
				// special case if game is won with rolls left over
				int w = 23;
				while (!board.stacks[w].contains(piece)) { //checks if there are no more pieces on the board 
					w--;
					if (w == -1) {
						rolls.clear(); // clear remaining rolls
						break;
					}
				}
				
				break;
			}
			 
		}
		
		System.out.printf("End of %s's (%c) turn.\n", name, piece);
		System.out.println();
		
	}
	
	public static void main(String[] args) {
	
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player 1 (O): ");
        String player1 = scanner.nextLine();
        
        System.out.println("Player 2 (X): ");
        String player2 = scanner.nextLine();
        
        while (player1.equals(player2)) {
        	System.out.println("Sorry, player names must be different.\nPlease re-enter a new name for Player 2 (X):");
        	player2 = scanner.nextLine();
        }
        
        
        
        int roll1 = (int) (Math.random() * 6) + 1;
        int roll2 = (int) (Math.random() * 6) + 1;
        
        while (roll1 == roll2) {
        	roll2 = (int) (Math.random() * 6) + 1;
        }
        
        System.out.printf("Auto rolls, %s: %d and %s: %d\n", player1, roll1, player2, roll2);
        
        Player p1; Player p2;
        if (roll2 > roll1) {
        	p1 = new Player(player2, 'X');
        	p2 = new Player(player1, 'O');
        	System.out.printf("%s (X) will play first.\n", p1.name);
        }
        else {
        	p1 = new Player(player1, 'O');
        	p2 = new Player(player2, 'X');
        	System.out.printf("%s (O) will play first.\n", p1.name);
        }
        
        Game game = new Game(p1,p2);
        
        while(true) { // Main gameplay loop
        	takeTurn(p1);
        	if(game.isWon('X') || game.isWon('O')) { // Check for win condition
            	System.out.printf("%s (%c) wins!\n", p1.name, p1.piece);
            	break;
        	}
        	takeTurn(p2);
        	if(game.isWon('X') || game.isWon('O')) { // Check for win condition
            	System.out.printf("%s (%c) wins!\n", p2.name, p2.piece);
            	break;
        	}
        }

        scanner.close();
	}

}
