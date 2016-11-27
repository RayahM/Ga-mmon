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

package uk.co.davidlomas.gammon.test.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.co.davidlomas.gammon.game.MovesLeft;
import uk.co.davidlomas.gammon.test.helpers.Settings;

public class MovesLeftTest {

	private MovesLeft testSubject;

	@BeforeClass
	public static void beforeClass() {
		Settings.resettSettings();
	}

	@AfterClass
	public static void afterClass() {
		Settings.resettSettings();
	}

	@Before
	public void setUp() throws Exception {
		testSubject = new MovesLeft();
	}

	@Test
	public void callingCloneConstructorOnMovesLeftShouldReturnaBoardWithTheSameContents() {
		// Given
		testSubject.add(4);
		testSubject.add(9);
		testSubject.add(3);

		MovesLeft clonedMovesLeft;

		// When
		clonedMovesLeft = new MovesLeft(testSubject);

		// Then
		assertEquals(4, clonedMovesLeft.getNext());
		assertTrue(clonedMovesLeft.movesLeft.contains(4));
		assertTrue(clonedMovesLeft.movesLeft.contains(9));
		assertTrue(clonedMovesLeft.movesLeft.contains(3));
	}

	@Test
	public void addingANumberToTheMovesLeftShouldStoreIt() {

		// When
		testSubject.add(5);

		// Then
		assertEquals(5, testSubject.getNext());
		assertTrue(testSubject.contains(5));
	}

	@Test
	public void removingMoveFromArrayAndThenCallingContainsShouldReturnFalse() {
		// Given
		testSubject.add(4);
		testSubject.add(9);

		// When
		testSubject.remove(4);

		// Then
		assertEquals(9, testSubject.getNext());
		assertFalse(testSubject.contains(4));
	}

	@Test
	public void adding3ElementsToArrayAndCallingSizeShouldReturn3() {
		// Given
		testSubject.add(4);
		testSubject.add(9);
		testSubject.add(3);

		// When
		final int size = testSubject.size();

		// Then
		assertEquals(3, size);
	}
}