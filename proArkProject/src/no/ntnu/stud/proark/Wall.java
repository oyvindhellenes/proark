package no.ntnu.stud.proark;

import java.util.Vector;

public class Wall {
	
	Vector<Integer> position = new Vector<Integer>();
	
	public Wall(Vector<Integer> position) {
		this.position = position;
	}
	
	public Vector<Integer> getPosition(){
		return position;
	}
	
}
