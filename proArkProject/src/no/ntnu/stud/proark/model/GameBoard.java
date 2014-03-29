package no.ntnu.stud.proark.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import android.graphics.Rect;
import no.ntnu.stud.proark.Parameters;
import no.ntnu.stud.proark.model.pieces.BoardPiece;
import no.ntnu.stud.proark.model.pieces.GoalPiece;
import no.ntnu.stud.proark.model.pieces.PlayerPiece;


public class GameBoard {
	
	private Difficulty level;
	private Tile[] board = new Tile[36];
	private Map<Integer, BoardPiece> pieces = new HashMap<Integer, BoardPiece>();
	
	// Predefined set of walls
	private Set<Move> walls = new HashSet<Move>(){{
		add(new Move(6, 7));
		add(new Move(7, 8));
		add(new Move(8, 14));
		add(new Move(9, 10));
		add(new Move(14, 15));
		add(new Move(16, 17));
		add(new Move(18, 19));
		add(new Move(19, 25));
		add(new Move(21, 22));
		add(new Move(25, 26));
		add(new Move(27, 33));
		add(new Move(28, 29));
	}};
	
	public GameBoard(Difficulty level) {
		this.level = level;
		for (int i=0; i<board.length; i++) {
			board[i] = Tile.EMPTY;
		}
		board[0] = Tile.PLAYER_ONE;
		board[35] = Tile.PLAYER_TWO;
		board[20] = Tile.GOAL;
		pieces.put(1, new PlayerPiece("Player 1", 0));
		pieces.put(2, new PlayerPiece("Player 2", 35));
		pieces.put(0, new GoalPiece(20));
	}
	
	public Set<Move> getWalls() {
		return this.walls;
	}
	
	public Tile getTile(int position) {
		return board[position];
	}
	
	public Move makeMove(int player, int moveTo) {
		// TODO fetch from player object
		PlayerPiece current = (PlayerPiece)pieces.get(player);
		System.out.println("PLayer: "+current.getName());
		if (Math.abs(current.getPosition() - moveTo) == 1 || Math.abs(current.getPosition() - moveTo) == 6) {
			board[current.getPosition()] = Tile.EMPTY;
			Move move = new Move(current.getPosition(), moveTo);
			current.setPosition(moveTo);
			if (player == 1) {
				board[moveTo] = Tile.PLAYER_ONE;
			}
			if (player == 2) {
				board[moveTo] = Tile.PLAYER_TWO;
			}
			return move;
		}

		// move wasn't valid, return null
		return null;
	}
	
}
