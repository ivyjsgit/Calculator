package DataStructures;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import Databases.FunctionsDatabase;
import Databases.HistoryDatabase;

public class DatabaseContainer {

	private HistoryDatabase history;
	private FunctionsDatabase functions;

	public DatabaseContainer(HistoryDatabase history, FunctionsDatabase functions) {
		this.history = history;
		this.functions = functions;
	}

	public void addEquation(String equation) throws SQLException {
		equation = removeAllWhitespace(equation);
		history.insertEquation(equation);
	}

	public Boolean checkEquationInDatabase(String equation) throws SQLException {
		equation = removeAllWhitespace(equation);
		ArrayList<String> allEquations = history.getAllEquations();
		Boolean result = false;
		for (String str : allEquations) {
			if (str.equals(equation)) {
				result = true;
			}
		}
		return result;
	}

	private String removeAllWhitespace(String equation) {
		String[] splittedEquation = equation.split("");
		ArrayList<String> result = new ArrayList<String>();
		for (int x = 0; x < splittedEquation.length; x++) {
			if (!splittedEquation[x].equals(" ")) {
				result.add(splittedEquation[x]);
			}
		}
		return listToString(result);
	}

	private String listToString(ArrayList<String> list) {
		String result = "";
		for (int x = 0; x < list.size(); x++) {
			result = result + list.get(x);
		}
		return result;
	}

	public void addFunction(String function) throws SQLException {
		function = removeExtraWhitespace(function);
		if (!checkFunctionInDatabase(function)) {
			functions.insertFunction(function);
		}
	}

	private boolean checkFunctionInDatabase(String function) throws SQLException {
		boolean result = false;
		if (functionsContain(function)) {
			result = true;
		}
		return result;
	}

	private boolean functionsContain(String function) throws SQLException {
		boolean result = false;
		ArrayList<String> allFunctions = functions.getAllFunctions();
		for (int x = 0; x < allFunctions.size(); x++) {
			if (allFunctions.get(x).equals(function)) {
				result = true;
			}
		}
		return result;
	}

	private String removeExtraWhitespace(String function) {
		String[] splittedFunction = function.split(" ");
		for (int x = 0; x < splittedFunction.length; x++) {
			splittedFunction[x] = removeWhitespace(splittedFunction[x]);
		}
		String result = arrayToString(splittedFunction);
		return result;
	}

	private String arrayToString(String[] splittedFunction) {
		String result = "";
		for (int x = 0; x < splittedFunction.length; x++) {
			result = result + " " + splittedFunction[x];
		}
		return result.substring(1);
	}

	private String removeWhitespace(String str) {
		if (str.charAt(0) == ' ') {
			str = removeWhitespace(str.substring(1));
		} else if (str.charAt(str.length() - 1) == ' ') {
			str = removeWhitespace(str.substring(0, str.length() - 1));
		} else {
			return str;
		}
		return str;
	}

	public String getFunction(String functionName) throws SQLException {
		String result = "";
		ArrayList<String> allFunctions = functions.getAllFunctions();
		for (int x = allFunctions.size() - 1; x >= 0; x--) {
			if (getName(allFunctions.get(x)).equals(functionName)) {
				result = allFunctions.get(x);
			}
		}
		return result;
	}

	public String getName(String str) {
		String[] splittedString = str.split(" ");
		str = splittedString[0];
		return str;
	}

	public ArrayList<String> getParameters(String str) {
		String[] splittedString = str.split(" ");
		ArrayList<String> result = new ArrayList<String>();
		int counter = 1;
		while (!splittedString[counter].contains("=")) {
			System.out.println(splittedString[counter]);
			result.add(splittedString[counter]);
			counter++;
		}

		return result;
	}

	public String getEquation(String str) {
		int index = str.indexOf("=") + 1;
		str = str.substring(index, str.length());
		str = removeAllWhitespace(str);
		return str;
	}

	public String getFunctionApplication(String name, ArrayList<String> paremeters) throws ScriptException {
		ArrayList<String> paremVariables = getParameters(name);
		String equation = getEquation(name);
		equation = replaceVariables(paremeters, paremVariables, equation);
		return equation;
	}

	private String replaceVariables(ArrayList<String> paremeters, ArrayList<String> paremVariables, String equation) throws ScriptException {
		for (int x = 0; x < paremeters.size(); x++) {
			String replacement = paremeters.get(x);
			String variable = paremVariables.get(x);
			while (equation.contains(variable)) {
				equation = replace(equation, replacement, variable);
			}
		}
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		int result = (int) engine.eval(equation);
		return Integer.toString(result);
	}

	private String replace(String equation, String replacement, String variable) {
		int variableLength = variable.length();
		int index = equation.indexOf(variable);
		equation = equation.substring(0, index) + replacement + equation.substring(index + variableLength);
		return equation;
	}

}
