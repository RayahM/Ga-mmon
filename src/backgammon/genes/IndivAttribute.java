/**
 * 	GNU General Public License
 * 
 *  This file is part of GA-mmon.
 *  
 *  GA-mmon is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  GA-mmon is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with GA-mmon.  If not, see <http://www.gnu.org/licenses/>.
*/

package backgammon.genes;

/**
 * IndivAttribute
 * 
 * attribute for the individual, each will have a number of these e.g. bear a
 * piece
 * 
 * Controls the particular chance of doing a certain strategy
 * 
 * @author David Lomas - 11035527
 */
public class IndivAttribute {

	private String name = "";
	private int value = 0;

	/**
	 * IndivAttribute
	 * 
	 * @param n
	 *            - the name
	 */
	public IndivAttribute(String n) {
		name = n;
		value = (int) (Math.random() * 100);
	}

	/**
	 * getName
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * setName
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getValue
	 * 
	 * @return value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * setValue
	 * 
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return name + ": " + value;
	}
}