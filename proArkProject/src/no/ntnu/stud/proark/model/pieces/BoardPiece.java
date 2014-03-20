package no.ntnu.stud.proark.model.pieces;

public abstract class BoardPiece {
	
	//Position
	int posX;
	int posY;
	

	public BoardPiece(int x, int y) {
		// TODO Auto-generated constructor stub
	}
	
	public int getPositionX() {
		return posX;
	}
	
	public int setPositionY() {
		return posY;
	}
	
	public void setPositionX(int x) {
		this.posX = x;
	}
	
	public void setPositionY(int y) {
		this.posY = y;
	}
}
