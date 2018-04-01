package DataStructures.InputContainers.InputAdders;

import java.sql.SQLException;
import java.util.ArrayList;

import DataStructures.WhitespaceRemover;
import Databases.HistoryDatabase;

public class EquationAdder {
	
	String equation;
	HistoryDatabase history;

	public EquationAdder(String equation, HistoryDatabase history) throws SQLException {
		this.equation = equation;
		this.history = history;
		equation = WhitespaceRemover.removeAllWhitespace(equation);
		history.insertEquation(equation);
	}

	public Boolean checkEquationInDatabase() throws SQLException {
		equation = WhitespaceRemover.removeAllWhitespace(equation);
		ArrayList<String> allEquations = history.getAllEquations();
		Boolean result = false;
		for (String str : allEquations) {
			if (str.equals(equation)) {
				result = true;
			}
		}
		return result;
	}

}
