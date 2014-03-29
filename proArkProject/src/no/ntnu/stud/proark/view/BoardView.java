package no.ntnu.stud.proark.view;

import no.ntnu.stud.proark.R;
import no.ntnu.stud.proark.model.GameBoard;
import no.ntnu.stud.proark.model.pieces.PlayerPiece;
import no.ntnu.stud.proark.states.GameActivity;
import no.ntnu.stud.proark.states.ImageAdapter;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class BoardView extends BaseAdapter {
	
	private Context mainContext;

    public BoardView(Context context) {
        mainContext = context;
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
	
    public int playerCrashed(char direction, PlayerPiece player, int position) {
    	switch (direction) {
    		case 'N':
    			return player.getCrashNorth();
    			break;
    		case 'S':
    			break;
    		case 'E':
    			break;
    		case 'W':
    			break;
    	}
    }
    private void updateTile(ViewGroup parent, int position, int tileImage){
    	((ImageView) parent.getChildAt(position)).setImageResource(tileImage);
    }
    
    
    public void updateTile(ViewGroup parent, int position, boolean reset) {
    	if (reset) {
    		((ImageView) parent.getChildAt(position)).setImageResource(R.drawable.ic_tile);
    	}
    	else {
    		((ImageView) parent.getChildAt(position)).setImageResource(R.drawable.ic_launcher);
    	}
        Toast.makeText(mainContext.getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();
        
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

        imageView.setImageResource(R.drawable.ic_tile);
        return imageView;
    }
	
}
