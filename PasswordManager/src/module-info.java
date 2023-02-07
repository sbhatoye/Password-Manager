module PasswordManager {
	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.base;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml,javafx.base;
}
