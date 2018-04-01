package DataStructures.DataStructureTests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.script.ScriptException;

import org.junit.Test;

import DataStructures.DatabaseContainer;
import DataStructures.EquationContainer;
import DataStructures.FunctionApplicationContainer;
import Databases.FunctionsDatabase;
import Databases.HistoryDatabase;

public class FunctionApplicationContainerTest {

	@Test
	public void test() throws ClassNotFoundException, SQLException, ScriptException {
		HistoryDatabase history = new HistoryDatabase();
        FunctionsDatabase functions = new FunctionsDatabase(history.getCon());
        DatabaseContainer database = new DatabaseContainer(history, functions);
		
        String function1 = "function 2 3";
        
        FunctionApplicationContainer application = new FunctionApplicationContainer(function1, database); 
        System.out.println(application.run());
	}

}
