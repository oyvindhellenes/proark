package no.ntnu.stud.proark.model;

import no.ntnu.stud.proark.R;

public enum Tile {
	EMPTY(R.drawable.empty),
	GOAL(R.drawable.goal),
	AVAILABLE_MOVE(R.drawable.available_move),
	
	PLAYER_ONE(R.drawable.player1),
	PLAYER_ONE_CRASH_N(R.drawable.crash_n_pl1),
	PLAYER_ONE_CRASH_S(R.drawable.crash_s_pl1),
	PLAYER_ONE_CRASH_E(R.drawable.crash_e_pl1),
	PLAYER_ONE_CRASH_W(R.drawable.crash_w_pl1),
	
	PLAYER_TWO(R.drawable.player2),
	PLAYER_TWO_CRASH_N(R.drawable.crash_n_pl2),
	PLAYER_TWO_CRASH_S(R.drawable.crash_s_pl2),
	PLAYER_TWO_CRASH_E(R.drawable.crash_e_pl2),
	PLAYER_TWO_CRASH_W(R.drawable.crash_w_pl2);
	
	private int imageRef;
	public int getTileImage(){
		return this.imageRef;
	}
	Tile(int imageRef){
		this.imageRef = imageRef;
	}
}
