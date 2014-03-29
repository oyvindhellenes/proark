package no.ntnu.stud.proark.controller;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;
import no.ntnu.stud.proark.R;
import no.ntnu.stud.proark.model.GameBoard;
import no.ntnu.stud.proark.model.Move;
import no.ntnu.stud.proark.view.BoardView;

public class BoardController {

	private final GameBoard board;
	private final BoardView boardView;
	
	public BoardController(GameBoard board, BoardView boardView) {
		this.board = board;
		this.boardView = boardView;
	}
	
	public boolean isValidMove(Move move) {
		return !board.getWalls().contains(move);
	}
	
	public void tileClicked(View v, int position) {
		boardView.updateTile(v, position);
		
	}
	
}
