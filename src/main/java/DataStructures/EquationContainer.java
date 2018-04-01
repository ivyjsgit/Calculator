package DataStructures;

import java.sql.SQLException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class EquationContainer {

	private String equation;
	private DatabaseContainer databases;
	
	public EquationContainer(String equation, DatabaseContainer databases) {
		this.equation = equation;
		this.databases = databases;
	}
	
	public String run() throws ScriptException, SQLException {
		
		/*
		 * https://stackoverflow.com/questions/2605032/is-there-an-eval-function-in-java
		 */
		
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		int result = (int) engine.eval(equation);
		
		databases.addEquation(equation);
		
		return Integer.toString(result);
	}

}
