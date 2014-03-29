package no.ntnu.stud.proark.model.pieces;

public abstract class BoardPiece {
	
	//Position
	int position;
	

	public BoardPiece(int position) {
		this.position = position;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
}
