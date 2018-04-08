package DataStructures.InputContainers;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.script.ScriptException;

import DataStructures.WhitespaceRemover;

public class FunctionApplicationContainer {

	private String functionApplication;
	private DatabaseContainer databases;
	private int skippedIndexes = 0;

	public FunctionApplicationContainer(String functionApplication, DatabaseContainer databases) {
		this.functionApplication = WhitespaceRemover.removeWhitespace(functionApplication);
		this.databases = databases;
	}

	public String run() throws ScriptException, SQLException {

		String functionEquation = getFunction();
		ArrayList<String> newParameters = getParameters(functionApplication);
		String result = databases.getFunctionApplication(functionEquation, newParameters);
		return result;
	}

	public ArrayList<String> getParameters(String str) throws ScriptException, SQLException {
		String[] splittedString = str.split(" ");
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
				String newParem = func.run();
				resultList.add(newParem);
				x = skippedIndexes - 1;
			} else {
				resultList.add(str);
			}
		}
		printArray(WhitespaceRemover.listToArray(resultList));
		return WhitespaceRemover.listToArray(resultList);
	}

	private String grabAllParameters(int x, String[] splittedString) {
		String result = "";
		int count = 1;
		result = result + " " + splittedString[x];
		x++;
		while (count != 0) {
			if (splittedString[x].contains(")")) {
				count --;
			} else if (splittedString[x].contains("(")) {
				count ++;
			}
			result = result + " " + splittedString[x];
			x++;
		}
		skippedIndexes = x;
		result = WhitespaceRemover.removeWhitespaceAndParenthesis(result);
		return result;
	}

	private void printArray(String[] splittedString) {
		for (int x = 0; x < splittedString.length; x++) {
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
