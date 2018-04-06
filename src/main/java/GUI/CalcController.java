package GUI;

import com.github.daytron.simpledialogfx.data.DialogStyle;
import com.github.daytron.simpledialogfx.dialog.Dialog;
import com.github.daytron.simpledialogfx.dialog.DialogType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.TreeMap;

import Databases.*;
import DataStructures.*;
import DataStructures.InputContainers.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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
            Dialog  errorMessage = new Dialog(DialogStyle.HEADLESS,"An error has occurred. Please show this to the developers", e);
            errorMessage.show();
		}

        //https://stackoverflow.com/a/25252616
        input.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER)  {
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
		}catch (ArrayIndexOutOfBoundsException e){
		    Dialog errorMessage = new Dialog(DialogType.ERROR, DialogStyle.HEADLESS, "An error has occurred", "Error, function is not defined. Please define the function");
            errorMessage.show();
        }catch (IndexOutOfBoundsException e) {
            Dialog errorMessage = new Dialog(DialogType.ERROR, DialogStyle.HEADLESS, "An error has occurred", "Parameters do not match function definition. Please check your parameters.");
            errorMessage.show();
        }catch (Exception e){
            //https://github.com/Daytron/SimpleDialogFX
            System.out.println(e);
            Dialog  errorMessage = new Dialog(DialogStyle.HEADLESS,"An error has occurred. Please show this to the developers", e);
            errorMessage.show();
        }

	}
	public void setUpHistory(HistoryDatabase history) {
		//Treemaps come sorted.
        try {
            TreeMap<String, String> historyResults = new TreeMap<>();
            historyResults.putAll(history.getAllTimeStamps());
            for (String key : historyResults.keySet()) {
                calculations.add(historyResults.get(key) + ": " + key);
            }
        }catch (SQLException e){
            Dialog  errorMessage = new Dialog(DialogStyle.HEADLESS,"An error has occurred. Please show this to the developers", e);
            errorMessage.show();
        }
	}
	public void displayHelpMenu(){
        System.out.println("Helping!");
    }
}
