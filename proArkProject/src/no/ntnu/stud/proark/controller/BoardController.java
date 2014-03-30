package no.ntnu.stud.proark.controller;

import android.view.ViewGroup;
import no.ntnu.stud.proark.model.GameBoard;
import no.ntnu.stud.proark.model.GameScore;
import no.ntnu.stud.proark.model.Move;
import no.ntnu.stud.proark.model.Tile;
import no.ntnu.stud.proark.model.pieces.Dice;
import no.ntnu.stud.proark.view.BoardView;

public class BoardController {
	
	private static BoardController instance = null;
	
	private GameBoard board;
	private BoardView boardView;
	private GameScore gameScore;
	
	private int currentPlayer;
	private int movesLeft;
	private int currentDiceRoll;
	private int hasHitWall = -1;
	
	public BoardController() {
		this.gameScore = new GameScore(3, 2);
	}
	
	public static BoardController getInstance() {
		if (instance == null) {
			instance = new BoardController();
		}
		return instance;
	}
	
	public void setBoardView(BoardView boardView) {
		this.boardView = boardView;
	}
	
	public void setBoard(GameBoard board) {
		this.board = board;
	}
	
	public void startGame(ViewGroup parent) {
		resetGameVariables();
		boardView.showMoves(parent, board.getPiecePosition(currentPlayer), movesLeft, false);
	}
	
	public void resetGameVariables() {
		currentPlayer = 1;
		currentDiceRoll = Dice.roll();
		movesLeft = currentDiceRoll;
	}
	
	public void showDiceRoll(int number) {
		// this.currentDiceRoll
		// kall view her
		boardView.updateDice(number);
	}
	
	private void playerMoved(ViewGroup parent) {
		movesLeft -= 1;
		if (board.getPiecePosition(0) == board.getPiecePosition(currentPlayer)) {
			boardView.showAlertMessage(parent, String.format("%s won this round!", board.getPlayerName(currentPlayer)));
			int winner = currentPlayer;
			gameScore.updateScore(currentPlayer);
			// TODO: Check for overall winner here
			System.out.println("Position of 1: "+board.getPiecePosition(1));
			System.out.println("Position of 2: "+board.getPiecePosition(2));
			boardView.updateTile(parent, board.getPiecePosition(1), Tile.EMPTY);
			boardView.updateTile(parent, board.getPiecePosition(2), Tile.EMPTY);
			board.newRound();
			resetGameVariables();
			currentPlayer = board.getNextPLayer(winner);
		}
		else if (movesLeft == 0) {
			nextPlayer();
			boardView.showAlertMessage(parent, String.format("It is now %s's turn", board.getPlayerName(currentPlayer)));
		}
	}
	
	private void nextPlayer() {
		currentPlayer = board.getNextPLayer(currentPlayer);
		movesLeft = Dice.roll();
		showDiceRoll(movesLeft);
	}
	
	private void drawPieces(ViewGroup parent) {
		boardView.updateTile(parent, board.getPiecePosition(1), board.getTile(board.getPiecePosition(1)));
 	    boardView.updateTile(parent, board.getPiecePosition(2), board.getTile(board.getPiecePosition(2)));
 	    boardView.updateTile(parent, board.getPiecePosition(0), board.getTile(board.getPiecePosition(0)));
	}
	
	public void tileClicked(ViewGroup parent, int position) {
		// We set the player back to start when they hit a wall, but we do it on the next tap
		if (hasHitWall >= 0) {
			// Hide move tiles + player
			boardView.showMoves(parent, hasHitWall, movesLeft, true);
			boardView.updateTile(parent, hasHitWall, Tile.EMPTY);
			// Put the player back to start in the board model
			board.movePlayerToStart(currentPlayer);
     	    // Initiate next players turn
			nextPlayer();
			// Show moves available after moving (could be other players moving tiles being shown by this)
			boardView.showMoves(parent, board.getPiecePosition(currentPlayer), movesLeft, false);
			// Redraw all players
			drawPieces(parent);
			// "unset" this variable.
			hasHitWall = -1;
		}
		else {
			Move move = board.makeMove(currentPlayer, position);
			if (move.isError()) {
				if (move.hitWall()) {
					boardView.crashed(parent, currentPlayer, move.getFrom(), move.getCrashDirection());
					boardView.showAlertMessage(parent, "You have hit a wall and will be moved back to start!");
					// If a player hits a wall, we store the tile on which it happened and then wait for another tap.
					hasHitWall = move.getFrom();
				}
				if (hasHitWall < 0) {
					boardView.showAlertMessage(parent, move.getErrorReason());
				}
			}
			else {
				// Hide move tiles + player
				boardView.showMoves(parent, move.getFrom(), movesLeft, true);
				boardView.updateTile(parent, move.getFrom(), Tile.EMPTY);
				boardView.updateTile(parent, position, board.getTile(position));
				// Run a method to see if player moving has any consequenses
				playerMoved(parent);
				// Show moves available after moving (could be other players moving tiles being shown by this)
				boardView.showMoves(parent, board.getPiecePosition(currentPlayer), movesLeft, false);
				// Redraw all players and goal
				drawPieces(parent);
			}
		}
	}
	
}
