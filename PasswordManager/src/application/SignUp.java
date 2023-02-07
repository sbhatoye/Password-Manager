package application;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SignUp extends Login {

	/**
	 * This is the controller class for the Sign Up Page 
	 * 
	 **/

	@FXML
	private TextField fn;
	@FXML
	private TextField ln;
	@FXML
	private TextField em;
	@FXML
	private TextField cn;
	@FXML
	private TextField bk;
	@FXML
	private PasswordField p;
	@FXML
	private Button backLogin;
	@FXML
	private Stage signUpStage;
	@FXML
	private Scene signUpScene;
	@FXML
	private Parent signUpRoot;

	public void submit(ActionEvent e) throws SQLException {

		// Store data entered by user into string variables
		String firstName = fn.getText();
		String lastName = ln.getText();
		String email = em.getText();
		String password = p.getText();

		// SQL statement checks to see if email (unique identifier) entered by user exists in database
		PreparedStatement userExists = connect.prepareStatement("select * from sign_up_form where email = ?");
		userExists.setString(1, email);

		// Execute the query to return a result set
		ResultSet result = userExists.executeQuery();

		/**
		 * 
		 * isBeforeFirst() method used to check if result set is empty --> returns false
		 * if result set is empty, returns true if email has already been taken
		 * 
		 **/

		if (result.isBeforeFirst()) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error");
			error.setHeaderText("A user with this email already exists. Please try again.");
			error.show();

		} else {

			// Store new user information in database
			PreparedStatement insert = connect.prepareStatement("insert into sign_up_form(first_name,last_name,email,password,ownership) values(?,?,?,?,?)");
			insert.setString(1, firstName);
			insert.setString(2, lastName);
			insert.setString(3, email);
			insert.setString(4, password);
			insert.setNString(5, email);
			insert.executeUpdate();

			Alert info = new Alert(AlertType.INFORMATION);
			info.setTitle("Success");
			info.setHeaderText("You're all signed up for Password Manager! \n" + "Please log in again to get started!");
			info.show();

			// Clear all text fields
			fn.setText("");
			ln.setText("");
			em.setText("");
			p.setText("");

		}

	}

	// Scene change from signup page to login page
	public void backToLogin(ActionEvent e) throws IOException {

		Parent signUp = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
		signUpStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		signUpScene = new Scene(signUp);
		signUpStage.setScene(signUpScene);
		signUpStage.setTitle("Login");
		signUpStage.show();

	}
}
