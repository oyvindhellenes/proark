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
	private int players;
	private Tile[] board = new Tile[36];
	// Players are stored at their ID, goal piece is position 0.
	private Map<Integer, BoardPiece> pieces = new HashMap<Integer, BoardPiece>();
	private int[] startingPositions = new int[]{0,30,35,5};
	private int player_one_start = 0, player_two_start = 2;
	
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
	
	public GameBoard(Difficulty level, int players) {
		this.level = level;
		this.players = players;
		for (int i=0; i<board.length; i++) {
			board[i] = Tile.EMPTY;
		}
		setPositions();
	}
	
	public void setPositions() {
		board[startingPositions[player_one_start]] = Tile.PLAYER_ONE;
		board[startingPositions[player_two_start]] = Tile.PLAYER_TWO;
		board[20] = Tile.GOAL;
		pieces.put(1, new PlayerPiece("Player 1", startingPositions[player_one_start]));
		pieces.put(2, new PlayerPiece("Player 2", startingPositions[player_two_start]));
		pieces.put(0, new GoalPiece(20));
	}
	
	public Set<Move> getWalls() {
		return this.walls;
	}
	
	public Tile getTile(int position) {
		return board[position];
	}
	
	/*
	 * State methods
	 */
	
	public int getNextPLayer(int currentPlayer) {
		if (currentPlayer == players) {
			return 1;
		}
		else {
			return currentPlayer + 1;
		}
	}
	
	/**
	 * Returns the position of a piece.
	 * Goal is piece 0.
	 * 
	 * @param piece
	 * @return position of the piece
	 */
	public int getPiecePosition(int piece) {
		return pieces.get(piece).getPosition();
	}
	
	public void movePlayerToStart(int player) {
		if (player == 1) {
			pieces.put(1, new PlayerPiece("Player 1", startingPositions[player_one_start]));
			board[startingPositions[player_one_start]] = Tile.PLAYER_ONE;
		}
		if (player == 2) {
			pieces.put(2, new PlayerPiece("Player 2", startingPositions[player_two_start]));
			board[startingPositions[player_two_start]] = Tile.PLAYER_TWO;
		}
	}
	
	public void newRound() {
		player_one_start = player_one_start == 3 ? 0 : player_one_start++;
		player_two_start = player_two_start == 3 ? 0 : player_two_start++;
		setPositions();
	}
	
	/*
	 * Moving
	 */
	
	public boolean isValidMove(Move move) {
		if (Math.abs(move.getFrom() - move.getTo()) == 1 || Math.abs(move.getFrom() - move.getTo()) == 6) {
			return true;
		}		
		return false;
	}

	public boolean collidesWithWall(Move move) {
		if (this.getWalls().contains(move)) {
			return true;
		}
		return false;
	}
	
	public Move makeMove(int player, int moveTo) {
		PlayerPiece currentPlayer = (PlayerPiece)pieces.get(player);
		Move move = new Move(currentPlayer.getPosition(), moveTo);
		if (!isValidMove(move)) {
			move.setError(true);
			move.setErrorReason("Invalid move");
		}
		else if (collidesWithWall(move)) {
			move.setError(true);
			move.setErrorReason("Found a wall");
			move.setHitWall(true);
		}
		else {
			board[move.getFrom()] = Tile.EMPTY;
			currentPlayer.setPosition(moveTo);
			if (player == 1) {
				board[moveTo] = Tile.PLAYER_ONE;
			}
			if (player == 2) {
				board[moveTo] = Tile.PLAYER_TWO;
			}
		}

		return move;
	}
	
}
