import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GuestController {

	//FXML Variables
	@FXML
	MenuItem importReviewsButton;
	@FXML
	MenuItem importSeedButton;
	@FXML
	MenuItem exitButton;
	@FXML
	private TextField searchField;
	@FXML
	private Button searchButton;
	@FXML
	private Button advancedSearchButton;
	@FXML
	private Button showRatingButton;
	@FXML
	private ListView<String> displayList;
	@FXML
	private ObservableList<String> listItems = FXCollections.observableArrayList();

	//Rating Labels
	@FXML
	private Label allLabel;
	@FXML
	private Label priceLabel;
	@FXML
	private Label cleanLabel;
	@FXML
	private Label locLabel;
	@FXML
	private Label amenLabel;
	@FXML
	private Label servLabel;
	@FXML
	private Label roomLabel;

	//MenuItem buttons
	//Country
	@FXML
	private MenuItem selChina;
	@FXML
	private MenuItem selCA;
	@FXML
	private MenuItem selIndia;
	@FXML
	private MenuItem selUS;
	@FXML
	private MenuItem selUK;
	//State
	@FXML
	private MenuButton selState;
	@FXML
	private MenuItem selCalifornia;
	@FXML
	private MenuItem selIllinois;
	@FXML
	private MenuItem selNevada;
	@FXML
	private MenuItem selNewYork;
	//City
	@FXML
	private MenuButton selCity;
	@FXML
	private MenuItem selBeijing;
	@FXML
	private MenuItem selChicago;
	@FXML
	private MenuItem selLasVegas;
	@FXML
	private MenuItem selLondon;
	@FXML
	private MenuItem selMontreal;
	@FXML
	private MenuItem selNewDelhi;
	@FXML
	private MenuItem selNewYorkCity;
	@FXML
	private MenuItem selSanFrancisco;
	@FXML
	private MenuItem selShanghai;

	//JDBC Variables
	java.sql.Connection conn = null;
	PreparedStatement ps = null;

	//SQL Statements
	private final static String SEARCH_SQL = "SELECT hotel.hotelName FROM hotel, location WHERE hotel.locID = location.locID AND hotel.hotelName LIKE ?";
	private final static String SEARCH_SQL_COUNTRY = "SELECT hotel.hotelName FROM hotel, location WHERE hotel.locID = location.locID AND location.country = ?";
	private final static String SEARCH_SQL_STATE = "SELECT hotel.hotelName FROM hotel, location WHERE hotel.locID = location.locID AND location.state = ?";
	private final static String SEARCH_SQL_CITY = "SELECT hotel.hotelName FROM hotel, location WHERE hotel.locID = location.locID AND location.city = ?";

	//SQL Rating statements
	private final static String SEARCH_HOTELID = "SELECT hotelID FROM hotel where hotelName like ?";
	private final static String SEARCH_OVERALL = "SELECT TRUNC((SUM(rating)/6), 1) AS overAll FROM rating WHERE hotelID = ?";
	private final static String SEARCH_PRICE = "select TRUNC((SUM(rating)/COUNT(catName)), 1) AS Rating from rating where hotelID = ? and catName = 'Price'";
	private final static String SEARCH_CLEAN = "select TRUNC((SUM(rating)/COUNT(catName)), 1) AS Rating from rating where hotelID = ? and catName = 'Cleanliness'";
	private final static String SEARCH_LOC = "select TRUNC((SUM(rating)/COUNT(catName)), 1) AS Rating from rating where hotelID = ? and catName = 'Location'";
	private final static String SEARCH_AMEN = "select TRUNC((SUM(rating)/COUNT(catName)), 1) AS Rating from rating where hotelID = ? and catName = 'Amenities'";
	private final static String SEARCH_SERV = "select TRUNC((SUM(rating)/COUNT(catName)), 1) AS Rating from rating where hotelID = ? and catName = 'Service'";
	private final static String SEARCH_ROOM = "select TRUNC((SUM(rating)/COUNT(catName)), 1) AS Rating from rating where hotelID = ? and catName = 'Room'";

	//SQL Input Variables
	private static String country;
	private static String state;
	private static String city;


	public void initialize(URL location, ResourceBundle resources) {
		searchButton.setOnAction(e -> {
			try {
				searchButtonClicked(e);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		});
		selChina.setOnAction(e -> {
			try {
				searchChinaButtonClicked(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		selCA.setOnAction(e -> {
			try {
				searchCanadaButtonClicked(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		selIndia.setOnAction(e -> {
			try {
				searchIndiaButtonClicked(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		selUS.setOnAction(e -> {
			try {
				searchUnitedStatesButtonClicked(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		selUK.setOnAction(e -> {
			try {
				searchUnitedKingdomButtonClicked(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		selCalifornia.setOnAction(e -> {
			try {
				searchCaliforniaButtonClicked(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		selIllinois.setOnAction(e -> {
			try {
				searchIllinoisButtonClicked(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		selNevada.setOnAction(e -> {
			try {
				searchNevadaButtonClicked(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		selNewYork.setOnAction(e -> {
			try {
				searchNewYorkButtonClicked(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		selBeijing.setOnAction(e -> {
			try {
				searchBeijingButtonClicked(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		selChicago.setOnAction(e -> {
			try {
				searchChicagoButtonClicked(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		selLasVegas.setOnAction(e -> {
			try {
				searchLasVegasButtonClicked(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		selLondon.setOnAction(e -> {
			try {
				searchLondonButtonClicked(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		selMontreal.setOnAction(e -> {
			try {
				searchMontrealButtonClicked(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		selNewDelhi.setOnAction(e -> {
			try {
				searchNewDelhiButtonClicked(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		selNewYorkCity.setOnAction(e -> {
			try {
				searchNewYorkCityButtonClicked(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		selSanFrancisco.setOnAction(e -> {
			try {
				searchSanFranciscoButtonClicked(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		selShanghai.setOnAction(e -> {
			try {
				searchShanghaiButtonClicked(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		showRatingButton.setOnAction(e -> {
			try {
				showRatingButtonClicked(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		advancedSearchButton.setOnAction(e -> {
			try {
				advancedSearchButtonClicked(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	}


	//clears the display list before re-populating
	public void clearDisplayList() {
		displayList.getItems().clear();
	}

	//Typed Input Search
	@FXML
	public void searchButtonClicked(ActionEvent event) throws IOException {
			String searchText = searchField.getText();
			String boundText = '%'+searchText+'%';
			try {
				conn = Connection.getConnection();
				ps = conn.prepareStatement(SEARCH_SQL);
				ps.setString(1, boundText);
				ResultSet rs = ps.executeQuery();
				clearDisplayList();
				while(rs.next()) {
					listItems.addAll(rs.getString("hotelName"));
				}
				displayList.setItems(listItems);
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
			    try { ps.close(); } catch (Exception e) { /* ignored */ }
			    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
	}

	//Search by Country Buttons
	@FXML
	public void searchChinaButtonClicked(ActionEvent event) throws IOException {
			country = "China";
			try {
				conn = Connection.getConnection();
				ps = conn.prepareStatement(SEARCH_SQL_COUNTRY);
				ps.setString(1, country);
				ResultSet rs = ps.executeQuery();
				clearDisplayList();
				while(rs.next()) {
					listItems.addAll(rs.getString("hotelName"));
				}
				displayList.setItems(listItems);
				//display city option and hide cities that are not in china
				selState.setVisible(false);
				selCity.setVisible(true);
				selBeijing.setVisible(true);
				selChicago.setVisible(false);
				selLasVegas.setVisible(false);
				selLondon.setVisible(false);
				selMontreal.setVisible(false);
				selNewDelhi.setVisible(false);
				selNewYorkCity.setVisible(false);
				selSanFrancisco.setVisible(false);
				selShanghai.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
			    try { ps.close(); } catch (Exception e) { /* ignored */ }
			    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
	}

	@FXML
	public void searchCanadaButtonClicked(ActionEvent event) throws IOException {
			country = "CA";
			try {
				conn = Connection.getConnection();
				ps = conn.prepareStatement(SEARCH_SQL_COUNTRY);
				ps.setString(1, country);
				ResultSet rs = ps.executeQuery();
				clearDisplayList();
				while(rs.next()) {
					listItems.addAll(rs.getString("hotelName"));
				}
				displayList.setItems(listItems);
				//display city option and hide cities that are not in country
				selState.setVisible(false);
				selCity.setVisible(true);
				selBeijing.setVisible(false);
				selChicago.setVisible(false);
				selLasVegas.setVisible(false);
				selLondon.setVisible(false);
				selMontreal.setVisible(true);
				selNewDelhi.setVisible(false);
				selNewYorkCity.setVisible(false);
				selSanFrancisco.setVisible(false);
				selShanghai.setVisible(false);
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
			    try { ps.close(); } catch (Exception e) { /* ignored */ }
			    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
	}
	@FXML
	public void searchIndiaButtonClicked(ActionEvent event) throws IOException {
		country = "India";
			try {
				conn = Connection.getConnection();
				ps = conn.prepareStatement(SEARCH_SQL_COUNTRY);
				ps.setString(1, country);
				ResultSet rs = ps.executeQuery();
				clearDisplayList();
				while(rs.next()) {
					listItems.addAll(rs.getString("hotelName"));
				}
				displayList.setItems(listItems);
				//display city option and hide cities that are not in country
				selState.setVisible(false);
				selCity.setVisible(true);
				selChicago.setVisible(false);
				selLasVegas.setVisible(false);
				selLondon.setVisible(false);
				selMontreal.setVisible(false);
				selNewDelhi.setVisible(true);
				selNewYorkCity.setVisible(false);
				selSanFrancisco.setVisible(false);
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
			    try { ps.close(); } catch (Exception e) { /* ignored */ }
			    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
	}
	@FXML
	public void searchUnitedStatesButtonClicked(ActionEvent event) throws IOException {
			country = "US";
			try {
				conn = Connection.getConnection();
				ps = conn.prepareStatement(SEARCH_SQL_COUNTRY);
				ps.setString(1, country);
				ResultSet rs = ps.executeQuery();
				clearDisplayList();
				while(rs.next()) {
					listItems.addAll(rs.getString("hotelName"));
				}
				displayList.setItems(listItems);
				//display state option and hide states that are not in country
				selCity.setVisible(false);
				selState.setVisible(true);
				selCalifornia.setVisible(true);
				selIllinois.setVisible(true);
				selNevada.setVisible(true);
				selNewYork.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
			    try { ps.close(); } catch (Exception e) { /* ignored */ }
			    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
	}
	@FXML
	public void searchUnitedKingdomButtonClicked(ActionEvent event) throws IOException {
			country = "UK";
			try {
				conn = Connection.getConnection();
				ps = conn.prepareStatement(SEARCH_SQL_COUNTRY);
				ps.setString(1, country);
				ResultSet rs = ps.executeQuery();
				clearDisplayList();
				while(rs.next()) {
					listItems.addAll(rs.getString("hotelName"));
				}
				displayList.setItems(listItems);
				//display city option and hide cities that are not in country
				selState.setVisible(false);
				selCity.setVisible(true);
				selBeijing.setVisible(false);
				selChicago.setVisible(false);
				selLasVegas.setVisible(false);
				selLondon.setVisible(true);
				selMontreal.setVisible(false);
				selNewDelhi.setVisible(false);
				selNewYorkCity.setVisible(false);
				selSanFrancisco.setVisible(false);
				selShanghai.setVisible(false);
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
			    try { ps.close(); } catch (Exception e) { /* ignored */ }
			    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
	}

	//Search by State Buttons
	@FXML
	public void searchCaliforniaButtonClicked(ActionEvent event) throws IOException {
			state = "California";
			try {
				conn = Connection.getConnection();
				ps = conn.prepareStatement(SEARCH_SQL_STATE);
				ps.setString(1, state);
				ResultSet rs = ps.executeQuery();
				clearDisplayList();
				while(rs.next()) {
					listItems.addAll(rs.getString("hotelName"));
				}
				displayList.setItems(listItems);
				//display state option and hide states that are not in country
				//state
				selState.setVisible(true);
				selCalifornia.setVisible(true);
				selIllinois.setVisible(true);
				selNevada.setVisible(true);
				selNewYork.setVisible(true);
				//city
				selCity.setVisible(true);
				selBeijing.setVisible(false);
				selChicago.setVisible(false);
				selLasVegas.setVisible(false);
				selLondon.setVisible(false);
				selMontreal.setVisible(false);
				selNewDelhi.setVisible(false);
				selNewYorkCity.setVisible(false);
				selSanFrancisco.setVisible(true);
				selShanghai.setVisible(false);
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
			    try { ps.close(); } catch (Exception e) { /* ignored */ }
			    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
	}
	@FXML
	public void searchIllinoisButtonClicked(ActionEvent event) throws IOException {
		state = "Illinois";
			try {
				conn = Connection.getConnection();
				ps = conn.prepareStatement(SEARCH_SQL_STATE);
				ps.setString(1, state);
				ResultSet rs = ps.executeQuery();
				clearDisplayList();
				while(rs.next()) {
					listItems.addAll(rs.getString("hotelName"));
				}
				displayList.setItems(listItems);
				//display state option and hide states that are not in country
				//state
				selState.setVisible(true);
				selCalifornia.setVisible(true);
				selIllinois.setVisible(true);
				selNevada.setVisible(true);
				selNewYork.setVisible(true);
				//city
				selCity.setVisible(true);
				selBeijing.setVisible(false);
				selChicago.setVisible(true);
				selLasVegas.setVisible(false);
				selLondon.setVisible(false);
				selMontreal.setVisible(false);
				selNewDelhi.setVisible(false);
				selNewYorkCity.setVisible(false);
				selSanFrancisco.setVisible(false);
				selShanghai.setVisible(false);
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
			    try { ps.close(); } catch (Exception e) { /* ignored */ }
			    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
	}
	@FXML
	public void searchNevadaButtonClicked(ActionEvent event) throws IOException {
		state = "Nevada";
			try {
				conn = Connection.getConnection();
				ps = conn.prepareStatement(SEARCH_SQL_STATE);
				ps.setString(1, state);
				ResultSet rs = ps.executeQuery();
				clearDisplayList();
				while(rs.next()) {
					listItems.addAll(rs.getString("hotelName"));
				}
				displayList.setItems(listItems);
				//display state option and hide states that are not in country
				//state
				selState.setVisible(true);
				selCalifornia.setVisible(true);
				selIllinois.setVisible(true);
				selNevada.setVisible(true);
				selNewYork.setVisible(true);
				//city
				selCity.setVisible(true);
				selBeijing.setVisible(false);
				selChicago.setVisible(false);
				selLasVegas.setVisible(true);
				selLondon.setVisible(false);
				selMontreal.setVisible(false);
				selNewDelhi.setVisible(false);
				selNewYorkCity.setVisible(false);
				selSanFrancisco.setVisible(false);
				selShanghai.setVisible(false);
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
			    try { ps.close(); } catch (Exception e) { /* ignored */ }
			    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
	}
	@FXML
	public void searchNewYorkButtonClicked(ActionEvent event) throws IOException {
		state = "New York";
			try {
				conn = Connection.getConnection();
				ps = conn.prepareStatement(SEARCH_SQL_STATE);
				ps.setString(1, state);
				ResultSet rs = ps.executeQuery();
				clearDisplayList();
				while(rs.next()) {
					listItems.addAll(rs.getString("hotelName"));
				}
				displayList.setItems(listItems);
				//display state option and hide states that are not in country
				//state
				selState.setVisible(true);
				selCalifornia.setVisible(true);
				selIllinois.setVisible(true);
				selNevada.setVisible(true);
				selNewYork.setVisible(true);
				//city
				selCity.setVisible(true);
				selBeijing.setVisible(false);
				selChicago.setVisible(false);
				selLasVegas.setVisible(false);
				selLondon.setVisible(false);
				selMontreal.setVisible(false);
				selNewDelhi.setVisible(false);
				selNewYorkCity.setVisible(true);
				selSanFrancisco.setVisible(false);
				selShanghai.setVisible(false);
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
			    try { ps.close(); } catch (Exception e) { /* ignored */ }
			    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
	}

	//Search by City Buttons
	@FXML
	public void searchBeijingButtonClicked(ActionEvent event) throws IOException {
			city = "Beijing";
			try {
				conn = Connection.getConnection();
				ps = conn.prepareStatement(SEARCH_SQL_CITY);
				ps.setString(1, city);
				ResultSet rs = ps.executeQuery();
				clearDisplayList();
				while(rs.next()) {
					listItems.addAll(rs.getString("hotelName"));
				}
				displayList.setItems(listItems);
				//display state option and hide states that are not in country
				//state
				selState.setVisible(false);
				//city
				selCity.setVisible(true);
				selBeijing.setVisible(true);
				selChicago.setVisible(false);
				selLasVegas.setVisible(false);
				selLondon.setVisible(false);
				selMontreal.setVisible(false);
				selNewDelhi.setVisible(false);
				selNewYorkCity.setVisible(false);
				selSanFrancisco.setVisible(false);
				selShanghai.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
			    try { ps.close(); } catch (Exception e) { /* ignored */ }
			    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
	}
	@FXML
	public void searchChicagoButtonClicked(ActionEvent event) throws IOException {
		city = "Chicago";
			try {
				conn = Connection.getConnection();
				ps = conn.prepareStatement(SEARCH_SQL_CITY);
				ps.setString(1, city);
				ResultSet rs = ps.executeQuery();
				clearDisplayList();
				while(rs.next()) {
					listItems.addAll(rs.getString("hotelName"));
				}
				displayList.setItems(listItems);
				//display state option and hide states that are not in country
				//state
				selState.setVisible(true);
				selCalifornia.setVisible(true);
				selIllinois.setVisible(true);
				selNevada.setVisible(true);
				selNewYork.setVisible(true);
				//city
				selCity.setVisible(true);
				selBeijing.setVisible(false);
				selChicago.setVisible(true);
				selLasVegas.setVisible(false);
				selLondon.setVisible(false);
				selMontreal.setVisible(false);
				selNewDelhi.setVisible(false);
				selNewYorkCity.setVisible(false);
				selSanFrancisco.setVisible(false);
				selShanghai.setVisible(false);
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
			    try { ps.close(); } catch (Exception e) { /* ignored */ }
			    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
	}
	@FXML
	public void searchLasVegasButtonClicked(ActionEvent event) throws IOException {
		city = "Las Vegas";
			try {
				conn = Connection.getConnection();
				ps = conn.prepareStatement(SEARCH_SQL_CITY);
				ps.setString(1, city);
				ResultSet rs = ps.executeQuery();
				clearDisplayList();
				while(rs.next()) {
					listItems.addAll(rs.getString("hotelName"));
				}
				displayList.setItems(listItems);
				//display state option and hide states that are not in country
				//state
				selState.setVisible(true);
				selCalifornia.setVisible(true);
				selIllinois.setVisible(true);
				selNevada.setVisible(true);
				selNewYork.setVisible(true);
				//city
				selCity.setVisible(true);
				selBeijing.setVisible(false);
				selChicago.setVisible(false);
				selLasVegas.setVisible(true);
				selLondon.setVisible(false);
				selMontreal.setVisible(false);
				selNewDelhi.setVisible(false);
				selNewYorkCity.setVisible(false);
				selSanFrancisco.setVisible(false);
				selShanghai.setVisible(false);
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
			    try { ps.close(); } catch (Exception e) { /* ignored */ }
			    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
	}
	@FXML
	public void searchLondonButtonClicked(ActionEvent event) throws IOException {
		city = "London";
			try {
				conn = Connection.getConnection();
				ps = conn.prepareStatement(SEARCH_SQL_CITY);
				ps.setString(1, city);
				ResultSet rs = ps.executeQuery();
				clearDisplayList();
				while(rs.next()) {
					listItems.addAll(rs.getString("hotelName"));
				}
				displayList.setItems(listItems);
				//display state option and hide states that are not in country
				//state
				selState.setVisible(false);
				//city
				selCity.setVisible(true);
				selBeijing.setVisible(false);
				selChicago.setVisible(false);
				selLasVegas.setVisible(false);
				selLondon.setVisible(true);
				selMontreal.setVisible(false);
				selNewDelhi.setVisible(false);
				selNewYorkCity.setVisible(false);
				selSanFrancisco.setVisible(false);
				selShanghai.setVisible(false);
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
			    try { ps.close(); } catch (Exception e) { /* ignored */ }
			    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
	}
	@FXML
	public void searchMontrealButtonClicked(ActionEvent event) throws IOException {
		city = "Montreal";
			try {
				conn = Connection.getConnection();
				ps = conn.prepareStatement(SEARCH_SQL_CITY);
				ps.setString(1, city);
				ResultSet rs = ps.executeQuery();
				clearDisplayList();
				while(rs.next()) {
					listItems.addAll(rs.getString("hotelName"));
				}
				displayList.setItems(listItems);
				//display state option and hide states that are not in country
				//state
				selState.setVisible(false);
				//city
				selCity.setVisible(true);
				selBeijing.setVisible(false);
				selChicago.setVisible(false);
				selLasVegas.setVisible(false);
				selLondon.setVisible(false);
				selMontreal.setVisible(true);
				selNewDelhi.setVisible(false);
				selNewYorkCity.setVisible(false);
				selSanFrancisco.setVisible(false);
				selShanghai.setVisible(false);
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
			    try { ps.close(); } catch (Exception e) { /* ignored */ }
			    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
	}
	@FXML
	public void searchNewDelhiButtonClicked(ActionEvent event) throws IOException {
		city = "New Delhi";
			try {
				conn = Connection.getConnection();
				ps = conn.prepareStatement(SEARCH_SQL_CITY);
				ps.setString(1, city);
				ResultSet rs = ps.executeQuery();
				clearDisplayList();
				while(rs.next()) {
					listItems.addAll(rs.getString("hotelName"));
				}
				displayList.setItems(listItems);
				//display state option and hide states that are not in country
				//state
				selState.setVisible(false);
				//city
				selCity.setVisible(true);
				selBeijing.setVisible(false);
				selChicago.setVisible(false);
				selLasVegas.setVisible(false);
				selLondon.setVisible(false);
				selMontreal.setVisible(false);
				selNewDelhi.setVisible(true);
				selNewYorkCity.setVisible(false);
				selSanFrancisco.setVisible(false);
				selShanghai.setVisible(false);
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
			    try { ps.close(); } catch (Exception e) { /* ignored */ }
			    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
	}
	@FXML
	public void searchNewYorkCityButtonClicked(ActionEvent event) throws IOException {
		city = "New York City";
			try {
				conn = Connection.getConnection();
				ps = conn.prepareStatement(SEARCH_SQL_CITY);
				ps.setString(1, city);
				ResultSet rs = ps.executeQuery();
				clearDisplayList();
				while(rs.next()) {
					listItems.addAll(rs.getString("hotelName"));
				}
				displayList.setItems(listItems);
				//display state option and hide states that are not in country
				//state
				selState.setVisible(true);
				selCalifornia.setVisible(true);
				selIllinois.setVisible(true);
				selNevada.setVisible(true);
				selNewYork.setVisible(true);
				//city
				selCity.setVisible(true);
				selBeijing.setVisible(false);
				selChicago.setVisible(false);
				selLasVegas.setVisible(false);
				selLondon.setVisible(false);
				selMontreal.setVisible(false);
				selNewDelhi.setVisible(false);
				selNewYorkCity.setVisible(true);
				selSanFrancisco.setVisible(false);
				selShanghai.setVisible(false);
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
			    try { ps.close(); } catch (Exception e) { /* ignored */ }
			    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
	}
	@FXML
	public void searchSanFranciscoButtonClicked(ActionEvent event) throws IOException {
		city = "San Francisco";
			try {
				conn = Connection.getConnection();
				ps = conn.prepareStatement(SEARCH_SQL_CITY);
				ps.setString(1, city);
				ResultSet rs = ps.executeQuery();
				clearDisplayList();
				while(rs.next()) {
					listItems.addAll(rs.getString("hotelName"));
				}
				displayList.setItems(listItems);
				//display state option and hide states that are not in country
				//state
				selState.setVisible(true);
				selCalifornia.setVisible(true);
				selIllinois.setVisible(true);
				selNevada.setVisible(true);
				selNewYork.setVisible(true);
				//city
				selCity.setVisible(true);
				selBeijing.setVisible(false);
				selChicago.setVisible(false);
				selLasVegas.setVisible(false);
				selLondon.setVisible(false);
				selMontreal.setVisible(false);
				selNewDelhi.setVisible(false);
				selNewYorkCity.setVisible(false);
				selSanFrancisco.setVisible(true);
				selShanghai.setVisible(false);
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
			    try { ps.close(); } catch (Exception e) { /* ignored */ }
			    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
	}
	@FXML
	public void searchShanghaiButtonClicked(ActionEvent event) throws IOException {
			city = "Shanghai";
			try {
				conn = Connection.getConnection();
				ps = conn.prepareStatement(SEARCH_SQL_CITY);
				ps.setString(1, city);
				ResultSet rs = ps.executeQuery();
				clearDisplayList();
				String line;
				while(rs.next()) {
					line = rs.getString("hotelName")+" - "+rs.getString("city");
					listItems.addAll(line);
				}
				displayList.setItems(listItems);
				//display state option and hide states that are not in country
				//state
				selState.setVisible(false);
				//city
				selCity.setVisible(true);
				selBeijing.setVisible(true);
				selChicago.setVisible(false);
				selLasVegas.setVisible(false);
				selLondon.setVisible(false);
				selMontreal.setVisible(false);
				selNewDelhi.setVisible(false);
				selNewYorkCity.setVisible(false);
				selSanFrancisco.setVisible(false);
				selShanghai.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
			    try { ps.close(); } catch (Exception e) { /* ignored */ }
			    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
	}

	//Queries database to obtain each rating then sets labels to those values
	@FXML
	public void showRatingButtonClicked(ActionEvent event) throws IOException {
		String hotelName = displayList.getSelectionModel().getSelectedItem();
		int hotelID = getHotelID(hotelName);
		int overAllrating = getOverAll(hotelID);
		int priceRating = getPrice(hotelID);
		int cleanRating = getClean(hotelID);
		int locRating = getLoc(hotelID);
		int amenRating = getAmen(hotelID);
		int servRating = getServ(hotelID);
		int roomRating = getRoom(hotelID);

		allLabel.setText(Integer.toString(overAllrating));
		priceLabel.setText(Integer.toString(priceRating));
		cleanLabel.setText(Integer.toString(cleanRating));
		locLabel.setText(Integer.toString(locRating));
		amenLabel.setText(Integer.toString(amenRating));
		servLabel.setText(Integer.toString(servRating));
		roomLabel.setText(Integer.toString(roomRating));
	}

	public int getHotelID(String hotelName) {
		int hotelID = 0;
		try {
			conn = Connection.getConnection();
			ps = conn.prepareStatement(SEARCH_HOTELID);
			ps.setString(1, hotelName);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				hotelID = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		    try { ps.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}

		return hotelID;
	}

	public int getOverAll(int ID) {
		int rating = 0;
		try {
			conn = Connection.getConnection();
			ps = conn.prepareStatement(SEARCH_OVERALL);
			ps.setInt(1, ID);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				rating = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		    try { ps.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
		return rating;
	}

	public int getPrice(int ID) {
		int rating = 0;
		try {
			conn = Connection.getConnection();
			ps = conn.prepareStatement(SEARCH_PRICE);
			ps.setInt(1, ID);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				rating = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		    try { ps.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
		return rating;
	}

	public int getClean(int ID) {
		int rating = 0;
		try {
			conn = Connection.getConnection();
			ps = conn.prepareStatement(SEARCH_CLEAN);
			ps.setInt(1, ID);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				rating = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		    try { ps.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
		return rating;
	}

	public int getLoc(int ID) {
		int rating = 0;
		try {
			conn = Connection.getConnection();
			ps = conn.prepareStatement(SEARCH_LOC);
			ps.setInt(1, ID);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				rating = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		    try { ps.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
		return rating;
	}

	public int getAmen(int ID) {
		int rating = 0;
		try {
			conn = Connection.getConnection();
			ps = conn.prepareStatement(SEARCH_AMEN);
			ps.setInt(1, ID);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				rating = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		    try { ps.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
		return rating;
	}

	public int getServ(int ID) {
		int rating = 0;
		try {
			conn = Connection.getConnection();
			ps = conn.prepareStatement(SEARCH_SERV);
			ps.setInt(1, ID);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				rating = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		    try { ps.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
		return rating;
	}

	public int getRoom(int ID) {
		int rating = 0;
		try {
			conn = Connection.getConnection();
			ps = conn.prepareStatement(SEARCH_ROOM);
			ps.setInt(1, ID);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				rating = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		    try { ps.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
		return rating;
	}

	@FXML
	public void advancedSearchButtonClicked(ActionEvent event) throws IOException {
		Parent aSearchViewParent = FXMLLoader.load(getClass().getResource("advanced_search_hotel.fxml"));
		Scene aSearchViewScene = new Scene(aSearchViewParent);
		Stage aSearchWindow = Main.primaryStage;
		aSearchWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
		aSearchWindow.setScene(aSearchViewScene);
		aSearchWindow.show();
	}

	@FXML
	public void exitProgram() {
		exitButton.setOnAction(e -> System.exit(0));
	}

}
