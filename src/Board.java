import java.util.*;

public class Board {
	
	ArrayList<Character>[] stacks = new ArrayList[25];
	//lists 0-23 represent in-play stacks, and list 24 holds "taken" pieces
	
	public void start() { //initialise stacks for start of game
		
		for (int i = 0; i < 25; i++) {
		    stacks[i] = new ArrayList<Character>();	//initialise ArrayLists for each stack
		}
		
		for (int a = 0; a < 2; a++) { //2 stacks
			stacks[0].add('X');
			stacks[23].add('O');
		}
		
		for (int b = 0; b < 5; b++) { //5 stacks
			stacks[5].add('O');
			stacks[11].add('X');
			stacks[12].add('O');
			stacks[18].add('X');
		}
		
		for (int c = 0; c < 3; c++) { //3 stacks
			stacks[7].add('O');
			stacks[16].add('X');
		}
		
	}
	
	public void render() { //prints formatted board to display
		
		ArrayList<Character>[] stacks = this.stacks;
		
		int ucode = 76; //unicode var for easier printing
		System.out.print(" ");
		for (int a = 0; a < 6; a++) { //left top alphabetical refs
			System.out.printf(" %c ", (char) ucode);
			ucode--;
		}
		System.out.print(' '); //central buffer
		for (int a = 0; a < 6; a++) { //right top alphabetical refs
			System.out.printf(" %c ", (char) ucode);
			ucode--;
		}
		System.out.print("\n");
		
		System.out.print('+');  //top border
		for (int a = 0; a < 37; a++) {
			System.out.print('-');
		}
		System.out.print("+\n");
		
		for (int i = 0; i < 5; i++) { //top stacks
			System.out.print('|');
			for (int j = 12; j < 18; j++) { //left top stacks
				if (stacks[j].size() > 5 && i == 4) {
					System.out.printf("(%d)", stacks[j].size());
				}
			    else if (stacks[j].size() > i) {
					System.out.printf(" %c ", stacks[j].get(i));
				}
				else {
					System.out.print("   ");
				}
			}
			System.out.print('|'); //central bar
			for (int j = 18; j < 24; j++) { //right top stacks
				if (stacks[j].size() > 5 && i == 4) {
					System.out.printf("(%d)", stacks[j].size());
				}
			    else if (stacks[j].size() > i) {
					System.out.printf(" %c ", stacks[j].get(i));
				}
				else {
					System.out.print("   ");
				}
			}
			System.out.print("|\n");
		}
		
		//middle section
		System.out.print('|'); //high-middle buffer
		for (int k = 0; k < 18; k++) {
			System.out.print(" ");
		}
		System.out.print('|');
		for (int k = 0; k < 18; k++) {
			System.out.print(" ");
		}
		System.out.print("|\n");
		
		System.out.print('|'); //middle line
		for (int k = 0; k < 13; k++) {
			System.out.print("-");
		}
		// initialise and count each piece in "taken" stack
		int ocount = 0;
		int xcount = 0;
		for (int i = 0; i < stacks[24].size(); i++) {
			if (stacks[24].get(i) == 'O') {
				ocount++;
			}
			else {
				xcount++;
			}
		}
		// if any, display number of each piece currently "taken"
		if (ocount != 0) {
			System.out.printf("O:[%d]", ocount);
		}
		else {
			System.out.print("-----");
		}
		System.out.print('|'); //central bar
		if (xcount != 0) {
			System.out.printf("X:[%d]", xcount);
		}
		else {
			System.out.print("-----");
		}
		
		for (int k = 0; k < 13; k++) {
			System.out.print("-");
		}
		System.out.print("|\n");
		
		System.out.print('|'); //low-middle buffer
		for (int k = 0; k < 18; k++) {
			System.out.print(" ");
		}
		System.out.print('|');
		for (int k = 0; k < 18; k++) {
			System.out.print(" ");
		}
		System.out.print("|\n");
		
		for (int i = 4; i > -1; i--) { //bottom stacks
			System.out.print('|');
			for (int j = 11; j > 5; j--) { //left bottom stacks
			    if (stacks[j].size() > 5 && i == 4) {
			    	System.out.printf("(%d)", stacks[j].size());
			    }
				else if (stacks[j].size() > i) {
					System.out.printf(" %c ", stacks[j].get(i));
				}
				else {
					System.out.print("   ");
				}
			}
			System.out.print('|'); //middle bar
			for (int j = 5; j > -1; j--) { //right bottom stacks
			    if (stacks[j].size() > 5 && i == 4) {
			    	System.out.printf("(%d)", stacks[j].size());
			    }
				else if (stacks[j].size() > i) {
					System.out.printf(" %c ", stacks[j].get(i));
				}
				else {
					System.out.print("   ");
				}
			}
			System.out.print("|\n");
		}
		
		System.out.print('+');  //bottom border
		for (int b = 0; b < 37; b++) {
			System.out.print('-');
		}
		System.out.print("+\n");
		
		ucode = 77;
		System.out.print(" ");
		for (int a = 0; a < 6; a++) { //left bottom alphabetical refs
			System.out.printf(" %c ", (char) ucode);
			ucode++;
		}
		System.out.print(' '); //central buffer
		for (int a = 0; a < 6; a++) { //right bottom alphabetical refs
			System.out.printf(" %c ", (char) ucode);
			ucode++;
		}
		System.out.print("\n");
		
	}

}
