package no.ntnu.stud.proark.states;

import no.ntnu.stud.proark.Parameters;
import no.ntnu.stud.proark.R;
import no.ntnu.stud.proark.R.layout;
import no.ntnu.stud.proark.R.menu;
import no.ntnu.stud.proark.controller.BoardController;
import no.ntnu.stud.proark.model.Difficulty;
import no.ntnu.stud.proark.model.GameBoard;
import no.ntnu.stud.proark.view.BoardView;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.Display;
import android.view.Menu;
import android.view.WindowManager;

public class GameActivity extends Activity {
	
	// Test data
	
	private Difficulty level = Difficulty.EASY;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		GameBoard board = new GameBoard(level);
		BoardController boardController = new BoardController(board);
		BoardView boardView = new BoardView(board, this);
		
		setContentView(boardView);
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		Parameters.getInstance().setPixelHeight(size.y);
		Parameters.getInstance().setPixelWidth(size.x);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

}
