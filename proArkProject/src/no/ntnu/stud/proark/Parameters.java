package no.ntnu.stud.proark;

public class Parameters {
	public static Parameters instance = null;
	
	public Parameters(){
		
	}
	
	public static Parameters getInstance () {
		if (instance == null) {
			instance = new Parameters();
		}
		return instance;
	}
}
