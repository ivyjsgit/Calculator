package DataStructures.InputContainers.InputAdders;

import java.sql.SQLException;

import DataStructures.WhitespaceRemover;
import Databases.FunctionsDatabase;

public class FunctionAdder {

	String function;
	FunctionsDatabase functions;
	
	public FunctionAdder(String function, FunctionsDatabase functions) throws SQLException {
		this.function = function;
		this.functions = functions;
		
		function = WhitespaceRemover.removeExtraWhitespace(function);
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
