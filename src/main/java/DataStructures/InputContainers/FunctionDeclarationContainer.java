package DataStructures.InputContainers;

import java.sql.SQLException;

public class FunctionDeclarationContainer {

	private String functionDeclaration;
	private DatabaseContainer databases;
	
	public FunctionDeclarationContainer(String functionDeclaration, DatabaseContainer databases) {
		this.functionDeclaration = functionDeclaration;
		this.databases = databases;
	}
	
	public String run() throws SQLException {
		databases.addFunction(functionDeclaration);
		return "";
	}

}
