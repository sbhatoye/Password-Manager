package application;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Vector;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * 
 * This is controller class for the Manage Passwords Page 
 * 
 **/

public class Manager extends Login implements Initializable {

	@FXML
	private Button exitButton;
	@FXML
	private AnchorPane managerScene;
	@FXML
	private TextField ID;
	@FXML
	private TextField website;
	@FXML
	private TextField emailInput;
	@FXML
	private TextField username;
	@FXML
	private TextField password;
	@FXML
	private TextArea notes;

	@FXML
	private TableView<User> pmTable;
	@FXML
	private TableColumn<User, String> IDColumn;
	@FXML
	private TableColumn<User, String> websiteColumn;
	@FXML
	private TableColumn<User, String> emailColumn;
	@FXML
	private TableColumn<User, String> usernameColumn;
	@FXML
	private TableColumn<User, String> passwordColumn;
	@FXML
	private TableColumn<User, String> notesColumn;

	Stage managerStage;

	ObservableList<User> users = FXCollections.observableArrayList();

	// Allows user to add entry in table 
	public void add(ActionEvent e) throws SQLException {

		String userID = ID.getText();
		String userWebsite = website.getText();
		String userEmail = emailInput.getText();
		String userUsername = username.getText();
		String userPassword = password.getText();
		String userNotes = notes.getText();

		PreparedStatement insert = connect.prepareStatement("insert into update_manager(website,email,username,password,notes,ownership)values(?,?,?,?,?,?)");

		insert.setString(1, userID);
		insert.setString(1, userWebsite);
		insert.setString(2, userEmail);
		insert.setString(3, userUsername);
		insert.setString(4, userPassword);
		insert.setString(5, userNotes);
		insert.setString(6, email);
		insert.executeUpdate();

		User user = new User();
		user.setID(userID);
		user.setWebsite(userWebsite);
		user.setEmail(userEmail);
		user.setUsername(userUsername);
		user.setPassword(userPassword);
		user.setNotes(userNotes);
		user.setOwnership(email);

		users.add(user);

		Alert info = new Alert(AlertType.INFORMATION);
		info.setTitle("Password Added");
		info.setHeaderText("Password successfully added");
		info.show();

		ID.setText("");
		website.setText("");
		emailInput.setText("");
		username.setText("");
		password.setText("");
		notes.setText("");

		updateTable();

	}
	
	// Allows user to delete entry in table 
	public void delete(ActionEvent e) throws SQLException {

		PreparedStatement delete = connect.prepareStatement("delete from update_manager where ID=?");
		delete.setString(1, ID.getText());
		delete.executeUpdate();

		Alert info = new Alert(AlertType.INFORMATION);
		info.setTitle("Password Deleted");
		info.setHeaderText("Password successfully deleted");
		info.show();

		ID.setText("");
		website.setText("");
		emailInput.setText("");
		username.setText("");
		password.setText("");
		notes.setText("");

		updateTable();

	}
	
	// Allows user to refresh contents of table 
	public void refresh(ActionEvent e) throws SQLException {
		updateTable();

	}

	// Allows user to select row in table 
	@FXML
	public void selectRow(MouseEvent event) {

		int index = -1;

		index = pmTable.getSelectionModel().getSelectedIndex(); // selects row
		if (index <= -1) {
			return;
		}

		users = FXCollections.observableArrayList();
		users = pmTable.getSelectionModel().getSelectedItems();
		TablePosition tableposition = pmTable.getSelectionModel().getSelectedCells().get(0);
		int row = tableposition.getRow();

		ID.setText(IDColumn.getCellData(index).toString());
		website.setText(websiteColumn.getCellData(index).toString());
		emailInput.setText(emailColumn.getCellData(index).toString());
		username.setText(usernameColumn.getCellData(index).toString());
		password.setText(passwordColumn.getCellData(index).toString());
		notes.setText(notesColumn.getCellData(index).toString());

	}

	public void logOut(ActionEvent e) {

		Alert confirm = new Alert(AlertType.CONFIRMATION);
		confirm.setTitle("Log Out");
		confirm.setHeaderText("Are you sure you want to log out?");

		if (confirm.showAndWait().get() == ButtonType.OK) {
			managerStage = (Stage) managerScene.getScene().getWindow();

			managerStage.close();
		}

	}
	
	// Updates contents in table 
	public void updateTable() throws SQLException {

		users = FXCollections.observableArrayList();
		PreparedStatement showTable = connect.prepareStatement("select * from update_manager WHERE ownership = ?");
		showTable.setString(1, email);
		ResultSet result = showTable.executeQuery();

		while (result.next()) {
			User userAdd = new User();
			userAdd.setID(result.getString("id"));
			userAdd.setWebsite(result.getString("website"));
			userAdd.setEmail(result.getString("email"));
			userAdd.setUsername(result.getString("username"));
			userAdd.setPassword(result.getString("password"));
			userAdd.setNotes(result.getString("notes"));
			userAdd.setOwnership(result.getString("ownership"));
			users.add(userAdd);

		}

		pmTable.setItems(users);

		IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
		websiteColumn.setCellValueFactory(new PropertyValueFactory<>("website"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
		passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
		notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));

	}

	// Overrides Initializable interface
	@Override
	public void initialize(URL url, ResourceBundle resourceB) {

		try {
			updateTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
