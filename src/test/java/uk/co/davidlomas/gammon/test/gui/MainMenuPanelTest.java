package uk.co.davidlomas.gammon.test.gui;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import uk.co.davidlomas.gammon.test.helpers.Settings;

public class MainMenuPanelTest {
	@BeforeClass
	public static void beforeClass() {
		Settings.resettSettings();
	}

	@AfterClass
	public static void afterClass() {
		Settings.resettSettings();
	}
}