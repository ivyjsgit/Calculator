package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.sql.*;

import javax.script.ScriptException;

import Databases.*;
import DataStructures.*;
import DataStructures.InputContainers.*;
import DataStructures.InputContainers.InputAdders.*;

public class CalcController {

	@FXML
	private ListView<String> info;

	@FXML
	private ObservableList<String> calculations = FXCollections.observableArrayList();

	@FXML
	private TextField input;

	@FXML
	private Button solver;

	private String userEntered;

	/*
	 * HistoryDatabase history = new HistoryDatabase(); FunctionsDatabase
	 * functions = new FunctionsDatabase(history.getCon()); DatabaseContainer
	 * databases = new DatabaseContainer(history, functions);
	 */

	public void initialize() {
		info.setItems(calculations);
	}

	public void userInput() {
		userEntered = input.getText();
	}

	public void calculate() throws ClassNotFoundException, SQLException, ScriptException {
		userInput();
		HistoryDatabase history = new HistoryDatabase();
		FunctionsDatabase functions = new FunctionsDatabase(history.getCon());
		DatabaseContainer databases = new DatabaseContainer(history, functions);
		CommandConverter converter = new CommandConverter(userEntered, databases);
		String result = converter.run();
		calculations.add(result);
		input.clear();

	}
}
