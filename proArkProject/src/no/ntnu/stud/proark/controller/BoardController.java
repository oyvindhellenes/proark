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
	
	public void tileClicked(ViewGroup parent, int position) {
		Move move = board.makeMove(1, position);
		if (move.isError()) {
			boardView.showText(move.getErrorReason());
		}
		else {
			boardView.updateTile(parent, move.getFrom(), Tile.EMPTY);
			boardView.updateTile(parent, position, board.getTile(position));
		}
	}
	
}
