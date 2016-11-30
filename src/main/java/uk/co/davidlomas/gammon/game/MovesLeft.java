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

import java.util.ArrayList;
import java.util.List;

/**
 * The Class MovesLeft.
 *
 * used for loops checking which moves the player has left to use
 *
 * @author David Lomas
 */
public class MovesLeft {

	public List<Integer> movesLeft;

	public MovesLeft() {
		movesLeft = new ArrayList<>();
	}

	/**
	 * Clone constructor
	 *
	 * Instantiates a new moves left.
	 *
	 * @param movesLeftCopy
	 *            the moves left copy
	 */
	public MovesLeft(final MovesLeft movesLeftCopy) {

		// create new list
		movesLeft = new ArrayList<>();

		// copy old one
		for (final int x : movesLeftCopy.movesLeft) {
			movesLeft.add(x);
		}
	}

	/**
	 * Add adds the param passed in to the list
	 *
	 * @param value
	 *            the int to be added
	 */
	public void add(final int value) {
		movesLeft.add(Integer.valueOf(value));
	}

	/**
	 * Clear.
	 */
	public void clear() {
		movesLeft.clear();
	}

	public boolean contains(final int i) {
		if (movesLeft.contains(i)) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the next.
	 *
	 * @return the next
	 */
	public int getNext() {
		return movesLeft.get(0);
	}

	/**
	 * Remove removes the value passed in
	 *
	 * @param value
	 *            the value to be removed
	 */
	public void remove(final int value) {
		movesLeft.remove(Integer.valueOf(value));
	}

	/**
	 * setTo
	 *
	 * Sets the list to the same as the one passed in
	 *
	 * @param diceRolls
	 *            - set the current list to contents of dicerolls
	 */
	public void setTo(final List<Integer> diceRolls) {
		clear();
		for (final int x : diceRolls) {
			add(x);
		}
	}

	/**
	 * Size.
	 *
	 * @return the size of list
	 */
	public int size() {
		return movesLeft.size();
	}

	/*
	 * toString method
	 */
	@Override
	public String toString() {
		String str = size() + " moves Left, with Rolls: ";

		for (int x = 0; x < size(); x++) {
			str += movesLeft.get(x) + ", ";
		}

		return str;

	}

}
