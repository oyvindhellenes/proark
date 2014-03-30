package no.ntnu.stud.proark.view;

import no.ntnu.stud.proark.R;
import no.ntnu.stud.proark.model.DiceSides;
import no.ntnu.stud.proark.model.Tile;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.*;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

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
    
    public void updateDice(int diceRoll){
//        ImageView diceImage = (ImageView) mainContext.
//        		.findViewById(R.id.dice);
//        diceImage.setImageResource(R.drawable.dice_3);
    }
    public void updateTile(ViewGroup parent, int position, Tile tile) {
    	((ImageView) parent.getChildAt(position)).setImageResource(tile.getTileImage());
    }
    
    public void showText(String text) {
    	Toast.makeText(mainContext.getApplicationContext(), text, Toast.LENGTH_LONG).show();
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
   
    public void showAlertMessage(ViewGroup parent, String message) {
    	new AlertDialog.Builder(mainContext)
        .setTitle("Ooops...")
        .setMessage(message)
        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
                // continue with delete
            }
         })
        .setIcon(R.drawable.goal)
        .show();
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
	
}
