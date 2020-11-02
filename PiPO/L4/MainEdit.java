//40322
//I don't know if it works coz my Eclipse is doing something weird
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class WinnerWasCalled extends Exception {
}

class Log {

	public static void info() {
		System.out.println("");
	}

	public static void info(String message) {
		System.out.println(message);
	}

}
///////////////////////////////////////////////////////////////////////////////////////////
class Dice {

	public int roll(int max_walls) {
		Random rand = new Random();
		int result = rand.nextInt(max_walls) + 1;

		Log.info("Dice roll: " + result);
		return result;
	}

}
/////////////////////////////////////////////////////////////////////////////////////////////
class Pawn {

	public int position;
	public String name;

	public Pawn(String name) {
		this.position = 0;
		this.name = name;
		
		Log.info(this.name + " joined the game.");
	}
//////////////////////////////////////////////////////////////////////////////////////////////
}

class Board {

	public int max_position, max_turns, max_walls;

	public ArrayList<Pawn> pawns;
	public Dice dice;
	public Pawn winner;
	public int turnsCounter;

	public Board() {
		this.pawns = new ArrayList<Pawn>();
		this.dice = null;
		this.winner = null;
		this.turnsCounter = 0;
	}

	public void performTurn() throws WinnerWasCalled {
		this.turnsCounter++;
		if (this.turnsCounter <= max_turns) {
			Log.info();
			Log.info("Turn " + this.turnsCounter);
	
			for(Pawn pawn : this.pawns) {
				int rollResult = this.dice.roll(max_walls);
				pawn.position += rollResult;
				Log.info(pawn.name + " new position: " + pawn.position);
	
				if(pawn.position >= Board.max_position) {
					this.winner = pawn;
					throw new WinnerWasCalled();
				}
			}
		}else System.out.println("Game over");
	}
//////////////////////////////////////////////////////////////////////////////////////////////
}

public class Main {

	public void main(String[] args) {
		
		int player_nr, walls, max_steps, max_turn;
		Scanner scan = new Scanner(System.in);
		String player_names;
		
		Board board = new Board();
		board.dice = new Dice();
		
		System.out.println("Enter number of dice walls");
		walls = scan.nextInt();
		System.out.println("Enter number of stepping fields on the board");
		max_steps = scan.nextInt();
		System.out.println("Enter max number of turns");
		max_turn = scan.nextInt();
		
		board.max_position=max_steps;
		board.max_walls=walls;
		board.max_turns=max_turn;
		
		while(player_nr >= 2 || player_nr <= 11){
			System.out.println("How many players?");	       
			player_nr = scan.nextInt();	    }
	    for(int i=0; i <= player_nr - 1 ;i++){
	        
	        System.out.println("Enter player" + i+1 + "name");
	        player_names = scan.nextLine();
	        board.pawns.push_back(Pawn(player_names));
	    }

		try {
			while(true) {
				board.performTurn();
			}
		} catch(WinnerWasCalled exception) {
			Log.info();
			if(board.turnsCounter<=board.max_turns) {
				Log.info(board.winner.name + " has won");
			}else 	System.out.println("Game over");	     
		}
	}

}
