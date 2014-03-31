package no.ntnu.stud.proark.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameScore {
	private int roundsLeft;
	private int[] players;
	
	public GameScore(int rounds, int numberOfPlayers){
		this.roundsLeft = rounds;
		this.players = new int[numberOfPlayers];
	}
	public void updateScore(int playerNumber){
		players[playerNumber-1] += 1;
		roundsLeft -= 1;
		System.out.println("Player "+playerNumber+ "s score is now "+players[playerNumber-1]);
	}
	
	public int getRoundsLeft() {
		return this.roundsLeft;
	}
	
	public int getScore(int player){
		return this.players[player-1]; 
	}

}
