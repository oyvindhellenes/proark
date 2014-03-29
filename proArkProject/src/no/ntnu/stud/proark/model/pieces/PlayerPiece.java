package no.ntnu.stud.proark.model.pieces;

public class PlayerPiece extends BoardPiece {

	private String name;
	private int playerNumber;
	private int position;
	
	public PlayerPiece(String n, int pos) {
		super(pos);
		this.name = n;
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
