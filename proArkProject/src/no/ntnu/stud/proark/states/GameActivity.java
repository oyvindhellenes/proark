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
import android.graphics.Point;
import android.app.Activity;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class GameActivity extends Activity {
	
	// Test data
	
	private Difficulty level = Difficulty.EASY;	
	private BoardView boardView;
	private GameBoard board;
	private BoardController boardController;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		board = new GameBoard(level);
		boardView = new BoardView(this);
		boardController = new BoardController(board, boardView);
		
		final GridView gridView = (GridView) findViewById(R.id.gridview);
		gridView.setAdapter(new ImageAdapter(this));

	    gridView.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	        	boardController.tileClicked(view, position);
	        }
	    });
		
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int itemId = item.getItemId();
		
		if(itemId == R.id.action_settings) {
			//TODO
		}
		return super.onOptionsItemSelected(item);
		
	}
	

}
