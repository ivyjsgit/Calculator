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
		 * I need to add the ability to have a function application
		 * on a function application.
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
