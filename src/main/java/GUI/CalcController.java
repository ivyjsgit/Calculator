package GUI;

import com.github.daytron.simpledialogfx.data.DialogStyle;
import com.github.daytron.simpledialogfx.dialog.Dialog;
import com.github.daytron.simpledialogfx.dialog.DialogType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.script.ScriptException;

import Databases.*;
import DataStructures.*;
import DataStructures.InputContainers.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class CalcController {

	@FXML
	private ListView<String> info;

	@FXML
	private ObservableList<String> calculations = FXCollections.observableArrayList();

	@FXML
	private TextField input;

	@FXML
	private Button enter;

	@FXML
	private Button graph;

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
			Dialog errorMessage = new Dialog(DialogStyle.HEADLESS,
					"An error has occurred. Please show this to the developers", e);
			errorMessage.show();
		}

		// https://stackoverflow.com/a/25252616
		input.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if (keyEvent.getCode() == KeyCode.ENTER) {
					calculate();
					input.setText("");
				}
			}
		});
	}

	public void userInput() {
		userEntered = input.getText();
	}

	public void calculate() {
		try {
			userInput();
			CommandConverter converter = new CommandConverter(userEntered, databases);
			String result = converter.run();
			calculations.add(result);
			input.clear();
		} catch (ArrayIndexOutOfBoundsException e) {
			Dialog errorMessage = new Dialog(DialogType.ERROR, DialogStyle.HEADLESS, "An error has occurred",
					"Error, function is not defined. Please define the function");
			errorMessage.show();
		} catch (IndexOutOfBoundsException e) {
			Dialog errorMessage = new Dialog(DialogType.ERROR, DialogStyle.HEADLESS, "An error has occurred",
					"Parameters do not match function definition. Please check your parameters.");
			errorMessage.show();
		} catch (Exception e) {
			// https://github.com/Daytron/SimpleDialogFX
			Dialog errorMessage = new Dialog(DialogStyle.HEADLESS,
					"An error has occurred. Please show this to the developers", e);
			errorMessage.show();
		}

	}

	public void graphWindow() {
		Group root = new Group();
		Scene scene = new Scene(root, 600, 300, Color.GHOSTWHITE);
		ArrayList<XYContainer> result;

		for (int r = 0; r < 600; r++) {
			Text xAxis = new Text(r, 150, "-");
			root.getChildren().add(xAxis);
			Text yAxis = new Text(300, r, "|");
			root.getChildren().add(yAxis);
		}

		TextField function = new TextField();
		function.setPromptText("Form: x^2");
		TextField x = new TextField();
		x.setPromptText("Enter an x bound value. 10 -> -10 to 10");
		TextField y = new TextField();
		y.setPromptText("Enter a y bound value. 10 -> -10 to 10");

		GraphingContainer userGraph = new GraphingContainer(function.getText(), Integer.parseInt(x.getText()), Integer.parseInt(y.getText()), 10);
		try {
			result.addAll(userGraph.run());
		} catch (ScriptException e) {
			Dialog errorMessage = new Dialog(DialogType.ERROR, DialogStyle.HEADLESS, "Something went wrong with graphing.");
			errorMessage.show();
		}
		for(XYContainer p : result){
			Text ponint = new Text(300 + p.getX() * 30, 150 - p.getY(), ".");
			root.getChildren().add(ponint);

			}
		}

	}

	public void setUpHistory(HistoryDatabase history) {
		// Treemaps come sorted.
		try {
			TreeMap<String, String> historyResults = new TreeMap<>();
			historyResults.putAll(history.getAllTimeStamps());
			for (String key : historyResults.keySet()) {
				calculations.add(historyResults.get(key) + ": " + key);
			}
		} catch (SQLException e) {
			Dialog errorMessage = new Dialog(DialogStyle.HEADLESS,
					"An error has occurred. Please show this to the developers", e);
			errorMessage.show();
		}
	}

	public void displayHelpMenu() {
		System.out.println("Helping!");
	}
}
