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
	public int updateScore(int playerNumber){
		players[playerNumber-1] += 1;
		roundsLeft -= 1;
		System.out.println("Player "+playerNumber+ "s score is now "+players[playerNumber-1]);
		return roundsLeft;
	}
	public String getScore(){
		String s = "";

		for (int i = 0; i < players.length; i++) {
			int playerNumber = i+1;
			int score = players[i];
			s += "Player "+playerNumber+": "+score+"\n";
		}
		return s; 
	}

}
