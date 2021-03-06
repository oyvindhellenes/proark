package no.ntnu.stud.proark.controller;

import java.util.Currency;

import android.view.ViewGroup;
import no.ntnu.stud.proark.Parameters;
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
	
	private int players = 2;
	
	public BoardController() {
		this.gameScore = new GameScore(Parameters.getInstance().getNumberOfRounds(), players);
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
	
	public void resetGameVariables() {
		board = new GameBoard(board.getPlayerOneStart(), board.getPlayerTwoStart(), board.getNextPlayer());
	}
	
	public void startGame(ViewGroup parent) {
		board = new GameBoard();
		boardView.showMoves(parent, board.getCurrentPlayerPosition(), board.getMovesLeft(), false);
		showDiceRoll(board.getCurrentDiceRoll());
		boardView.updateRoundsLeft(gameScore.getRoundsLeft());
		boardView.updateScore(1, 0);
		boardView.updateScore(2, 0);
		boardView.updateActivePlayerFrame(board.getCurrentPlayer());
	}
	
	public void showDiceRoll(int number) {
		boardView.updateDice(number);
	}
	
	private void playerMoved(ViewGroup parent) {
		board.decreaseMovesLeft();
		if (board.getPiecePosition(0) == board.getCurrentPlayerPosition()) {
			gameScore.updateScore(board.getCurrentPlayer());
			boardView.updateScore(board.getCurrentPlayer(), gameScore.getScore(board.getCurrentPlayer()));
			boardView.updateRoundsLeft(gameScore.getRoundsLeft());
			if (gameScore.getRoundsLeft() == 0) {
				boardView.showAlertMessage("Game finished", String.format("%s has won the game!", board.getPlayerName(gameScore.getWinner())));
				return;
			}
			boardView.showAlertMessage(board.getCurrentPlayer(), 'r', "Round finished", String.format("%s won this round!", board.getPlayerName(board.getCurrentPlayer())));
			boardView.updateTile(parent, board.getPiecePosition(1), Tile.EMPTY);
			boardView.updateTile(parent, board.getPiecePosition(2), Tile.EMPTY);
			resetGameVariables();
		}
		else if (board.getMovesLeft() == 0) {
			nextPlayer();
			boardView.showAlertMessage(board.getCurrentPlayer(), 't', "New turn", String.format("It is now %s's turn", board.getPlayerName(board.getCurrentPlayer())));
		}
	}
	
	private void nextPlayer() {
		board.nextPLayer();
		boardView.updateActivePlayerFrame(board.getCurrentPlayer());
		showDiceRoll(board.getCurrentDiceRoll());
	}
	
	private void drawPieces(ViewGroup parent) {
		boardView.updateTile(parent, board.getPiecePosition(1), board.getTile(board.getPiecePosition(1)));
 	    boardView.updateTile(parent, board.getPiecePosition(2), board.getTile(board.getPiecePosition(2)));
 	    boardView.updateTile(parent, board.getPiecePosition(0), board.getTile(board.getPiecePosition(0)));
	}
	
	public void tileClicked(ViewGroup parent, int position) {
		if (gameScore.getRoundsLeft() == 0) {
			boardView.exitToMenu();
			return;
		}
		// We set the player back to start when they hit a wall, but we do it on the next tap
		if (board.getHasHitWall() >= 0) {
			boardView.updateTile(parent, board.getHasHitWall(), Tile.EMPTY);
			// Put the player back to start in the board model
			board.movePlayerToStart(board.getCurrentPlayer());
     	    // Initiate next players turn
			nextPlayer();
			// Show moves available after moving (could be other players moving tiles being shown by this)
			boardView.showMoves(parent, board.getPiecePosition(board.getCurrentPlayer()), board.getMovesLeft(), false);
			// Redraw all players
			drawPieces(parent);
			// "unset" this variable.
			board.unsetHasHitWall();
		}
		else {
			Move move = board.makeMove(board.getCurrentPlayer(), position);
			if (move.isError()) {
				if (move.hitWall()) {
					// Hide move tiles + player
					boardView.showMoves(parent, move.getFrom(), board.getMovesLeft(), true);
					// Redraw all players
					drawPieces(parent);
					// Show crash
					boardView.crashed(parent, board.getCurrentPlayer(), move.getFrom(), move.getCrashDirection());
					boardView.showAlertMessage(board.getCurrentPlayer(), 'a', "Oooops...", "You have hit a wall and will be moved back to start!");
					// If a player hits a wall, we store the tile on which it happened and then wait for another tap.
					board.hasHitWall();
				}
				if (board.getHasHitWall() < 0) {
					boardView.showAlertMessage(board.getCurrentPlayer(), 'a', "Oooops...", move.getErrorReason());
				}
			}
			else {
				// Hide move tiles + player
				boardView.showMoves(parent, move.getFrom(), board.getMovesLeft(), true);
				boardView.updateTile(parent, move.getFrom(), Tile.EMPTY);
				boardView.updateTile(parent, position, board.getTile(position));
				// Run a method to see if player moving has any consequenses
				playerMoved(parent);
				if (gameScore.getRoundsLeft() == 0) {
					return;
				}
				// Show moves available after moving (could be other players moving tiles being shown by this)
				boardView.showMoves(parent, board.getPiecePosition(board.getCurrentPlayer()), board.getMovesLeft(), false);
				// Redraw all players and goal
				drawPieces(parent);
			}
		}
	}
	
}
