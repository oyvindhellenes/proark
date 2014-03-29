package no.ntnu.stud.proark.model.pieces;

public class Dice {
//	public Dice() {
//		rollDice();
//	}
	public static int roll(){
		return (int)(Math.random()*6) + 1;
	}
}
