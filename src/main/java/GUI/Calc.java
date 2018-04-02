package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Border;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Calc extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//if this is on a Mac
			BorderPane root;
			//For some reason, you need a Resources folder to work with Mac? It crashes without you having one because it looks in there first I guess
			if(System.getProperty("os.name").startsWith("Mac")) {
				 root = FXMLLoader.load(getClass().getResource("/CalcGUI.fxml"));
			}else{
				 root = FXMLLoader.load(getClass().getResource("CalcGUI.fxml"));

			}
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}


    public static void main(String[] args) {
        launch(args);
    }
}
