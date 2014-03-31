package no.ntnu.stud.proark.states;

import no.ntnu.stud.proark.Parameters;
import no.ntnu.stud.proark.R;
import no.ntnu.stud.proark.R.layout;
import no.ntnu.stud.proark.R.menu;
import no.ntnu.stud.proark.model.Difficulty;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class SettingsActivity extends Activity {

	private RadioGroup radioGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		radioGroup = (RadioGroup) findViewById(R.id.radioGroupDifficulty);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
					case R.id.easyRadioButton:
						Parameters.getInstance().setDifficulty(Difficulty.EASY);
						break;
					case R.id.mediumRadioButton:
						Parameters.getInstance().setDifficulty(Difficulty.MEDIUM);
						break;
					case R.id.hardRadioButton:
						Parameters.getInstance().setDifficulty(Difficulty.HARD);
						break;
					default:
						break;
				}
				Log.v("Difficulty", ""+checkedId);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);	
		return true;
	}

}
