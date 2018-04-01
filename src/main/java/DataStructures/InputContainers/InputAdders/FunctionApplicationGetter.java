package DataStructures.InputContainers.InputAdders;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class FunctionApplicationGetter {

	private String equation;
	private ArrayList<String> parameters = new ArrayList<String>();
	private ArrayList<String> paremVariables = new ArrayList<String>();
	
	public FunctionApplicationGetter(String equation, ArrayList<String> parameters, ArrayList<String> paremVariables) {
		this.equation = equation;
		this.parameters = parameters;
		this.paremVariables = paremVariables;
	}
	
	public String run() throws ScriptException {
		return replaceVariables(parameters, paremVariables, equation);
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
