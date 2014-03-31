package no.ntnu.stud.proark.view;

import no.ntnu.stud.proark.R;
import no.ntnu.stud.proark.model.DiceSides;
import no.ntnu.stud.proark.model.Tile;
import no.ntnu.stud.proark.states.GameActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BoardView extends BaseAdapter {
	
	private static BoardView instance = null;
	
	private Context mainContext;

    public BoardView() {
    }
    
    public static BoardView getInstance() {
    	if (instance == null) {
    		instance = new BoardView();
    	}
    	return instance;
    }
    
    public void setContext(Context context) {
    	this.mainContext = context;
    }

    public int getCount() {
        return 36;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }   
    
    public View getViewById(int id) {
    	GameActivity gameActivity = (GameActivity) mainContext;
    	return gameActivity.getViewById(id);
    }
    
    public void updateDice(int diceRoll){
    	ImageView diceView = (ImageView) getViewById(R.id.dice);
    	
    	String diceImage = "DICE_"+diceRoll;
    	int diceImg = DiceSides.valueOf(diceImage).getDiceImage();
    	
    	diceView.setImageResource(diceImg);	
    }
    
    public void updateTile(ViewGroup parent, int position, Tile tile) {
    	((ImageView) parent.getChildAt(position)).setImageResource(tile.getTileImage());
    }
    
    public void updateRoundsLeft(int roundsLeft) {
    	TextView rounds = (TextView) getViewById(R.id.rounds_left);
    	rounds.setText("ROUNDS LEFT: "+roundsLeft);
    }
    
    public void updateScore(int player, int score) {
    	View v = player == 1 ? getViewById(R.id.player_one_score) : getViewById(R.id.player_two_score);
    	TextView rounds = (TextView) v;
    	rounds.setText(""+score);
    }
    
    public void showMoves(ViewGroup parent, int position, int moves, boolean hide) {
    	int column = position % 6;
    	int row = position / 6;
    	Tile tile = hide ? Tile.EMPTY : Tile.AVAILABLE_MOVE;
    	
    	for (int i=1; i <= moves; i++) {
    		// Shows moves downward
    		if (row + i < 6) updateTile(parent, position + (i * 6), tile);
    		// Upward
    		if (row - i >= 0) updateTile(parent, position - (i * 6), tile);
    		// Right
    		if (column + i < 6) updateTile(parent, position + i, tile);
    		// Left
    		if (column - i >= 0) updateTile(parent, position - i, tile);
    	}
    }
    
    public void crashed(ViewGroup parent, int player, int position, char crashDirection) {
    	String tileWanted = player == 1 ? "PLAYER_ONE" : "PLAYER_TWO";
		tileWanted += "_CRASH_" + crashDirection;
		updateTile(parent, position, Tile.valueOf(tileWanted));
    }
   
    public void showAlertMessage(String title, String message) {
    	// r = round finished, t = turn finished, a = alert/oops etc.
    	new AlertDialog.Builder(mainContext)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {}
         })
        .setIcon(R.drawable.goal)
        .show();
    }
    
    public void showAlertMessage(int currentPlayer, char alertType, String title, String message) {
    	// r = round finished, t = turn finished, a = alert/oops etc.
    	new AlertDialog.Builder(mainContext)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {}
         })
        .setIcon(getAlertIcon(currentPlayer, alertType))
        .show();
    }
    
    private int getAlertIcon(int currentPlayer, int alertType){
    	// r = round finished, t = turn finished, a = alert/oops etc.
        int icon =0;
    	switch (alertType) {
		case 'r':
			icon = (R.drawable.goal_alert);
			break;
		case 't':
			if(currentPlayer ==1) {
				icon = (R.drawable.profile_w_pl1);
			} else {
				icon = (R.drawable.profile_w_pl2);
			}
			break;
		case 'a':
			if(currentPlayer == 1) {
				icon = (R.drawable.alert_pl1);
			} else {
				icon = (R.drawable.alert_pl2);
			}
			break;
		default:
	        icon = (R.drawable.empty);
			break;
		}
    	return icon;
    }
    
    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mainContext);
            //imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }
        
        imageView.setImageResource(R.drawable.empty);
        return imageView;
    }
	
    /**
     * Quit to menu
     */
    public void exitToMenu() {
    	((GameActivity) mainContext).exitToMent();
    }
}
