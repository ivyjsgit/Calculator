package DataStructures.InputContainers.InputAdders;

import java.sql.SQLException;

import DataStructures.Parser;
import Databases.FunctionsDatabase;

public class FunctionAdder {

	String function;
	FunctionsDatabase functions;
	
	public FunctionAdder(String function, FunctionsDatabase functions) throws SQLException {
		this.function = function;
		this.functions = functions;
		function = Parser.removeExtraWhitespace(function);
		if (!checkFunctionInDatabase(function)) {
			functions.insertFunction(function);
		}
	}
	
	private boolean checkFunctionInDatabase(String function) throws SQLException {
		boolean result = false;
		if (functions.getAllFunctions().contains(function)) {
			result = true;
		}
		return result;
	}

}
