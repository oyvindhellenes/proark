package no.ntnu.stud.proark.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import no.ntnu.stud.proark.Parameters;
import no.ntnu.stud.proark.model.pieces.BoardPiece;
import no.ntnu.stud.proark.model.pieces.Dice;
import no.ntnu.stud.proark.model.pieces.GoalPiece;
import no.ntnu.stud.proark.model.pieces.PlayerPiece;


public class GameBoard {
	
	private int players = 2;
	private Tile[] board = new Tile[36];
	// Players are stored at their ID, goal piece is position 0.
	private Map<Integer, BoardPiece> pieces = new HashMap<Integer, BoardPiece>();
	private int[] startingPositions = new int[]{0,30,35,5};
	private int player_one_start = 0, player_two_start = 2;
	
	// Game state variables
	private int currentPlayer;
	private int movesLeft;
	private int currentDiceRoll;
	private int hasHitWall = -1;
	
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
	
	public GameBoard() {
		initBoard(0, 2, 1);
	}
	
	public GameBoard(int player_one_start, int player_two_start, int startingPlayer) {
		initBoard(player_one_start, player_two_start, startingPlayer);
	}
	
	public void initBoard(int player_one_start, int player_two_start, int startingPlayer) {
		this.player_one_start = player_one_start;
		this.player_two_start = player_two_start;
		this.currentPlayer = startingPlayer;
		
		currentDiceRoll = Dice.roll();
		movesLeft = currentDiceRoll;
		
		for (int i=0; i<board.length; i++) {
			board[i] = Tile.EMPTY;
		}
		
		pieces.put(1, new PlayerPiece(Parameters.getInstance().getPlayerOne(), startingPositions[player_one_start]));
		pieces.put(2, new PlayerPiece(Parameters.getInstance().getPlayerTwo(), startingPositions[player_one_start]));
		setPositions();
		
		System.out.println("(init) Current player: "+currentPlayer);
	}
	
	public void setPositions() {
		board[startingPositions[player_one_start]] = Tile.PLAYER_ONE;
		board[startingPositions[player_two_start]] = Tile.PLAYER_TWO;
		board[20] = Tile.GOAL;
		pieces.get(1).setPosition(startingPositions[player_one_start]);
		pieces.get(2).setPosition(startingPositions[player_two_start]);
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
	
	public void nextPLayer() {
		if (currentPlayer == players) {
			this.currentPlayer = 1;
		}
		else {
			this.currentPlayer++;
		}
		currentDiceRoll = Dice.roll();
		movesLeft = currentDiceRoll;
	}
	
	public int getNextPlayer() {
		if (currentPlayer == players) {
			return 1;
		}
		else {
			return currentPlayer + 1;
		}
	}
	
	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	public int getCurrentPlayerPosition() {
		return pieces.get(currentPlayer).getPosition();
	}

	public int getMovesLeft() {
		return movesLeft;
	}

	public void decreaseMovesLeft() {
		System.out.println("(decm) Current player: "+currentPlayer);
		this.movesLeft--;
	}

	public int getCurrentDiceRoll() {
		return currentDiceRoll;
	}

	public void setCurrentDiceRoll(int currentDiceRoll) {
		this.currentDiceRoll = currentDiceRoll;
	}

	public void unsetHasHitWall() {
		this.hasHitWall = -1;
	}

	public void hasHitWall() {
		this.hasHitWall = getCurrentPlayerPosition();
	}

	public int getHasHitWall() {
		return this.hasHitWall;
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
	
	public String getPlayerName(int player) {
		return ((PlayerPiece)pieces.get(player)).getName();
	}
	
	public void movePlayerToStart(int player) {
		if (player == 1) {
			board[pieces.get(player).getPosition()] = Tile.EMPTY;
			pieces.get(player).setPosition(startingPositions[player_one_start]);
			board[pieces.get(player).getPosition()] = Tile.PLAYER_ONE;
		}
		if (player == 2) {
			board[pieces.get(player).getPosition()] = Tile.EMPTY;
			pieces.get(player).setPosition(startingPositions[player_two_start]);
			board[pieces.get(player).getPosition()] = Tile.PLAYER_TWO;
		}
	}
	
	public void newRound() {
		int winner = getCurrentPlayer();
		player_one_start = player_one_start == 3 ? 0 : ++player_one_start;
		player_two_start = player_two_start == 3 ? 0 : ++player_two_start;
		movePlayerToStart(1);
		movePlayerToStart(2);
		setPositions();
	}
	
	public int getPlayerOneStart() {
		return this.player_one_start;
	}
	
	public int getPlayerTwoStart() {
		return this.player_two_start;
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
	
	public boolean alreadyOccupied(int position) {
		return (board[position] == Tile.PLAYER_ONE || board[position] == Tile.PLAYER_TWO);
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
		else if (alreadyOccupied(moveTo)) {
			move.setError(true);
			move.setErrorReason("A player already stands on this tile");
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
