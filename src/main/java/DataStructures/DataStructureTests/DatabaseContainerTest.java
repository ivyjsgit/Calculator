package DataStructures.DataStructureTests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.script.ScriptException;

import org.junit.Test;

import DataStructures.InputContainers.DatabaseContainer;
import Databases.FunctionsDatabase;
import Databases.HistoryDatabase;

public class DatabaseContainerTest {

	@Test
	public void addEquationTest() throws ClassNotFoundException, SQLException, ScriptException {
		HistoryDatabase history = new HistoryDatabase();
        FunctionsDatabase functions = new FunctionsDatabase(history.getCon());
        DatabaseContainer databases = new DatabaseContainer(history, functions);
        
        databases.addEquation("3 +5");
        databases.addEquation("5+3");
        
        assertTrue(databases.checkEquationInDatabase("3+ 5"));
        assertTrue(databases.checkEquationInDatabase("5+3"));
        
        databases.addFunction("function x y = x + y");
        databases.addFunction("another1 y e = y + y - e");
        String function1 = databases.getFunction("function");
        String function2 = databases.getFunction("another1");
        
        assertTrue(databases.getFunction("function").equals("function x y = x + y"));
        assertTrue(databases.getFunction("another1").equals("another1 y e = y + y - e"));
        
        assertTrue(databases.getName(function1).equals("function"));
        assertTrue(databases.getName(function2).equals("another1"));
        
        ArrayList<String> function1parems = new ArrayList<String>();
        function1parems.add("x");
        function1parems.add("y");
        ArrayList<String> function2parems = new ArrayList<String>();
        function2parems.add("y");
        function2parems.add("e");
        
        assertTrue(databases.getParameters(function1).equals(function1parems));
        assertTrue(databases.getParameters(function2).equals(function2parems));
        
        String function1equation = "x+y";
        String function2equation = "y+y-e";
        
        assertTrue(databases.getEquation(function1).equals(function1equation));
        assertTrue(databases.getEquation(function2).equals(function2equation)); 
        
        ArrayList<String>function1nums = new ArrayList<String>();
        function1nums.add("1");
        function1nums.add("2");
        ArrayList<String>function2nums = new ArrayList<String>();
        function2nums.add("3");
        function2nums.add("2");
        
        assertTrue(databases.getFunctionApplication(function1, function1nums).equals("3"));
        assertTrue(databases.getFunctionApplication(function2, function2nums).equals("4"));
        
        databases.dropEverything();
        assertTrue(databases.getAllFunctions().size() == 0);
        
        databases.close();

    }
	
}
