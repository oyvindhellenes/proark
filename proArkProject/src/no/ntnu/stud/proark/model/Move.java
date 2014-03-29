package no.ntnu.stud.proark.model;

public class Move {

	private int from, to;
	private boolean error = false;
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
	
	public void setErrorReason(String reason) {
		this.errorReason = reason;
	}
	
	public String getErrorReason() {
		return this.errorReason;
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
	
	public int hashCode() {
		if (from > to) {
			return (from*1000) + to;
		}
		else {
			return (to*1000) + from;
		}
	}
	
}
