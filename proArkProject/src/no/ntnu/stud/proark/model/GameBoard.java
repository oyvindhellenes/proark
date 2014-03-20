package no.ntnu.stud.proark.model;

import android.graphics.Rect;
import no.ntnu.stud.proark.model.pieces.GoalPiece;
import no.ntnu.stud.proark.model.pieces.PlayerPiece;


public class GameBoard {

	private Difficulty level;
	private GoalPiece goalPiece;
	private PlayerPiece playerPiece;
	private Rect rect = new Rect(0,250,720,720);

	public GameBoard(Difficulty level, GoalPiece goalPiece, PlayerPiece playerPiece){
		this.level = level;
		this.goalPiece = goalPiece;
		this.playerPiece = playerPiece;
	}
	
	public Rect getRect(){
		return rect;
	}
	
}
