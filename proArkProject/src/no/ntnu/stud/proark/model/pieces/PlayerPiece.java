package no.ntnu.stud.proark.model.pieces;



public class PlayerPiece extends BoardPiece {

	private PlayerPieceState playerState;
	
	public PlayerPiece() {
		// TODO Auto-generated constructor stub
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

}
