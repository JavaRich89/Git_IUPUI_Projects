import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Main extends Application implements Initializable {

	static ArrayList<String> lines = new ArrayList<String>();
	@FXML
	public static Stage primaryStage;
	@FXML
	private Button loginAdminButton;
	@FXML
	private Button loginGuestButton;
	@FXML
	private TextField usernameField;
	@FXML
	private TextField passwordField;
	@FXML
	private Label loginFailed;
	@FXML
	private MenuItem exitItem;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loginAdminButton.setOnAction(e -> checkLogin(e));

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("home_hotel.fxml"));
		Scene scene = new Scene(parent);
		primaryStage.setTitle("Hotel Database - Login");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@FXML
	public void loginAdminButtonClicked(ActionEvent event) throws IOException {
		checkLogin(event);
	}

	@FXML
	public void checkLogin(ActionEvent event) {
		String username = usernameField.getText();
		String password = passwordField.getText();
		if (Connection.adminLogin(username,password)) {
			try {
				openAdminScene(event);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else {
			setLoginFailed();
		}
	}

	@FXML
	public void setLoginFailed() {
		loginFailed.setVisible(true);
	}

	@FXML
	private void openAdminScene(ActionEvent event) throws IOException {
		Parent adminViewParent = FXMLLoader.load(getClass().getResource("admin_hotel.fxml"));
		Scene adminViewScene = new Scene(adminViewParent);
		Stage adminWindow = primaryStage;
		adminWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
		adminWindow.setScene(adminViewScene);
		adminWindow.show();
	}

	@FXML
	private void openGuestScene(ActionEvent event) throws IOException {
		Parent guestViewParent = FXMLLoader.load(getClass().getResource("guest_hotel.fxml"));
		Scene guestViewScene = new Scene(guestViewParent);
		Stage guestWindow = primaryStage;
		guestWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
		guestWindow.setScene(guestViewScene);
		guestWindow.show();
	}

	@FXML
	public void exitProgram() {
		exitItem.setOnAction(e -> System.exit(0));
	}

	public static void main(String[] args) {
		launch(args);
	}

}
