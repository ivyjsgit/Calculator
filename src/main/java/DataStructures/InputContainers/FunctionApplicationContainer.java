package DataStructures.InputContainers;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.script.ScriptException;

import DataStructures.WhitespaceRemover;

public class FunctionApplicationContainer {

	private String functionApplication;
	private DatabaseContainer databases;

	public FunctionApplicationContainer(String functionApplication, DatabaseContainer databases) {
		this.functionApplication = WhitespaceRemover.removeWhitespace(functionApplication);
		this.databases = databases;
	}

	public String run() throws ScriptException, SQLException {

		String functionEquation = getFunction();
		ArrayList<String> newParameters = getParameters(functionApplication);
		String result = databases.getFunctionApplication(functionEquation, newParameters);
		/*
		 * I need to add the ability to have a function application on a
		 * function application.
		 */
		return result;
	}

	public ArrayList<String> getParameters(String str) throws ScriptException, SQLException {
		String[] splittedString = str.split(" ");
		printArray(splittedString);
		splittedString = accountForParenthesis(splittedString);
		printArray(splittedString);
		ArrayList<String> result = new ArrayList<String>();
		for (int x = 1; x < splittedString.length; x++) {
			String doubleVariable = WhitespaceRemover.changeToDouble(splittedString[x]);
			result.add(doubleVariable);
		}
		return result;
	}

	private String[] accountForParenthesis(String[] splittedString) throws ScriptException, SQLException {
		ArrayList<String> resultList = new ArrayList<String>();
		for (int x = 0; x < splittedString.length; x++) {
			String str = splittedString[x];
			if (str.contains("(")) {
				String parameterWithFunction = grabAllParameters(x, splittedString);
				FunctionApplicationContainer func = new FunctionApplicationContainer(parameterWithFunction, databases);
				parameterWithFunction = func.run();
				resultList.add(parameterWithFunction);
				x = x + (parameterWithFunction.length() - 2);
			} else {
				resultList.add(str);
			}
		}
		System.out.println(resultList.toString());
		return WhitespaceRemover.listToArray(resultList);
	}

	private String grabAllParameters(int x, String[] splittedString) {
		String result = "";
		while (!splittedString[x].contains(")")) {
			result = result + " " + splittedString[x];
			x++;
		}
		result = result + " " + splittedString[x];
		result = result.substring(2, result.length() - 1);
		return result;
	}

	private void printArray(String[] splittedString) {
		for (int x = 0; x < splittedString.length; x++) {
			System.out.println(splittedString[x]);
		}
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
