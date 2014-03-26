package no.ntnu.stud.proark.states;

import no.ntnu.stud.proark.Parameters;
import no.ntnu.stud.proark.R;
import no.ntnu.stud.proark.model.Difficulty;
import no.ntnu.stud.proark.model.GameBoard;
import no.ntnu.stud.proark.model.pieces.GoalPiece;
import no.ntnu.stud.proark.model.pieces.PlayerPiece;
import no.ntnu.stud.proark.view.BoardView;
import android.os.Bundle;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.Menu;
<<<<<<< HEAD
import android.view.MenuItem;
=======
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
>>>>>>> 2778c4856411150924ff3bb72551ea83aa100092

public class GameActivity extends ActionBarActivity {
	
	// Test data
	
	private Difficulty level = Difficulty.EASY;
	private PlayerPiece piece = new PlayerPiece("Jens", 1, 10,10);
	private GoalPiece goal = new GoalPiece(50,50);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		final GridView gridview = (GridView) findViewById(R.id.gridview);
	    gridview.setAdapter(new ImageAdapter(this));

	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        	((ImageView) v).setImageResource(R.drawable.ic_launcher);
	            Toast.makeText(GameActivity.this, "" + position, Toast.LENGTH_SHORT).show();
	        }
	    });
		
//		GameBoard game = new GameBoard(level, goal, piece);
//		BoardView view = new BoardView(game, this);
//
//		setContentView(view);
//		Display display = getWindowManager().getDefaultDisplay();
//		Point size = new Point();
//		display.getSize(size);
//		Parameters.getInstance().setPixelHeight(size.y);
//		Parameters.getInstance().setPixelWidth(size.x);
		
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
