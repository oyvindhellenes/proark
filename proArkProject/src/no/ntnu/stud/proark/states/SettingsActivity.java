package no.ntnu.stud.proark.states;

import no.ntnu.stud.proark.Parameters;
import no.ntnu.stud.proark.R;
import no.ntnu.stud.proark.R.layout;
import no.ntnu.stud.proark.R.menu;
import no.ntnu.stud.proark.model.Difficulty;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
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
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				Parameters.getInstance().setPlayerOne(s.toString());
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}	
			@Override
			public void afterTextChanged(Editable s) {}
		});
		playerTwoInput.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				Parameters.getInstance().setPlayerTwo(s.toString());
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}	
			@Override
			public void afterTextChanged(Editable s) {}
		});
		
		playerOneInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
	        @Override
	        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
	            if (actionId == EditorInfo.IME_ACTION_DONE) {
	            	playerOneInput.clearFocus();
	            	InputMethodManager imm = (InputMethodManager)getSystemService(
	            		      Context.INPUT_METHOD_SERVICE);
	            		imm.hideSoftInputFromWindow(playerOneInput.getWindowToken(), 0);
	                return true;
	            }
	            return false;
	        }
	    });
		playerTwoInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
	        @Override
	        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
	            if (actionId == EditorInfo.IME_ACTION_DONE) {
	            	playerTwoInput.clearFocus();
	            	InputMethodManager imm = (InputMethodManager)getSystemService(
	            		      Context.INPUT_METHOD_SERVICE);
	            		imm.hideSoftInputFromWindow(playerTwoInput.getWindowToken(), 0);
	                return true;
	            }
	            return false;
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
