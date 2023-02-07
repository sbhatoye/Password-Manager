package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class checks to see if user should be granted access to Password Manager
 * based on the credentials entered by them
 **/

public class ValidCredentials {

	public static void sceneChange(ActionEvent event, String fxmlFile, String title, String email, String password)
			throws IOException {

		Parent root = null;
		if (email != null && password != null) {

			FXMLLoader loader = new FXMLLoader(ValidCredentials.class.getResource(fxmlFile));
			root = loader.load();
			Login login = loader.getController();
			login.setEmail(email);

		} else {
			root = FXMLLoader.load(ValidCredentials.class.getResource(fxmlFile));

		}

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle(title);
		stage.setScene(new Scene(root, 850, 500));
		stage.show();

	}

}