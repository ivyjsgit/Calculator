package DataStructures.InputContainers;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.script.ScriptException;

import DataStructures.Parser;
import DataStructures.InputContainers.InputAdders.EquationAdder;
import DataStructures.InputContainers.InputAdders.FunctionAdder;
import DataStructures.InputContainers.InputAdders.FunctionApplicationGetter;
import Databases.FunctionsDatabase;
import Databases.HistoryDatabase;

public class DatabaseContainer {

	private HistoryDatabase history;
	private FunctionsDatabase functions;

	public DatabaseContainer(HistoryDatabase history, FunctionsDatabase functions) {
		this.history = history;
		this.functions = functions;
	}

	public void setHistoryDatabase(HistoryDatabase history){
		this.history = history;
	}

	public void setFunctionsDatabase(FunctionsDatabase functions) {
		this.functions = functions;
	}

	public void addEquation(String equation) throws SQLException {
		new EquationAdder(equation, history);
	}

	public Boolean checkEquationInDatabase(String equation) throws SQLException {
		EquationAdder addEquation = new EquationAdder(equation, history);
		return addEquation.checkEquationInDatabase();
	}

	public void addFunction(String function) throws SQLException {
		new FunctionAdder(function, functions);
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
			result.add(splittedString[counter]);
			counter++;
		}

		return result;
	}

	public String getEquation(String str) {
		int index = str.indexOf("=") + 1;
		str = str.substring(index, str.length());
		str = Parser.removeAllWhitespace(str);
		return str;
	}


	public String getFunctionApplication(String name, ArrayList<String> parameters) throws ScriptException {
		ArrayList<String> paremVariables = getParameters(name);
		String equation = getEquation(name);
		FunctionApplicationGetter getFunctionApplication = new FunctionApplicationGetter(equation, parameters, paremVariables);
		return getFunctionApplication.run();
	}
	public void close() throws SQLException {
		history.closeCon();
		functions.closeCon();
	}
	
	public void dropEverything() throws SQLException {
		history.dropEverything();
		functions.dropEverything();
	}
	
	public ArrayList<String> getAllFunctions() throws SQLException {
		return functions.getAllFunctions();
	}

}
