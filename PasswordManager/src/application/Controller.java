package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void switchToSignUp(ActionEvent e) throws IOException {
		Parent signIn = FXMLLoader.load(getClass().getResource("EnterInfo.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(signIn);
		stage.setScene(scene);
		stage.show();
		
	}
	
	
	

}
