package DataStructures.DataStructureTests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import javax.script.ScriptException;

import org.junit.Test;

import DataStructures.InputContainers.DatabaseContainer;
import DataStructures.InputContainers.EquationContainer;
import Databases.FunctionsDatabase;
import Databases.HistoryDatabase;

public class EquationContainerTest {

	@Test
	public void answersTest() throws ScriptException, SQLException, ClassNotFoundException {
		HistoryDatabase history = new HistoryDatabase();
        FunctionsDatabase functions = new FunctionsDatabase(history.getCon());
        DatabaseContainer databases = new DatabaseContainer(history, functions);
		
		EquationContainer check1 = new EquationContainer("3+5", databases);
		assertTrue(check1.run().equals("8"));
		
		EquationContainer check2 = new EquationContainer("3 + 5", databases);
		assertTrue(check2.run().equals("8"));
		
		EquationContainer check3 = new EquationContainer("5-3", databases);
		assertTrue(check3.run().equals("2"));
		
		EquationContainer check4 = new EquationContainer("3-5", databases);
		assertTrue(check4.run().equals("-2"));
		
		EquationContainer check5 = new EquationContainer("3*5", databases);
		assertTrue(check5.run().equals("15"));
		
		EquationContainer check6 = new EquationContainer("6/3", databases);
		assertTrue(check6.run().equals("2"));
	}

}
