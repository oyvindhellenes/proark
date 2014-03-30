package no.ntnu.stud.proark.controller;

import android.view.ViewGroup;
import no.ntnu.stud.proark.model.GameBoard;
import no.ntnu.stud.proark.model.Move;
import no.ntnu.stud.proark.model.Tile;
import no.ntnu.stud.proark.view.BoardView;

public class BoardController {
	
	private static BoardController instance = null;
	
	private GameBoard board;
	private BoardView boardView;
	
	private int currentPlayer = 1;
	
	public BoardController() {
	}
	
	public static BoardController getInstance() {
		if (instance == null) {
			instance = new BoardController();
		}
		return instance;
	}
	
	public void setBoardView(BoardView boardView) {
		this.boardView = boardView;
	}
	
	public void setBoard(GameBoard board) {
		this.board = board;
	}
	
	public void startGame() {
		
	}
	
	public void showDiceRoll(int number) {
		// kall view her
	}
	
	public void tileClicked(ViewGroup parent, int position) {
		Move move = board.makeMove(currentPlayer, position);
		if (move.isError()) {
			boardView.showText(move.getErrorReason());
			if (move.hitWall()) {
				String tileWanted = currentPlayer == 1 ? "PLAYER_ONE" : "PLAYER_TWO";
				tileWanted += "_CRASH_" + move.getCrashDirection();
				boardView.updateTile(parent, move.getFrom(), Tile.valueOf(tileWanted));
			}
		}
		else {
			boardView.updateTile(parent, move.getFrom(), Tile.EMPTY);
			boardView.updateTile(parent, position, board.getTile(position));
		}
	}
	
}
