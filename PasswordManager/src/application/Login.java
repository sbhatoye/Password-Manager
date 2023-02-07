package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login {

	/**
	 * This is the controller class for the Login Page
	 * 
	 **/

	protected String email;
	@FXML
	private TextField userEmail;
	@FXML
	private PasswordField userPass;
	@FXML
	private Stage logInStage;
	@FXML
	private Label loginLabel;
	@FXML
	private Scene logInScene;
	@FXML
	private Parent logInRoot;

	Connection connect = null;

	public Login() {

		// Connects controller class file to MySQl database in PhpMyAdmin
		connect = SQLDatabase.dataBConnect();

	}

	@FXML
	public void signIn(ActionEvent event) throws SQLException, IOException {

		// Store email and password entered by user into string variables
		String em = userEmail.getText();
		String pass = userPass.getText();

		// SQL statement matches credentials entered by user with data stored in
		// database
		PreparedStatement userLogin = connect.prepareStatement("select * from sign_up_form WHERE email=? and password=?");
		userLogin.setString(1, em);
		userLogin.setString(2, pass);

		// Execute the query to return a result set
		ResultSet result = userLogin.executeQuery();

		// If entered credentials match with data in database, user is directed to
		// Manage Passwords page
		if (result.next()) {
			ValidCredentials.sceneChange(event, "PassManager.fxml", "Manage Passwords", em, pass);

		} else {
			loginLabel.setText("Please enter correct email and password.");
		}

	}

	public void setEmail(String email) {

		this.email = email;

	}

	@FXML
	public void switchToSignUp(ActionEvent e) throws IOException {
		Parent signIn = FXMLLoader.load(getClass().getResource("EnterInfo.fxml"));
		logInStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		logInScene = new Scene(signIn);
		logInStage.setScene(logInScene);
		logInStage.setTitle("Sign Up");
		logInStage.show();

	}

}
