package DataStructures;

import java.sql.SQLException;

import javax.script.ScriptException;

import DataStructures.InputContainers.DatabaseContainer;
import DataStructures.InputContainers.EquationContainer;
import DataStructures.InputContainers.FunctionApplicationContainer;
import DataStructures.InputContainers.FunctionDeclarationContainer;

public class CommandConverter {

	private String command;
	private DatabaseContainer databases;
	
	public CommandConverter(String command, DatabaseContainer databases) {
		this.command = command;
		this.databases = databases;
	}
	
	public Boolean checkForFunctionDeclaration() {
		if (command.contains("=")) {
			return true;
		} else {
			return false;
		}
	}

	public String run() throws ScriptException, SQLException {

		if (checkForFunctionDeclaration()) {
			return handleFunctionDeclaration();
		} else if (checkForEquation()) {
			return handleEquation();
		} else if (checkForFunctionApplication()) {
			return handleFunctionApplication();
		} else {
			// make an exception to throw here.
			return " ";
		}
	}

	private String handleFunctionApplication() throws ScriptException, SQLException {
		FunctionApplicationContainer functionApplicationContainer = new FunctionApplicationContainer(command, databases);
		return functionApplicationContainer.run();
	}

	private String handleEquation() throws ScriptException, SQLException {
		EquationContainer equationContainer = new EquationContainer(command, databases);
		return equationContainer.run();
	}

	private String handleFunctionDeclaration() throws SQLException {
		FunctionDeclarationContainer functionDeclarationContainer = new FunctionDeclarationContainer(command, databases);
		return functionDeclarationContainer.run();
	}

	public Boolean checkForEquation() {
		String[] splittedCommand = command.split(" ");
		return checkForLetters(splittedCommand);
	}

	private Boolean checkForLetters(String[] splittedCommand) {
		Boolean result = true;
		for (int x = 0; x < splittedCommand.length; x++) {
		/*
		 * https://stackoverflow.com/questions/5238491/check-if-string-contains-only-letters
		 */
			if (splittedCommand[x].matches("[a-zA-Z]+")) {
				result = false;
			}
		}
		return result;
	}

	public Boolean checkForFunctionApplication() {
		return !(checkForFunctionDeclaration() || checkForEquation());
		// this is -rigged right now to work, it would be more reliable if this was checking
		// the database for the function name.
	}

}
