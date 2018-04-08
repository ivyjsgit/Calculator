package DataStructures.InputContainers;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import DataStructures.Parser;

public class EquationContainer {

	private String equation;
	private DatabaseContainer databases;
	
	public EquationContainer(String equation, DatabaseContainer databases) {
		this.equation = equation;
		this.databases = databases;
	}
	
	public String run() throws ScriptException, SQLException, ArrayIndexOutOfBoundsException {
		
		/*
		 * I got the below code for evaluating the result of a string from
		 * https://stackoverflow.com/questions/2605032/is-there-an-eval-function-in-java
		 */
		
		changeToDouble();
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		double result = (double) engine.eval(equation);
		
		databases.addEquation(equation);
		
		return Double.toString(result);
	}

	private void changeToDouble() {
		ArrayList<String> splittedArray = splitNums();
		splittedArray = toDouble(splittedArray);
		equation = Parser.listToString(splittedArray);

		
	}

	private ArrayList<String> toDouble(ArrayList<String> splittedArray) {
		for (int x = 0; x < splittedArray.size(); x++) {
			if (stringIsNum(splittedArray.get(x)) && (!splittedArray.get(x).contains("."))) {
				splittedArray.set(x, splittedArray.get(x) + ".0");
			}
		}
		return splittedArray;
	}

	private boolean stringIsNum(String string) {
		for (int x = 0; x < string.length(); x++) {
			if (!isNum(string.charAt(x)) && string.charAt(x) != '.') {
				return false;	
			}
		}
		return true;
	}

	private ArrayList<String> splitNums() {
		ArrayList<String> result = new ArrayList<String>();
		for (int x = 0; x < equation.length(); x++) {
			char ch = equation.charAt(x);
			if (isNum(ch)) {
				int endOfNum = getNumber(x);
				String str = equation.substring(x, endOfNum);
				result.add(str);
				x = endOfNum - 1;
			} else {
				result.add(String.valueOf(ch));
			}
		}
		return result;
	}

	private int getNumber(int x) {
		char ch = equation.charAt(x);
		while ((isNum(ch) || ch == '.') && (x < equation.length() - 1)) {
			x++;
			ch = equation.charAt(x);
		}
		if (isNum(equation.charAt(x))) {
			x++;
		}
		return x;
	}

	private boolean isNum(char ch) {
		if (ch == '1') {
			return true;
		} else if (ch == '2') {
			return true;
		} else if (ch == '3') {
			return true;
		} else if (ch == '4') {
			return true;
		} else if (ch == '5') {
			return true;
		} else if (ch == '6') {
			return true;
		} else if (ch == '7') {
			return true;
		} else if (ch == '8') {
			return true;
		} else if (ch == '9') {
			return true;
		} else if (ch == '0') {
			return true;
		} else {
			return false;
		}
	}

}
