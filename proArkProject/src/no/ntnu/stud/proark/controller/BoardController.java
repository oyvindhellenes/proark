package no.ntnu.stud.proark.controller;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;
import no.ntnu.stud.proark.R;
import no.ntnu.stud.proark.model.GameBoard;
import no.ntnu.stud.proark.model.Move;
import no.ntnu.stud.proark.model.Tile;
import no.ntnu.stud.proark.view.BoardView;

public class BoardController {
	
	private final GameBoard board;
	private final BoardView boardView;
	
	private int turn = 1;
	
	public BoardController(GameBoard board, BoardView boardView) {
		this.board = board;
		this.boardView = boardView;
	}
	
	public void startGame() {
		
	}
	
	public boolean isValidMove(Move move) {
		if (move == null) {
			return false;
		}		
		return true;
	}

	public boolean collidesWithWall(Move move) {
		if (board.getWalls().contains(move)) {
			return true;
		}
		return false;
	}
	
	public void tileClicked(ViewGroup parent, int position) {
		Move move = board.makeMove(1, position);
		if (!isValidMove(move)) {
			boardView.showText("Invalid move");
		}
		else if (collidesWithWall(move)) {
			boardView.showText("Found a wall");
		}
		else {
			boardView.updateTile(parent, move.getFrom(), Tile.EMPTY);
			boardView.updateTile(parent, position, board.getTile(position));
		}
	}
	
}
