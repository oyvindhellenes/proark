package no.ntnu.stud.proark.view;

import no.ntnu.stud.proark.R;
import no.ntnu.stud.proark.model.Tile;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
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
    
    public void updateTile(ViewGroup parent, int position, Tile tile) {
    	((ImageView) parent.getChildAt(position)).setImageResource(tile.getTileImage());
    }
    
    public void showText(String text) {
    	Toast.makeText(mainContext.getApplicationContext(), text, Toast.LENGTH_LONG).show();
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
