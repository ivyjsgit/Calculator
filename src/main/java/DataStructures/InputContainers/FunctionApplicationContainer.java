package DataStructures.InputContainers;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.script.ScriptException;

import DataStructures.WhitespaceRemover;

public class FunctionApplicationContainer {

	private String functionApplication;
	private DatabaseContainer databases;
	
	public FunctionApplicationContainer(String functionApplication, DatabaseContainer databases) {
		System.out.println(functionApplication);
		this.functionApplication = WhitespaceRemover.removeWhitespace(functionApplication);
		System.out.println(functionApplication);
		this.databases = databases;
	}
	
	public String run() throws ScriptException, SQLException {

		String functionEquation = getFunction();
		ArrayList<String> newParameters = getParameters(functionApplication);
		String result = databases.getFunctionApplication(functionEquation, newParameters);
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
	
	private String getFunction() throws SQLException {
		String name = getName(functionApplication);
		return databases.getFunction(name);
	}
	
}
