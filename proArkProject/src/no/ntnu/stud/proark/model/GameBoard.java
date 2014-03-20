package no.ntnu.stud.proark.model;

import no.ntnu.stud.proark.model.pieces.GoalPiece;
import no.ntnu.stud.proark.model.pieces.PlayerPiece;


public class GameBoard {

	private Difficulty level;
	private GoalPiece goalPiece;
	private PlayerPiece playerPiece;

	public GameBoard(Difficulty level, GoalPiece goalPiece, PlayerPiece playerPiece){
		this.level = level;
		this.goalPiece = goalPiece;
		this.playerPiece = playerPiece;
	}
	
}
