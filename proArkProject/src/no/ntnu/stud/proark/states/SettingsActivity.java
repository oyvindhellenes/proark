package no.ntnu.stud.proark.states;

import no.ntnu.stud.proark.Parameters;
import no.ntnu.stud.proark.R;
import no.ntnu.stud.proark.R.layout;
import no.ntnu.stud.proark.R.menu;
import no.ntnu.stud.proark.model.Difficulty;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class SettingsActivity extends Activity {

	private RadioGroup radioGroup;
	private EditText playerOneInput;
	private EditText playerTwoInput;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		radioGroup = (RadioGroup) findViewById(R.id.radioGroupDifficulty);
		playerOneInput = (EditText) findViewById(R.id.editPlayerOneName);
		playerTwoInput = (EditText) findViewById(R.id.editPlayerTwoName);
		playerOneInput.setText(Parameters.getInstance().getPlayerOne());
		playerTwoInput.setText(Parameters.getInstance().getPlayerTwo());
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
			}
		});
		
		playerOneInput.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				Parameters.getInstance().setPlayerOne(s.toString());
			}	
			@Override
			public void afterTextChanged(Editable s) {}
		});
		playerTwoInput.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				Log.v("Settings1", Parameters.getInstance().getPlayerOne());
				Log.v("Settings2", Parameters.getInstance().getPlayerTwo());
				Parameters.getInstance().setPlayerTwo(s.toString());
			}	
			@Override
			public void afterTextChanged(Editable s) {}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);	
		return true;
	}

}
