package no.ntnu.stud.proark.view;

import no.ntnu.stud.proark.model.GameBoard;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class BoardView extends View {
	
	private GameBoard board;
	
	public BoardView(GameBoard b, Context context) {
		super(context);
		this.board = b;
	}
	
	@Override
	public void onDraw(Canvas canvas){
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		canvas.drawRect(this.board.getRect(), paint);
	}

}
