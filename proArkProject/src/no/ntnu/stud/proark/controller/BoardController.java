package no.ntnu.stud.proark.controller;

import no.ntnu.stud.proark.model.GameBoard;
import no.ntnu.stud.proark.model.Move;

public class BoardController {

	private final GameBoard board;
	
	public BoardController(GameBoard board) {
		this.board = board;
	}
	
	public boolean isValidMove(Move move) {
		return !board.getWalls().contains(move);
	}
	
}
