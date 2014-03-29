package no.ntnu.stud.proark.model;

import java.util.HashSet;
import java.util.Set;

import android.graphics.Rect;
import no.ntnu.stud.proark.Parameters;
import no.ntnu.stud.proark.model.pieces.BoardPiece;
import no.ntnu.stud.proark.model.pieces.GoalPiece;
import no.ntnu.stud.proark.model.pieces.PlayerPiece;


public class GameBoard {
	
	private Difficulty level;
	private GoalPiece goalPiece;
	private PlayerPiece playerPiece;
	private Rect rect = new Rect(0,250,720,720);
	private Set<Move> walls = new HashSet<Move>();

	public GameBoard(Difficulty level) {
		this.level = level;
	}
	
	public GameBoard(Difficulty level, GoalPiece goalPiece, PlayerPiece playerPiece){
		this.level = level;
		this.goalPiece = goalPiece;
		this.playerPiece = playerPiece;
		this.pieces = new BoardPiece[8][8];
	}
	
	public Set<Move> getWalls() {
		return this.walls;
	}
	
	public Set<Move> getWalls() {
		return this.walls;
	}
	
	public Rect getRect(){
		return rect;
	}
	
}
