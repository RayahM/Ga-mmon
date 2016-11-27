/**
 * GNU General Public License
 *
 * This file is part of GA-mmon.
 *
 * GA-mmon is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * GA-mmon is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * GA-mmon. If not, see <http://www.gnu.org/licenses/>.
 */

package uk.co.davidlomas.gammon.game;

import uk.co.davidlomas.gammon.genes.Individual;
import uk.co.davidlomas.gammon.settings.GameSettings;

/**
 * The Class AIPlayer.
 *
 * extends the Player class and implements methods to allow the AI to choose the
 * next move
 *
 * @author David Lomas - 11035527
 */
public class AiPlayer extends Player {

	private final MoveGenerator moveGenerator;
	private Individual individual;

	public AiPlayer(final AiPlayer p) {
		super(p.black);

		movesLeft = new MovesLeft(p.movesLeft);
		black = p.black;
		die1 = p.die1;
		die2 = p.die2;
		turnOver = p.turnOver;
		moveGenerator = p.moveGenerator;
	}

	public AiPlayer(final boolean b, final Individual individual) {
		super(b);
		this.individual = individual;
		moveGenerator = new MoveGenerator(individual);
	}

	public Board AIturn(final Board currentBoard) {

		if (GameSettings.getDisplayConsole()) {
			System.out.println("------------AI's Turn!-----------------");
		}

		movesLeft = new MovesLeft();
		movesLeft.setTo(dice.RollDice());
		if (GameSettings.getDisplayConsole()) {
			System.out.println("Player has : " + movesLeft.toString());
		}

		Board newBoard = moveGenerator.getNextMoveBoard(currentBoard, this);

		// if the board has not changed
		if (newBoard == null) {
			newBoard = currentBoard;
		}

		return newBoard;
	}

	public Individual getIndividual() {
		return individual;
	}
}