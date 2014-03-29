package no.ntnu.stud.proark.model.pieces;

import android.graphics.Paint;



public class PlayerPiece extends BoardPiece {

	private PlayerPieceState playerState;
	private String name;
	private int playerNumber;
	private int position;
	
	public PlayerPiece(String n, int pos) {
		super(pos);
		this.name = n;
		this.playerState = PlayerPieceState.OBSERVE;
	}
	
    public void swapPlayerState(){
        if(playerState == PlayerPieceState.OBSERVE)
            playerState = PlayerPieceState.MOVE;
        else
            playerState = PlayerPieceState.OBSERVE;
    }

    public PlayerPieceState getPlayerState(){
        return this.playerState;
    }
    
    
    public void setName(String n) {
    	this.name = n;
    }
    
    public String getName(){
    	return this.name;
    }
    public int getPlayerNumber(){
    	return this.playerNumber;
    }
    

}
