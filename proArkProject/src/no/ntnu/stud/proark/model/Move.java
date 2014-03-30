package no.ntnu.stud.proark.model;

public class Move {

	private int from;
	private int to;
	private boolean error = false;
	private boolean hitWall = false;
	private String errorReason = "";
	
	public Move(int from, int to) {
		this.from = from;
		this.to = to;
	}
	
	public int getFrom() {
		return this.from;
	}
	
	public int getTo() {
		return this.to;
	}
	
	public void setError(boolean error) {
		this.error = error;
	}
	
	public boolean isError() {
		return this.error;
	}
	
	public boolean hitWall() {
		return hitWall;
	}

	public void setHitWall(boolean hitWall) {
		this.hitWall = hitWall;
	}

	public void setErrorReason(String reason) {
		this.errorReason = reason;
	}
	
	public String getErrorReason() {
		return this.errorReason;
	}
	
	/**
	 * Which direction the player was moving when the crash occurred.
	 * 
	 * @return int North=0, East=1, South=2, West=3
	 */
	public char getCrashDirection() {
		if (hitWall) {
			if (Math.abs(from - to) == 1) {
				return to > from ? 'E' : 'W';
			}
			if (Math.abs(from - to) == 6) {
				return to > from ? 'S' : 'N';
			}
		}
		return '-';
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Move)) return false;
		Move move = (Move)obj;
		
		if (this.from == move.from &&
				this.to == move.to) return true;
		if (this.from == move.to &&
				this.to == move.from) return true;
			
		return false;
	}
	
	@Override
	public int hashCode() {
		if (from > to) {
			return (from*1000) + to;
		}
		else {
			return (to*1000) + from;
		}
	}
	
}
