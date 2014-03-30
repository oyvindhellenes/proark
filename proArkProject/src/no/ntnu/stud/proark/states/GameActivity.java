package no.ntnu.stud.proark.states;

import no.ntnu.stud.proark.R;
import no.ntnu.stud.proark.controller.BoardController;
import no.ntnu.stud.proark.model.Difficulty;
import no.ntnu.stud.proark.model.GameBoard;
import no.ntnu.stud.proark.model.Tile;
import no.ntnu.stud.proark.view.BoardView;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

@SuppressLint("NewApi")
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
		boardView = BoardView.getInstance();
		boardView.setContext(this);
		boardController = BoardController.getInstance();
		boardController.setBoard(board);
		boardController.setBoardView(boardView);
		
		final GridView gridView = (GridView) findViewById(R.id.gridview);
		gridView.setAdapter(new BoardView(this));

	    gridView.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	        	System.out.println("Parent: " + parent);
	        	System.out.println("Children: " + parent.getChildCount());
	        	boardController.tileClicked(parent, position);
	        }
	    });
	    
	    gridView.getViewTreeObserver().addOnGlobalLayoutListener(
	    	new ViewTreeObserver.OnGlobalLayoutListener() {

			@Override
	        public void onGlobalLayout() {
	        	boardView.updateTile(gridView, 0, Tile.PLAYER_ONE);
	     	    boardView.updateTile(gridView, 35, Tile.PLAYER_TWO);
	     	    boardView.updateTile(gridView, 20, Tile.GOAL);

	            // unregister listener (this is important)
	            gridView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
	        }
	    });
	    
	    boardController.startGame();	    
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
