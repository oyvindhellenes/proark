package no.ntnu.stud.proark.model;

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
	}
	
	public int getRoundsLeft() {
		return this.roundsLeft;
	}
	
	public int getScore(int player){
		return this.players[player-1]; 
	}

	public int getWinner() {
		int winner = -1;
		int highestScore = -1;
		for (int i=0; i<players.length; i++) {
			if (players[i] > highestScore) {
				winner = i;
			}
		}
		return ++winner;
	}
}
