package no.ntnu.stud.proark.controller;

import android.view.ViewGroup;
import no.ntnu.stud.proark.model.GameBoard;
import no.ntnu.stud.proark.model.GameScore;
import no.ntnu.stud.proark.model.Move;
import no.ntnu.stud.proark.model.Tile;
import no.ntnu.stud.proark.view.BoardView;

public class BoardController {
	
	private static BoardController instance = null;
	
	private GameBoard board;
	private BoardView boardView;
	private GameScore gameScore;
	
	private int currentPlayer;
	private int movesLeft;
	private int currentDiceRoll;
	
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
		currentDiceRoll = 3;
		movesLeft = currentDiceRoll;
	}
	
	public void showDiceRoll(int number) {
		// this.currentDiceRoll
		// kall view her
	}
	
	private void playerMoved(ViewGroup parent) {
		movesLeft -= 1;
		if (board.getPiecePosition(0) == board.getPiecePosition(currentPlayer)) {
			// TODO: Show confirmation of winning round here
			int winner = currentPlayer;
			gameScore.updateScore(currentPlayer);
			// TODO: Check for winner here
			boardView.updateTile(parent, board.getPiecePosition(1), Tile.EMPTY);
			boardView.updateTile(parent, board.getPiecePosition(2), Tile.EMPTY);
			board.newRound();
			resetGameVariables();
			currentPlayer = board.getNextPLayer(winner);
		}
		else if (movesLeft == 0) {
			// TODO: Show confirmation of next turn here
			nextPlayer();
		}
	}
	
	private void nextPlayer() {
		currentPlayer = board.getNextPLayer(currentPlayer);
		movesLeft = 3;
	}
	
	private void drawPieces(ViewGroup parent) {
		boardView.updateTile(parent, board.getPiecePosition(1), board.getTile(board.getPiecePosition(1)));
 	    boardView.updateTile(parent, board.getPiecePosition(2), board.getTile(board.getPiecePosition(2)));
 	    boardView.updateTile(parent, board.getPiecePosition(0), board.getTile(board.getPiecePosition(0)));
	}
	
	public void tileClicked(ViewGroup parent, int position) {
		Move move = board.makeMove(currentPlayer, position);
		if (move.isError()) {
			boardView.showText(move.getErrorReason());
			if (move.hitWall()) {
				boardView.crashed(parent, currentPlayer, move.getFrom(), move.getCrashDirection());
				// TODO: Show confirmation about hitting wall here
				// Hide move tiles + player
				boardView.showMoves(parent, move.getFrom(), movesLeft, true);
				boardView.updateTile(parent, move.getFrom(), Tile.EMPTY);
				// Put the player back to start in the board model
				board.movePlayerToStart(currentPlayer);
				// Redraw all players
				drawPieces(parent);
	     	    // Initiate next players turn
				nextPlayer();
				// Show moves available after moving (could be other players moving tiles being shown by this)
				boardView.showMoves(parent, board.getPiecePosition(currentPlayer), movesLeft, false);
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
