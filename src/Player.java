import java.util.*;

public class Player {
	
	String name;
	char piece;

	Player(String name, char piece) {
		this.name = name;
		this.piece = piece;
	}
	
	public ArrayList<Integer> roll() { //randomises rolls and returns an array of values
		int roll1 = (int) (Math.random() * 6) + 1;
		int roll2 = (int) (Math.random() * 6) + 1;
		
		if (roll1 == roll2) {
			ArrayList<Integer> rolls = new ArrayList<Integer>(Arrays.asList(roll1, roll1, roll1, roll1));
			return rolls;
		}
		else {
			ArrayList<Integer> rolls = new ArrayList<Integer>(Arrays.asList(roll1, roll2));
			return rolls;
		}
	}
	
}
