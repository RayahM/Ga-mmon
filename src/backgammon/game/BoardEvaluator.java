package backgammon.game;

import backgammon.settings.GameSettings;

/**
 * The Class BoardEvaluator.
 * 
 * usefull methods for picking boards from the board list e.g. pick a board where it will take a peice
 */
public class BoardEvaluator {
	
	/** The live board. */
	private Board liveBoard;
	
	/** The current player. */
	private AIPlayer currentPlayer;
	
	/** The oponent zero positions. */
	private int myZero, oponentZero;
	
	
	/**
	 * Board evalutator cons.
	 */
	public void BoardEvalutator(){}
	
	
	/**
	 * Checks for a peice being taken.
	 *
	 * @param newBoard the new board
	 * @return true, if successful
	 */
	public boolean hasAPeiceBeenTaken(Board newBoard){
		if(liveBoard.Points[oponentZero].numEither()<newBoard.Points[oponentZero].numEither()){
			if(GameSettings.getDisplayConsole()){System.out.println("Peice has been taken.");}
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Sets the board.
	 *
	 * @param cb the new board
	 */
	public void setBoard(Board cb){
		liveBoard = cb;
	}
	
	/**
	 * Sets the player.
	 *
	 * @param p the new player
	 */
	public void setPlayer(AIPlayer p){
		currentPlayer = p;
		if(p.black){
			myZero = 25;
			oponentZero=0;
		}else{
			myZero = 0;
			oponentZero=25;
		}
	}
	
	/**
	 * Checks for a peice that has been bore.
	 *
	 * @param newBoard the new board
	 * @return true, if successful
	 */
	public boolean hasAPeiceBeenBore(Board newBoard) {
		if(liveBoard.howManyHasPlayerBore(currentPlayer.black)<newBoard.howManyHasPlayerBore(currentPlayer.black)){
			if(GameSettings.getDisplayConsole()){System.out.println("Peice has been BORE");}
			return true;
		}else{
			return false;
		}
	}
}