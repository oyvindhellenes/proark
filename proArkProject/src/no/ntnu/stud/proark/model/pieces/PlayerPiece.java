package no.ntnu.stud.proark.model.pieces;

import android.graphics.Paint;



public class PlayerPiece extends BoardPiece {

	private PlayerPieceState playerState;
	private String name;
	private int color;
	
	public PlayerPiece(String n, int c, int posX, int posY) {
		super(posX, posY);
		this.name = n;
		this.color = c;
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
    
    public Paint setColor(){
        Paint paint = new Paint();
        paint.setColor(this.color);
        return paint;
    }
    
    public void setName(String n) {
    	this.name = n;
    }
    
    public String getName(){
    	return this.name;
    }

}
