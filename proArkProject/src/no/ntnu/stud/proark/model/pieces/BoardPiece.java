package no.ntnu.stud.proark.model.pieces;

public abstract class BoardPiece {
	
	//Position
	int position;
	

	public BoardPiece(int pos) {
		this.position = pos;
	}
	
	public int getPositionX() {
		return position;
	}
	
	public void setPositionY(int pos) {
		this.position = pos;
	}
}
