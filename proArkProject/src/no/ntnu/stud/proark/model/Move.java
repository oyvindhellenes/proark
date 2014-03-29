package no.ntnu.stud.proark.model;

public class Move {

	int from, to;
	
	public Move(int from, int to) {
		this.from = from;
		this.to = to;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Move)) return false;
		Move move = (Move)o;
		
		if (this.from == move.from &&
				this.to == move.to) return true;
		if (this.from == move.to &&
				this.to == move.from) return true;
			
		return false;
	}
	
}
