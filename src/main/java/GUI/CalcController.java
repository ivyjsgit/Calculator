package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.script.ScriptException;


import Databases.*;
import DataStructures.*;
import DataStructures.InputContainers.*;

public class CalcController {

	@FXML
	private ListView<String> info;

	@FXML
	private ObservableList<String> calculations = FXCollections.observableArrayList();

	@FXML
	private TextField input;

	@FXML
	private Button enter;

	private String userEntered;

	DatabaseContainer databases = new DatabaseContainer(null, null);


	public void initialize() {
		info.setItems(calculations);

		try {
		  HistoryDatabase history = new HistoryDatabase();
		  FunctionsDatabase functions = new FunctionsDatabase(history.getCon());
		  databases.setHistoryDatabase(history);
		  databases.setFunctionsDatabase(functions);
		  setUpHistory(history);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}

	}

	public void userInput() {
		userEntered = input.getText();
	}

	public void calculate() throws SQLException, ScriptException {
		userInput();
		CommandConverter converter = new CommandConverter(userEntered, databases);
		String result = converter.run();
		calculations.add(result);
		input.clear();

	}
	public void setUpHistory(HistoryDatabase history) throws SQLException {
		//Treemaps come sorted.
		TreeMap<String,String> historyResults = new TreeMap<>();
		historyResults.putAll(history.getAllTimeStamps());
		for(String key: historyResults.keySet()){
			calculations.add(historyResults.get(key) + ": " + key);
		}
	}
}
