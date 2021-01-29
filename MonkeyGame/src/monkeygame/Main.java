/*
 * Main.java
 * Purpose: Calls the files required to run the monkey game
 * Author: Ashley Kim
 * Date: October 28, 2020
 * Course: ICS4U1
 */
package monkeygame;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle( "Monkey Game" );
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MonkeyGame.fxml"));
			BorderPane root = (BorderPane)loader.load();
			Scene scene = new Scene(root,1000,700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			MonkeyGameController controller = loader.getController();
			primaryStage.setScene(scene);
			controller.getScene(primaryStage);
			controller.gameLoop();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
