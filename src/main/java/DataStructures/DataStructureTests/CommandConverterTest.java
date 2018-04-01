package DataStructures.DataStructureTests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import DataStructures.CommandConverter;
import DataStructures.DatabaseContainer;
import Databases.FunctionsDatabase;
import Databases.HistoryDatabase;

public class CommandConverterTest {

	@Test
	public void testCommandIdentifications() throws ClassNotFoundException, SQLException {
		HistoryDatabase history = new HistoryDatabase();
        FunctionsDatabase functions = new FunctionsDatabase(history.getCon());
        DatabaseContainer databases = new DatabaseContainer(history, functions);
		
		CommandConverter check1 = new CommandConverter("3+5", databases);
		CommandConverter check2 = new CommandConverter("function e d = e + d", databases);
		CommandConverter check3 = new CommandConverter("function e d", databases);
		CommandConverter check4 = new CommandConverter(" function e d", databases);
		CommandConverter check5 = new CommandConverter("3+5", databases);
		assertTrue(check1.checkForEquation() == true);
		assertTrue(check1.checkForFunctionApplication() == false);
		assertTrue(check1.checkForFunctionDeclaration() == false);
		assertTrue(check2.checkForEquation() == false);
		assertTrue(check2.checkForFunctionApplication() == false);
		assertTrue(check2.checkForFunctionDeclaration() == true);
		assertTrue(check3.checkForEquation() == false);
		assertTrue(check3.checkForFunctionApplication() == true);
		assertTrue(check3.checkForFunctionDeclaration() == false);
		assertTrue(check4.checkForEquation() == false);
		assertTrue(check4.checkForFunctionApplication() == true);
		assertTrue(check4.checkForFunctionDeclaration() == false);
		assertTrue(check5.checkForEquation() == true);
		assertTrue(check5.checkForFunctionApplication() == false);
		assertTrue(check5.checkForFunctionDeclaration() == false);
	}

}