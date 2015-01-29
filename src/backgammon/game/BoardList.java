package backgammon.game;

import java.util.ArrayList;
import java.util.List;

import backgammon.genes.IndivAttribute;
import backgammon.genes.Individual;
import backgammon.settings.GameSettings;
/**
 * The Class BoardList.
 */
public class BoardList {

	/** The all possible moves. */
	private List<Board> boardList;

	/**  The individual that will make the move. */
	private Individual individual;

	/** The b eval. */
	private BoardEvaluator bEval;

	/**
	 * BoardList
	 * 
	 * Instantiates a new board list.
	 *
	 * @param indiv the indiv
	 */
	public BoardList(Individual indiv){

		boardList = new ArrayList<Board>();

		individual = indiv;

		bEval = new BoardEvaluator();
	}

	/**
	 * clearList
	 * 
	 * Emptys the list.
	 */
	public void clearList(){
		boardList.clear();
	}

	/**
	 * addBoard
	 * Adds the board passed in.
	 *
	 * @param b the board
	 */
	public void addBoard(Board b){
		boardList.add(b);
	}

	/**
	 * addBoard
	 * adds the board passed in with the move also passed in applied to it first.
	 * It then checks if any more moves can be made
	 *
	 * @param b the board passed in
	 * @param to the destination of move
	 * @param from the start of the move
	 * @param p1 the player object
	 * @param mg the move generator
	 */
	public void addBoard(Board b, int to, int from, AIPlayer p1, MoveGenerator mg){

		//move the piece on this new board
		p1.movePiece(from, to, b);

		if(p1.movesLeft.size()>1){
			p1.movesLeft.movesLeft.remove(Integer.valueOf(p1.distanceBetween(from, to)));
		}else{
			p1.movesLeft.movesLeft.remove(0);
			p1.movesLeft.movesLeft.add(Integer.valueOf(0));
		}

		//check if any further moves can be made
		if(!((p1.movesLeft.size()==1 && p1.movesLeft.getNext()==0)||(p1.movesLeft.size()==0))){
			mg.generateMoves(b, p1);
		}

		//add the board to the list if there is not a duplicate
		if(!(thereIsADuplicate(b))){
			addBoard(b);
		}
	}


	/**
	 * There is a duplicate.
	 * checks if there is already a duplicate of the board passed in already present in the list
	 *
	 * @param b the board it is checking against
	 * @return true, if there is a duplicate
	 */
	public boolean thereIsADuplicate(Board b){
		for(Board x: boardList){
			if(x.equals(b)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Select board.
	 * This will select the best board from the list for the player to use
	 *
	 * @param currentBoard the current board
	 * @param p the p
	 * @return the board that has been selected
	 */
	public Board selectBoard(Board currentBoard, AIPlayer p){

		//create a new board
		Board chosenBoard = null;

		if(boardList.size()>0){

			//Use the individual to decide what to do next
			if(individual!=null){

				//use the method individual decision to decide which to pick
				chosenBoard = individualDecision(currentBoard, p);
				return chosenBoard;

				//if the player personality = null then just pick at random, as this means its the basic opposition to test against
			}else{
				int x = (int)(Math.random()*(boardList.size()));

				if(GameSettings.getDisplayConsole()){System.out.println("board list size: "+boardList.size());}

				chosenBoard = boardList.get(x);

				return chosenBoard;
			}
		}else
		{
			//if there are no possible new boards(no possible moves) return null
			return null;
		}

	}

	/**
	 * Prints the board list.
	 */
	public void printBoardList(){
		int counter = 0;
		for(Board x: boardList){
			counter++;
			System.out.println("----------------------------");
			System.out.println("Board num "+ counter);
			x.printBoard();
			System.out.println("----------------------------");
		}
	}

	/**
	 * Checks for contents.
	 *
	 * @return true, if successful
	 */
	public boolean hasContents(){
		if(boardList.size()>0){
			return true;
		}else{
			return false;
		}
	}


	/**
	 * Individual decision.
	 * 
	 * Is called by select board and it is where the individuals attributes influance the chosen board
	 * 
	 * e.g. higher agression chance will produce more peices being taken
	 *
	 * @param currentBoard the current board
	 * @param p the player
	 * @return the board chosen
	 */
	public Board individualDecision(Board currentBoard, AIPlayer p){

		//if returned null it doesn't change
		Board chosenBoard = null;

		//Giving the board evaluator the info it needs
		bEval.setBoard(currentBoard);
		bEval.setPlayer(p);


		/*
		EXAMPLE TEXT FILE
			new IndivAttribute("bearAPiece"), 
			new IndivAttribute("takeAPiece"), 
			new IndivAttribute("doubleUpAPiece"), 
			new IndivAttribute("blockAnOpponent"),
			new IndivAttribute("movingAPieceSolo")
		 */


		// END GAME
		// Attribute 0 = bear a piece
		if(currentBoard.canPlayerBear(p.black) && p.getIndividual().getAttribute(0).getValue()>(int) (Math.random()*100)){

			//try to bear
			for(Board x: boardList){
				if(bEval.hasAPeiceBeenBore(x)){
					chosenBoard = x;
					break;
				}
			}

			// MID GAME
		}else if(chosenBoard==null){

			// Attribute 1 = take a piece
			if(p.getIndividual().getAttribute(1).getValue()>(int) (Math.random()*100)){
				//try to take a piece
				for(Board x: boardList){
					if(bEval.hasAPeiceBeenTaken(x)){
						chosenBoard = x;
						break;
					}
				}

				// Attribute 2 = doubleUpAPiece
			}else if(p.getIndividual().getAttribute(2).getValue()>(int) (Math.random()*100)){
				//try to double up a piece
				for(Board x: boardList){
					if(bEval.hasABlotBeenDoubled(x)){
						chosenBoard = x;
						break;
					}
				}

			/* Attribute 3 = blockAnOpponent
			}else if(p.getIndividual().getAttribute(2).getValue()>(int) (Math.random()*100)){
				//try to blockAnOpponent
				for(Board x: boardList){
					if(bEval.xxxxxxxx(x)){
						chosenBoard = x;
						break;
					}
				}*/
				
			/* Attribute 3 = blockAnOpponent
			}else if(p.getIndividual().getAttribute(2).getValue()>(int) (Math.random()*100)){
				//try to blockAnOpponent
				for(Board x: boardList){
					if(bEval.xxxxxxxx(x)){
						chosenBoard = x;
						break;
					}
				}*/


				//NO SPECIFIC MOVES AVAILABLE - RANDOM
			}else if(chosenBoard==null){
				int x = (int)(Math.random()*(boardList.size()));

				if(GameSettings.getDisplayConsole()){System.out.println("board list size: "+boardList.size());}

				chosenBoard = boardList.get(x);
			}
		}
		return chosenBoard;
	}
}