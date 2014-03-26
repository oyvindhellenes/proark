package no.ntnu.stud.proark.model;

public class Move {

	int tile1X, tile1Y, tile2X, tile2Y;
	
	public Move(int fromX, int fromY, int toX, int toY) {
		this.tile1X = fromX;
		this.tile1Y = fromY;
		this.tile2X = toX;
		this.tile2Y = toY;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Move)) return false;
		Move move = (Move)o;
		
		if (this.tile1X == move.tile1X && 
			this.tile1Y == move.tile1Y &&
			this.tile2X == move.tile2X &&
			this.tile2Y == move.tile2Y) return true;
		if (this.tile2X == move.tile1X && 
			this.tile2Y == move.tile1Y &&
			this.tile1X == move.tile2X &&
			this.tile1Y == move.tile2Y) return true;
		
		return false;
	}
	
}
