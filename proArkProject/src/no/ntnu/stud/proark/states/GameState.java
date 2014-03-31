package no.ntnu.stud.proark.states;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

public class GameState extends View {

	
	
	public GameState(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		postInvalidate();
	}
}
