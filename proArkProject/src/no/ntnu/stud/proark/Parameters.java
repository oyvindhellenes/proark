package no.ntnu.stud.proark;

public class Parameters {
	public static Parameters instance = null;
	
	private int pixelWidth = 0;
	private int pixelHeight = 0;
	
	public Parameters(){
		
	}
	
	public static Parameters getInstance () {
		if (instance == null) {
			instance = new Parameters();
		}
		return instance;
	}

	public int getPixelWidth() {
		return pixelWidth;
	}

	public void setPixelWidth(int pixelWidth) {
		this.pixelWidth = pixelWidth;
	}

	public int getPixelHeight() {
		return pixelHeight;
	}

	public void setPixelHeight(int pixelHeight) {
		this.pixelHeight = pixelHeight;
	}
	
}
