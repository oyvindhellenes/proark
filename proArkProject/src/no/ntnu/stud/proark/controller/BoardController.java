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
		currentPlayer = 1;
		currentDiceRoll = 3;
		movesLeft = currentDiceRoll;
		boardView.showMoves(parent, board.getPiecePosition(currentPlayer), movesLeft, false);
	}
	
	public void showDiceRoll(int number) {
		// this.currentDiceRoll
		// kall view her
	}
	
	private void playerMoved() {
		movesLeft -= 1;
		if (board.getPiecePosition(0) == board.getPiecePosition(currentPlayer)) {
			gameScore.updateScore(currentPlayer);
			board.newRound();
		}
		else if (movesLeft == 0) {
			nextPlayer();
		}
	}
	
	private void nextPlayer() {
		currentPlayer = board.getNextPLayer(currentPlayer);
		movesLeft = 3;
	}
	
	public void tileClicked(ViewGroup parent, int position) {
		Move move = board.makeMove(currentPlayer, position);
		if (move.isError()) {
			boardView.showText(move.getErrorReason());
			if (move.hitWall()) {
				boardView.crashed(parent, currentPlayer, move.getFrom(), move.getCrashDirection());
			}
		}
		else {
			boardView.showMoves(parent, move.getFrom(), movesLeft, true);
			boardView.updateTile(parent, move.getFrom(), Tile.EMPTY);
			boardView.updateTile(parent, position, board.getTile(position));
			playerMoved();
			boardView.showMoves(parent, board.getPiecePosition(currentPlayer), movesLeft, false);
			boardView.updateTile(parent, board.getPiecePosition(1), Tile.PLAYER_ONE);
     	    boardView.updateTile(parent, board.getPiecePosition(2), Tile.PLAYER_TWO);
     	    boardView.updateTile(parent, board.getPiecePosition(0), Tile.GOAL);
		}
	}
	
}
