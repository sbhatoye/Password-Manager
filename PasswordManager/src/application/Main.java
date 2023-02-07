package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

	@FXML
	private Button signup = new Button("Sign Up");

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
			Scene scene = new Scene(root, 850, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Login");
			primaryStage.show();
			primaryStage.setOnCloseRequest(event -> logOut(primaryStage));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void logOut(Stage logOutStage) {

		Alert confirm = new Alert(AlertType.CONFIRMATION);
		confirm.setTitle("Exit");
		confirm.setHeaderText("Are you sure you want to exit?");
		if (confirm.showAndWait().get() == ButtonType.OK) {
			logOutStage.close();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
