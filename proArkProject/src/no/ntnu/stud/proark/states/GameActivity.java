package no.ntnu.stud.proark.states;

import no.ntnu.stud.proark.R;
import no.ntnu.stud.proark.controller.BoardController;
import no.ntnu.stud.proark.model.Difficulty;
import no.ntnu.stud.proark.model.GameBoard;
import no.ntnu.stud.proark.model.Tile;
import no.ntnu.stud.proark.view.BoardView;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

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
		
		boardView = BoardView.getInstance();
		boardView.setContext(this);
		boardController = BoardController.getInstance();
		boardController.setBoardView(boardView);
		
		final GridView gridView = (GridView) findViewById(R.id.gridview);
		gridView.setAdapter(boardView);

	    gridView.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
	     	    boardController.startGame(gridView);	  

	            // unregister listener (this is important)
	            gridView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
	        }
	    });

	    // Draw player images in the bottom of the screen
	    final ImageView playerOne = (ImageView) findViewById(R.id.player1);
	    playerOne.setImageResource(R.drawable.profile_b_pl1);
 	    final ImageView playerTwo = (ImageView) findViewById(R.id.player2);
 	    playerTwo.setImageResource(R.drawable.profile_b_pl2);
	}

	public ImageView getViewById(int id) {
		return (ImageView) findViewById(id);

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
