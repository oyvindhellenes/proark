package no.ntnu.stud.proark.states;

import no.ntnu.stud.proark.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onClickGame(View v){
		Intent intent = new Intent(this, GameActivity.class);
		startActivity(intent);
	}
	public void onClickSettings(View v){
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
	}

}
