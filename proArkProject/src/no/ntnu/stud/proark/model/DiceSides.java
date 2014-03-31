package no.ntnu.stud.proark.model;

import no.ntnu.stud.proark.R;

public enum DiceSides {
	DICE_ONE(R.drawable.dice_1),
	DICE_TWO(R.drawable.dice_2),
	DICE_THREE(R.drawable.dice_3),
	DICE_FOUR(R.drawable.dice_4),
	DICE_FIVE(R.drawable.dice_5),
	DICE_SIX(R.drawable.dice_6);
	
	private int imageRef;
	public int getDiceImage(){
		return this.imageRef;
	}
	DiceSides(int imageRef){
		this.imageRef = imageRef;
	}
}


