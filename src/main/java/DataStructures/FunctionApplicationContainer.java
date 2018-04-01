package DataStructures;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.script.ScriptException;

public class FunctionApplicationContainer {

	private String functionApplication;
	private DatabaseContainer databases;
	
	public FunctionApplicationContainer(String functionApplication, DatabaseContainer databases) {
		this.functionApplication = functionApplication;
		this.databases = databases;
	}
	
	public String run() throws ScriptException, SQLException {

		String name = getName(functionApplication);
		String functionDeclaration = databases.getFunction(name);
		
		ArrayList<String> newParameters = getParameters(functionApplication);
		
		String result = databases.getFunctionApplication(functionDeclaration, newParameters);
		/*
		 * take the string and translate it into a list of some kind
		 * with the function name and all of the parameters as different
		 * elements
		 * 	the issue is that I need to split on 
		 * 
		 * check to see if the selection has a function in it
		 * 
		 * if it does then 
		 * 
		 * 
		 * ArrayList<String>function1nums = new ArrayList<String>();
        function1nums.add("1");
        function1nums.add("2");
        ArrayList<String>function2nums = new ArrayList<String>();
        function2nums.add("3");
        function2nums.add("2");
        
        assertTrue(databases.getFunctionApplication(function1, function1nums).equals("3"));
        assertTrue(databases.getFunctionApplication(function2, function2nums).equals("4"));
	}
		 */
		return result;
	}

	public ArrayList<String> getParameters(String str) {
		String[] splittedString = str.split(" ");
		ArrayList<String> result = new ArrayList<String>();
		for (int x = 1; x < splittedString.length; x++) {
			result.add(splittedString[x]);
		}

		return result;
	}

	private String getName(String function) {
		String[] splittedFunction = function.split(" ");
		return splittedFunction[0];
	}

	private String removeWhitespace(String equation) {
		if (equation.charAt(0) == ' ') {
			return removeWhitespace(equation.substring(1));
		} else {
			return equation;
		}
	}
	
}
