package no.ntnu.stud.proark.model;

import no.ntnu.stud.proark.R;

public enum DiceSides {
	DICE_1 (R.drawable.dice_1),
	DICE_2 (R.drawable.dice_2),
	DICE_3 (R.drawable.dice_3),
	DICE_4 (R.drawable.dice_4),
	DICE_5 (R.drawable.dice_5),
	DICE_6 (R.drawable.dice_6);
	
	private int imageRef;
	public int getDiceImage(){
		return this.imageRef;
	}
	DiceSides(int imageRef){
		this.imageRef = imageRef;
	}
}


