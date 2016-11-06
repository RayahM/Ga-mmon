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

package uk.co.davidlomas.gammon.genes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import uk.co.davidlomas.gammon.settings.GenAlgSettings;

/**
 * The Class Individual.
 *
 * The AI behind a player, contains attributes which dictates which decisions
 * they make
 *
 * @author David Lomas - 11035527
 */
public class Individual {

	private static final String PLAYERS_FILE_PATH = "savedPlayers/Attempt - ";

	// number of attributes
	private final int numOfAttributes;

	// Attributes
	private IndividualAttribute[] listOfAttributes = null;

	/** The chromosome. */
	private char[] chromosome;

	/** The fitness. */
	private double fitness = 0;

	/**
	 * Instantiates a new individual.
	 */
	public Individual() {

		// add attributes to the array
		listOfAttributes = new IndividualAttribute[] { new IndividualAttribute("bearAPiece"), // 0
				new IndividualAttribute("takeAPiece"), // 1
				new IndividualAttribute("doubleUpAPiece"), // 2
				new IndividualAttribute("blockAnOpponent"), // 3
				new IndividualAttribute("movingAPieceSolo"), // 4
				new IndividualAttribute("spreadAHomePiece"), // 5
				new IndividualAttribute("addACheckerToAStack"), // 6
				new IndividualAttribute("twoOneSplitPlayInitialMove"), // 7
				new IndividualAttribute("twoOneSlotPlayInitialMove"), // 8
				new IndividualAttribute("threeOneInitialMove"), // 9
				new IndividualAttribute("threeTwoSplitInitialMove"), // 10
				new IndividualAttribute("threeTwoOffenceInitialMove"), // 11
				new IndividualAttribute("fourOneInitialMove"), // 12
				new IndividualAttribute("fourOneInitialMoveAlt"), // 13
				new IndividualAttribute("fourTwoInitialMove"), // 14
				new IndividualAttribute("fourThreeInitialMoveSplit"), // 15
				new IndividualAttribute("fourThreeInitialMoveBlock"), // 16
				new IndividualAttribute("fiveOneInitialMove"), // 17
				new IndividualAttribute("fiveOneInitialMoveAlt"), // 18
				new IndividualAttribute("fiveTwoInitialMove"), // 19
				new IndividualAttribute("fiveTwoInitialMoveRisk"), // 20
				new IndividualAttribute("fiveThreeInitialMove"), // 21
				new IndividualAttribute("fiveFourInitialMoveAgr"), // 22
				new IndividualAttribute("fiveFourInitialMoveBal"), // 23
				new IndividualAttribute("sixOneInitialMove"), // 24
				new IndividualAttribute("sixTwoInitialMove"), // 25
				new IndividualAttribute("sixTwoInitialMoveAgr"), // 26
				new IndividualAttribute("sixThreeInitialMove"), // 27
				new IndividualAttribute("sixThreeInitialMoveSplit"), // 28
				new IndividualAttribute("sixFourInitialMove"), // 29
				new IndividualAttribute("sixFourInitialMoveSplit"), // 30
				new IndividualAttribute("sixFiveInitialMove") // 31
		};

		numOfAttributes = listOfAttributes.length;

		// convert to binary string
		chromosome = Util.convertFromIntToBinaryCharAry(listOfAttributes);
	}

	/**
	 * getAttribute
	 *
	 * @param pos
	 * @return
	 */
	public IndividualAttribute getAttribute(final int pos) {
		return listOfAttributes[pos];
	}

	/**
	 * Gets the chromosome.
	 *
	 * @return the chromosome
	 */
	public char[] getChromosome() {
		return chromosome;
	}

	/**
	 * Gets the fitness.
	 *
	 * @return the fitness
	 */
	public double getFitness() {
		return fitness;
	}

	/**
	 * Gets the number of attributes.
	 *
	 * @return the number of attributes
	 */
	public int getNumOfAttributes() {
		return numOfAttributes;
	}

	/**
	 * Load from file.
	 *
	 * @param fileName
	 *            the file name
	 */
	public void loadFromFile(final String fileName) {

		final Properties properties = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream(
					PLAYERS_FILE_PATH + GenAlgSettings.getAtemptCount() + "/" + fileName + ".properties");

			// load the file
			properties.load(input);

			// get the chromosome value
			chromosome = properties.getProperty("chromosome").toCharArray();

			// update to the rest of the attributes
			updateFromBinary();

		} catch (final IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Save to file.
	 *
	 * @param fileName
	 *            the file name
	 */
	public void saveToFile(final String fileName) {

		updateToBinary();
		final Properties properties = new Properties();
		OutputStream output = null;

		try {
			final Date date = new Date();
			final SimpleDateFormat sdf = new SimpleDateFormat("MM:dd:yyyy h:mm:ss a");
			final String formattedDate = sdf.format(date);
			final String savedPlayersDirectory = PLAYERS_FILE_PATH + GenAlgSettings.getAtemptCount() + "- "
					+ formattedDate;

			new File(savedPlayersDirectory).mkdir();

			output = new FileOutputStream(savedPlayersDirectory + "/" + fileName + ".properties");

			// set the properties value
			properties.setProperty("chromosome", String.valueOf(chromosome));
			properties.setProperty("fitness", getFitness() + "");
			for (final IndividualAttribute indivAtribute : listOfAttributes) {
				properties.setProperty(indivAtribute.getName(), indivAtribute.getValue() + "");
			}

			// save the file to the savedPlayers folder
			properties.store(output, null);

			GenAlgSettings.setAtemptCount(GenAlgSettings.getAtemptCount() + 1);

		} catch (final IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

	/**
	 * setAttribute
	 *
	 * @param attr
	 * @param data
	 */
	public void setAttribute(final int attr, final IndividualAttribute data) {
		listOfAttributes[attr] = data;
		updateToBinary();
	}

	/**
	 * Sets the fitness.
	 *
	 * @param fit
	 *            the new fitness
	 */
	public void setFitness(final double fit) {
		fitness = fit;
	}

	/**
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String result = "Indiviudal with chromosome string: " + String.valueOf(chromosome) + " and fitness of: "
				+ fitness;
		for (int x = 0; x < listOfAttributes.length; x++) {
			result += "| " + listOfAttributes[x].getName() + ": " + listOfAttributes[x].getValue();
		}
		return result;
	}

	/**
	 * Update from binary.
	 */
	private void updateFromBinary() {
		final int[] values = Util.convertFromBinaryStringsToIntAr(String.valueOf(chromosome), numOfAttributes, 7);
		for (int x = 0; x < values.length; x++) {
			listOfAttributes[x].setValue(values[x]);
		}
	}

	/**
	 * Update to binary.
	 */
	public void updateToBinary() {
		chromosome = Util.convertFromIntToBinaryCharAry(listOfAttributes);
	}
}
