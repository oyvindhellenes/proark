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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends Activity {

	private RadioGroup radioGroup;
	private EditText playerOneInput;
	private EditText playerTwoInput;
	private EditText numberOfRounds;
	private Context pointerHax;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		pointerHax = this;

		radioGroup = (RadioGroup) findViewById(R.id.radioGroupDifficulty);
		playerOneInput = (EditText) findViewById(R.id.editPlayerOneName);
		playerTwoInput = (EditText) findViewById(R.id.editPlayerTwoName);
		numberOfRounds = (EditText) findViewById(R.id.numberOfRounds);
		playerOneInput.setText(Parameters.getInstance().getPlayerOne());
		playerTwoInput.setText(Parameters.getInstance().getPlayerTwo());
		numberOfRounds.setText(""+Parameters.getInstance().getNumberOfRounds());
		if (Parameters.getInstance().getDifficulty() == Difficulty.EASY) {
			((RadioButton)findViewById(R.id.easyRadioButton)).setChecked(true);
		}else if (Parameters.getInstance().getDifficulty() == Difficulty.MEDIUM) {
			((RadioButton)findViewById(R.id.mediumRadioButton)).setChecked(true);
		}else if (Parameters.getInstance().getDifficulty() == Difficulty.HARD) {
			((RadioButton)findViewById(R.id.hardRadioButton)).setChecked(true);
		}
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
		
//		numberOfRounds.addTextChangedListener(new TextWatcher() {
//			@Override
//			public void onTextChanged(CharSequence s, int start, int before, int count) {
//				if (s.length() != 0) {
//					String st = s.toString();
//					if (Integer.parseInt(st) % 2 != 0) {
//						Parameters.getInstance().setNumberOfRounds(Integer.parseInt(s.toString()));
//						Log.v("Settings", ""+Parameters.getInstance().getNumberOfRounds());											
//					}else{
//						Toast t = Toast.makeText(pointerHax, "Number of rounds has to be odd", Toast.LENGTH_LONG);
//						t.show();
//					}
//				}
//			}
//			@Override
//			public void beforeTextChanged(CharSequence s, int start, int count,
//					int after) {}	
//			@Override
//			public void afterTextChanged(Editable s) {}
//		});
//		
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
		numberOfRounds.setOnEditorActionListener(new TextView.OnEditorActionListener() {
	        @Override
	        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
	            if (actionId == EditorInfo.IME_ACTION_DONE) {
	            	String s = v.getText().toString();
	    			if (s.length() != 0) {
						if (Integer.parseInt(s) % 2 != 0) {
							Parameters.getInstance().setNumberOfRounds(Integer.parseInt(s.toString()));
							Log.v("Settings", ""+Parameters.getInstance().getNumberOfRounds());											
						}else{
							numberOfRounds.setText(""+Parameters.getInstance().getNumberOfRounds());
							Toast t = Toast.makeText(pointerHax, "Number of rounds has to be odd", Toast.LENGTH_LONG);
							t.show();
						}
					}
	            	numberOfRounds.clearFocus();
	            	InputMethodManager imm = (InputMethodManager)getSystemService(
	            		      Context.INPUT_METHOD_SERVICE);
	            		imm.hideSoftInputFromWindow(numberOfRounds.getWindowToken(), 0);
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
