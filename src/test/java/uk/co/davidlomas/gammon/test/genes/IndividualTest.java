package uk.co.davidlomas.gammon.test.genes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.UUID;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import uk.co.davidlomas.gammon.genes.Individual;
import uk.co.davidlomas.gammon.test.helpers.Settings;

public class IndividualTest {

  final Logger logger = LogManager.getLogger(IndividualTest.class);

  private Individual individual1;
  private Individual individual2;
  private UUID uuid;
  private String filePath;

  @BeforeClass
  public static void beforeClass() {
    Settings.resetSettings();
  }

  @AfterClass
  public static void afterClass() {
    Settings.resetSettings();
  }

  @Before
  public void setup() {
    individual1 = new Individual();
    individual2 = new Individual();
    uuid = UUID.randomUUID();
    filePath = "savedPlayers/test/testIndividual - " + uuid.toString();
  }

  @Test
  public void getFilePathForPlayersComaparedToActualStringShouldBeTrue() {
    final String actual = individual1.getFilePathForPlayers();
    final String expected = "savedPlayers/Attempt - ";
    assertTrue(actual.startsWith(expected));
  }

  @Test
  public void savingAndLoadingIndividualCheckingPropertiesMatch() {

    // given
    individual1 = new Individual();
    individual1.saveToFile(filePath);
    assertTrue(new File(filePath).exists());

    // when
    individual2 = new Individual();
    individual2.loadFromFile(filePath);

    //thebn
    // check num of attributes is same
    assertEquals(individual1.getNumOfAttributes(), individual2.getNumOfAttributes());

    // check all attributes
    for (int i = 0; i < individual1.getNumOfAttributes(); i++) {

      assertEquals("Individuals attributes  don't match after loading",
          individual1.getAttribute(i).getName(), individual2.getAttribute(i).getName());

      assertEquals("Individuals attributes  don't match after loading",
          individual1.getAttribute(i).getValue(), individual2.getAttribute(i).getValue());
    }
  }
}