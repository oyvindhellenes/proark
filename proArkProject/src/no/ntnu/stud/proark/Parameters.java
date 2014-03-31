package no.ntnu.stud.proark;

import no.ntnu.stud.proark.model.Difficulty;

public class Parameters {
	public static Parameters instance = null;
	
	private int pixelWidth = 0;
	private int pixelHeight = 0;
	private Difficulty difficulty;
	private String playerOne;
	private String playerTwo;
	
	public Parameters(){
		difficulty = Difficulty.EASY;
		playerOne = "Player 1";
		playerTwo = "Player 2";
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

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public String getPlayerOne() {
		return playerOne;
	}

	public void setPlayerOne(String playerOne) {
		this.playerOne = playerOne;
	}

	public String getPlayerTwo() {
		return playerTwo;
	}

	public void setPlayerTwo(String playerTwo) {
		this.playerTwo = playerTwo;
	}
	
	
}
