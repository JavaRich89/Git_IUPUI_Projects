import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;

public class AdvanceSearchController {

	//search
	@FXML
	Button searchButton;
	@FXML
	MenuItem exitButton;

	//listView
	@FXML
	private ListView<String> advancedDisplayList;
	@FXML
	private ObservableList<String> advancedListItems = FXCollections.observableArrayList();

	//City
	@FXML
	private Label selCity;
	@FXML
	private MenuItem selCityBeijing;
	@FXML
	private MenuItem selCityChicago;
	@FXML
	private MenuItem selCityLasVegas;
	@FXML
	private MenuItem selCityLondon;
	@FXML
	private MenuItem selCityMontreal;
	@FXML
	private MenuItem selCityNewDelhi;
	@FXML
	private MenuItem selCityNewYorkCity;
	@FXML
	private MenuItem selCitySanFrancisco;
	@FXML
	private MenuItem selCityShanghai;

	//Number value buttons
	//Overall
	@FXML
	private Label selOverall;
	@FXML
	private MenuItem selOverall5;
	@FXML
	private MenuItem selOverallG45;
	@FXML
	private MenuItem selOverallG4;
	@FXML
	private MenuItem selOverallG35;
	@FXML
	private MenuItem selOverallG3;
	@FXML
	private MenuItem selOverallG25;
	@FXML
	private MenuItem selOverallG2;
	@FXML
	private MenuItem selOverallL2;
	@FXML
	private MenuItem selOverall0;

	//Price
	@FXML
	private Label selPrice;
	@FXML
	private MenuItem selPrice5;
	@FXML
	private MenuItem selPriceG45;
	@FXML
	private MenuItem selPriceG4;
	@FXML
	private MenuItem selPriceG35;
	@FXML
	private MenuItem selPriceG3;
	@FXML
	private MenuItem selPriceG25;
	@FXML
	private MenuItem selPriceG2;
	@FXML
	private MenuItem selPriceL2;
	@FXML
	private MenuItem selPrice0;

	//Cleanliness
	@FXML
	private Label selClean;
	@FXML
	private MenuItem selClean5;
	@FXML
	private MenuItem selCleanG45;
	@FXML
	private MenuItem selCleanG4;
	@FXML
	private MenuItem selCleanG35;
	@FXML
	private MenuItem selCleanG3;
	@FXML
	private MenuItem selCleanG25;
	@FXML
	private MenuItem selCleanG2;
	@FXML
	private MenuItem selCleanL2;
	@FXML
	private MenuItem selClean0;

	//Location
	@FXML
	private Label selLoc;
	@FXML
	private MenuItem selLoc5;
	@FXML
	private MenuItem selLocG45;
	@FXML
	private MenuItem selLocG4;
	@FXML
	private MenuItem selLocG35;
	@FXML
	private MenuItem selLocG3;
	@FXML
	private MenuItem selLocG25;
	@FXML
	private MenuItem selLocG2;
	@FXML
	private MenuItem selLocL2;
	@FXML
	private MenuItem selLoc0;

	//Amenities
	@FXML
	private Label selAmen;
	@FXML
	private MenuItem selAmen5;
	@FXML
	private MenuItem selAmenG45;
	@FXML
	private MenuItem selAmenG4;
	@FXML
	private MenuItem selAmenG35;
	@FXML
	private MenuItem selAmenG3;
	@FXML
	private MenuItem selAmenG25;
	@FXML
	private MenuItem selAmenG2;
	@FXML
	private MenuItem selAmenL2;
	@FXML
	private MenuItem selAmen0;

	//Service
	@FXML
	private Label selServ;
	@FXML
	private MenuItem selServ5;
	@FXML
	private MenuItem selServG45;
	@FXML
	private MenuItem selServG4;
	@FXML
	private MenuItem selServG35;
	@FXML
	private MenuItem selServG3;
	@FXML
	private MenuItem selServG25;
	@FXML
	private MenuItem selServG2;
	@FXML
	private MenuItem selServL2;
	@FXML
	private MenuItem selServ0;

	//Room
	@FXML
	private Label selRoom;
	@FXML
	private MenuItem selRoom5;
	@FXML
	private MenuItem selRoomG45;
	@FXML
	private MenuItem selRoomG4;
	@FXML
	private MenuItem selRoomG35;
	@FXML
	private MenuItem selRoomG3;
	@FXML
	private MenuItem selRoomG25;
	@FXML
	private MenuItem selRoomG2;
	@FXML
	private MenuItem selRoomL2;
	@FXML
	private MenuItem selRoom0;

	//JDBC Variables
	java.sql.Connection conn = null;
	PreparedStatement ps = null;

	//SQL Input Variables
	private static String overAllCompare;
	private static String priceCompare;
	private static String cleanCompare;
	private static String locCompare;
	private static String amenCompare;
	private static String servCompare;
	private static String roomCompare;

	private static String city;
	private static double overAll = 0;
	private static double price = 0;
	private static double cleanliness = 0;
	private static double location = 0;
	private static double amenities = 0;
	private static double service = 0;
	private static double room = 0;

	//SQL Statements
	private static final String SEARCH_BASE = "SELECT hotelName FROM AVG_RATING WHERE city = ?";
	//SQL Conditional statements
	private static final String SEARCH_OVERALL = " AND overall > ?";
	private static final String SEARCH_OVERALL_5 = " AND overall = ?";
	private static final String SEARCH_OVERALL_L2 = " AND overall < ?";
	private static final String SEARCH_PRICE = " AND price > ?";
	private static final String SEARCH_PRICE_5 = " AND price = ?";
	private static final String SEARCH_PRICE_L2 = " AND price < ?";
	private static final String SEARCH_CLEAN = " AND cleanliness > ?";
	private static final String SEARCH_CLEAN_5 = " AND cleanliness = ?";
	private static final String SEARCH_CLEAN_L2 = " AND cleanliness < ?";
	private static final String SEARCH_LOC = " AND location > ?";
	private static final String SEARCH_LOC_5 = " AND location = ?";
	private static final String SEARCH_LOC_L2 = " AND location < ?";
	private static final String SEARCH_AMEN = " AND amenitites > ?";
	private static final String SEARCH_AMEN_5 = " AND amenities = ?";
	private static final String SEARCH_AMEN_L2 = " AND amenities < ?";
	private static final String SEARCH_SERV = " AND service > ?";
	private static final String SEARCH_SERV_5 = " AND service = ?";
	private static final String SEARCH_SERV_L2 = " AND service < ?";
	private static final String SEARCH_ROOM = " AND room > ?";
	private static final String SEARCH_ROOM_5 = " AND room = ?";
	private static final String SEARCH_ROOM_L2 = " AND room < ?";



	public void initialize(URL location, ResourceBundle resources) {
		searchButton.setOnAction(e -> {
			try {
				searchButtonClicked(e);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		});
		selCityBeijing.setOnAction(e -> {
			setCityBeijing(e);
		});
		selCityChicago.setOnAction(e -> {
			setCityChicago(e);
		});
		selCityLasVegas.setOnAction(e -> {
			setCityLasVegas(e);
		});
		selCityLondon.setOnAction(e -> {
			setCityLondon(e);
		});
		selCityMontreal.setOnAction(e -> {
			setCityMontreal(e);
		});
		selCityNewDelhi.setOnAction(e -> {
			setCityNewDelhi(e);
		});
		selCityNewYorkCity.setOnAction(e -> {
			setCityNewYorkCity(e);
		});
		selCitySanFrancisco.setOnAction(e -> {
			setCitySanFrancisco(e);
		});
		selCityShanghai.setOnAction(e -> {
			setCityShanghai(e);
		});
		selOverall5.setOnAction(e -> {
			setOverall5(e);
		});
		selOverallG45.setOnAction(e -> {
			setOverallG45(e);
		});
		selOverallG4.setOnAction(e -> {
			setOverallG4(e);
		});
		selOverallG35.setOnAction(e -> {
			setOverallG35(e);
		});
		selOverallG3.setOnAction(e -> {
			setOverallG3(e);
		});
		selOverallG25.setOnAction(e -> {
			setOverallG25(e);
		});
		selOverallG2.setOnAction(e -> {
			setOverallG2(e);
		});
		selOverallL2.setOnAction(e -> {
			setOverallL2(e);
		});
		selOverall0.setOnAction(e -> {
			setOverall0(e);
		});
		selPrice5.setOnAction(e -> {
			setPrice5(e);
		});
		selPriceG45.setOnAction(e -> {
			setPriceG45(e);
		});
		selPriceG4.setOnAction(e -> {
			setPriceG4(e);
		});
		selPriceG35.setOnAction(e -> {
			setPriceG35(e);
		});
		selPriceG3.setOnAction(e -> {
			setPriceG3(e);
		});
		selPriceG25.setOnAction(e -> {
			setPriceG25(e);
		});
		selPriceG2.setOnAction(e -> {
			setPriceG2(e);
		});
		selPriceL2.setOnAction(e -> {
			setPriceL2(e);
		});
		selPrice0.setOnAction(e -> {
			setPrice0(e);
		});
		selClean5.setOnAction(e -> {
			setClean5(e);
		});
		selCleanG45.setOnAction(e -> {
			setCleanG45(e);
		});
		selCleanG4.setOnAction(e -> {
			setCleanG4(e);
		});
		selCleanG35.setOnAction(e -> {
			setCleanG35(e);
		});
		selCleanG3.setOnAction(e -> {
			setCleanG3(e);
		});
		selCleanG25.setOnAction(e -> {
			setCleanG25(e);
		});
		selCleanG2.setOnAction(e -> {
			setCleanG2(e);
		});
		selCleanL2.setOnAction(e -> {
			setCleanL2(e);
		});
		selClean0.setOnAction(e -> {
			setClean0(e);
		});
		selLoc5.setOnAction(e -> {
			setLoc5(e);
		});
		selLocG45.setOnAction(e -> {
			setLocG45(e);
		});
		selLocG4.setOnAction(e -> {
			setLocG4(e);
		});
		selLocG35.setOnAction(e -> {
			setLocG35(e);
		});
		selLocG3.setOnAction(e -> {
			setLocG3(e);
		});
		selLocG25.setOnAction(e -> {
			setLocG25(e);
		});
		selLocG2.setOnAction(e -> {
			setLocG2(e);
		});
		selLocL2.setOnAction(e -> {
			setLocL2(e);
		});
		selLoc0.setOnAction(e -> {
			setLoc0(e);
		});
		selAmen5.setOnAction(e -> {
			setAmen5(e);
		});
		selAmenG45.setOnAction(e -> {
			setAmenG45(e);
		});
		selAmenG4.setOnAction(e -> {
			setAmenG4(e);
		});
		selAmenG35.setOnAction(e -> {
			setAmenG35(e);
		});
		selAmenG3.setOnAction(e -> {
			setAmenG3(e);
		});
		selAmenG25.setOnAction(e -> {
			setAmenG25(e);
		});
		selAmenG2.setOnAction(e -> {
			setAmenG2(e);
		});
		selAmenL2.setOnAction(e -> {
			setAmenL2(e);
		});
		selAmen0.setOnAction(e -> {
			setAmen0(e);
		});
		selServ5.setOnAction(e -> {
			setServ5(e);
		});
		selServG45.setOnAction(e -> {
			setServG45(e);
		});
		selServG4.setOnAction(e -> {
			setServG4(e);
		});
		selServG35.setOnAction(e -> {
			setServG35(e);
		});
		selServG3.setOnAction(e -> {
			setServG3(e);
		});
		selServG25.setOnAction(e -> {
			setServG25(e);
		});
		selServG2.setOnAction(e -> {
			setServG2(e);
		});
		selServL2.setOnAction(e -> {
			setServL2(e);
		});
		selServ0.setOnAction(e -> {
			setServ0(e);
		});
		selRoom5.setOnAction(e -> {
			setRoom5(e);
		});
		selRoomG45.setOnAction(e -> {
			setRoomG45(e);
		});
		selRoomG4.setOnAction(e -> {
			setRoomG4(e);
		});
		selRoomG35.setOnAction(e -> {
			setRoomG35(e);
		});
		selRoomG3.setOnAction(e -> {
			setRoomG3(e);
		});
		selRoomG25.setOnAction(e -> {
			setRoomG25(e);
		});
		selRoomG2.setOnAction(e -> {
			setRoomG2(e);
		});
		selRoomL2.setOnAction(e -> {
			setRoomL2(e);
		});
		selRoom0.setOnAction(e -> {
			setRoom0(e);
		});

	}

	public void setRoom0(ActionEvent e) {
		selRoom.setText(" ");
		room = 0;
	}

	public void setServ0(ActionEvent e) {
		selServ.setText(" ");
		service = 0;
	}

	public void setAmen0(ActionEvent e) {
		selAmen.setText(" ");
		amenities = 0;
	}

	public void setLoc0(ActionEvent e) {
		selLoc.setText(" ");
		location = 0;
	}

	public void setClean0(ActionEvent e) {
		selClean.setText(" ");
		cleanliness = 0;
	}

	public void setPrice0(ActionEvent e) {
		selPrice.setText(" ");
		price = 0;
	}

	public void setOverall0(ActionEvent e) {
		selOverall.setText(" ");
		overAll = 0;
	}

	public void setRoomL2(ActionEvent e) {
		selRoom.setText("< 2");
		roomCompare = "<";
		room = 2;
	}

	public void setRoomG2(ActionEvent e) {
		selRoom.setText("> 2");
		roomCompare = ">";
		room = 2;
	}

	public void setRoomG25(ActionEvent e) {
		selRoom.setText("> 2.5");
		roomCompare = ">";
		room = 2.5;
	}

	public void setRoomG3(ActionEvent e) {
		selRoom.setText("> 3");
		roomCompare = ">";
		room = 3;
	}

	public void setRoomG35(ActionEvent e) {
		selRoom.setText("> 3.5");
		roomCompare = ">";
		room = 3.5;
	}

	public void setRoomG4(ActionEvent e) {
		selRoom.setText("> 4");
		roomCompare = ">";
		room = 4;
	}

	public void setRoomG45(ActionEvent e) {
		selRoom.setText("> 4.5");
		roomCompare = ">";
		room = 4.5;
	}

	public void setRoom5(ActionEvent e) {
		selRoom.setText("= 5");
		roomCompare = "=";
		room = 5;
	}

	public void setServL2(ActionEvent e) {
		selServ.setText("< 2");
		servCompare = "<";
		service = 2;
	}

	public void setServG2(ActionEvent e) {
		selServ.setText("> 2");
		servCompare = ">";
		service = 2;
	}

	public void setServG25(ActionEvent e) {
		selServ.setText("> 2.5");
		servCompare = ">";
		service = 2.5;
	}

	public void setServG3(ActionEvent e) {
		selServ.setText("> 3");
		servCompare = ">";
		service = 3;
	}

	public void setServG35(ActionEvent e) {
		selServ.setText("> 3.5");
		servCompare = ">";
		service = 3.5;
	}

	public void setServG4(ActionEvent e) {
		selServ.setText("> 4");
		servCompare = ">";
		service = 4;
	}

	public void setServG45(ActionEvent e) {
		selServ.setText("> 4.5");
		servCompare = ">";
		service = 4.5;
	}

	public void setServ5(ActionEvent e) {
		selServ.setText("= 5");
		servCompare = "=";
		service = 5;
	}

	public void setAmenL2(ActionEvent e) {
		selAmen.setText("< 2");
		amenCompare = "<";
		amenities = 2;
	}

	public void setAmenG2(ActionEvent e) {
		selAmen.setText("> 2");
		amenCompare = ">";
		amenities = 2;
	}

	public void setAmenG25(ActionEvent e) {
		selAmen.setText("> 2.5");
		amenCompare = ">";
		amenities = 2.5;
	}

	public void setAmenG3(ActionEvent e) {
		selAmen.setText("> 3");
		amenCompare = ">";
		amenities = 3;
	}

	public void setAmenG35(ActionEvent e) {
		selAmen.setText("> 3.5");
		amenCompare = ">";
		amenities = 3.5;
	}

	public void setAmenG4(ActionEvent e) {
		selAmen.setText("> 4");
		amenCompare = ">";
		amenities = 4;
	}

	public void setAmenG45(ActionEvent e) {
		selAmen.setText("> 4.5");
		amenCompare = ">";
		amenities = 4.5;
	}

	public void setAmen5(ActionEvent e) {
		selAmen.setText("= 5");
		amenCompare = "=";
		amenities = 5;
	}

	public void setLocL2(ActionEvent e) {
		selLoc.setText("< 2");
		locCompare = "<";
		location = 2;
	}

	public void setLocG2(ActionEvent e) {
		selLoc.setText("> 2");
		locCompare = ">";
		location = 2;
	}

	public void setLocG25(ActionEvent e) {
		selLoc.setText("> 2.5");
		locCompare = ">";
		location = 2.5;
	}

	public void setLocG3(ActionEvent e) {
		selLoc.setText("> 3");
		locCompare = ">";
		location = 3;
	}

	public void setLocG35(ActionEvent e) {
		selLoc.setText("> 3.5");
		locCompare = ">";
		location = 3.5;
	}

	public void setLocG4(ActionEvent e) {
		selLoc.setText("> 4");
		locCompare = ">";
		location = 4;
	}

	public void setLocG45(ActionEvent e) {
		selLoc.setText("> 4.5");
		locCompare = ">";
		location = 4.5;
	}

	public void setLoc5(ActionEvent e) {
		selLoc.setText("= 5");
		locCompare = "=";
		location = 5;
	}

	public void setCleanL2(ActionEvent e) {
		selClean.setText("< 2");
		cleanCompare = "<";
		cleanliness = 2;
	}

	public void setCleanG2(ActionEvent e) {
		selClean.setText("> 2");
		cleanCompare = ">";
		cleanliness = 2;
	}

	public void setCleanG25(ActionEvent e) {
		selClean.setText("> 2.5");
		cleanCompare = ">";
		cleanliness = 2.5;
	}

	public void setCleanG3(ActionEvent e) {
		selClean.setText("> 3");
		cleanCompare = ">";
		cleanliness = 3;
	}

	public void setCleanG35(ActionEvent e) {
		selClean.setText("> 3.5");
		cleanCompare = ">";
		cleanliness = 3.5;
	}

	public void setCleanG4(ActionEvent e) {
		selClean.setText("> 4");
		cleanCompare = ">";
		cleanliness = 4;
	}

	public void setCleanG45(ActionEvent e) {
		selClean.setText("> 4.5");
		cleanCompare = ">";
		cleanliness = 4.5;
	}

	public void setClean5(ActionEvent e) {
		selClean.setText("= 5");
		cleanCompare = "=";
		cleanliness = 5;
	}

	public void setPriceL2(ActionEvent e) {
		selPrice.setText("< 2");
		priceCompare = "<";
		price = 2;
	}

	public void setPriceG2(ActionEvent e) {
		selPrice.setText("> 2");
		priceCompare = ">";
		price = 2;
	}

	public void setPriceG25(ActionEvent e) {
		selPrice.setText("> 2.5");
		priceCompare = ">";
		price = 2.5;
	}

	public void setPriceG3(ActionEvent e) {
		selPrice.setText("> 3");
		priceCompare = ">";
		price = 3;
	}

	public void setPriceG35(ActionEvent e) {
		selPrice.setText("> 3.5");
		priceCompare = ">";
		price = 3.5;
	}

	public void setPriceG4(ActionEvent e) {
		selPrice.setText("> 4");
		priceCompare = ">";
		price = 4;
	}

	public void setPriceG45(ActionEvent e) {
		selPrice.setText("> 4.5");
		priceCompare = ">";
		price = 4.5;
	}

	public void setPrice5(ActionEvent e) {
		selPrice.setText("= 5");
		priceCompare = "=";
		price = 5;
	}

	public void setOverallL2(ActionEvent e) {
		selOverall.setText("< 2");
		overAllCompare = "<";
		overAll = 2;
	}

	public void setOverallG2(ActionEvent e) {
		selOverall.setText("> 2");
		overAllCompare = ">";
		overAll = 2;
	}

	public void setOverallG25(ActionEvent e) {
		selOverall.setText("> 2.5");
		overAllCompare = ">";
		overAll = 2.5;
	}

	public void setOverallG3(ActionEvent e) {
		selOverall.setText("> 3");
		overAllCompare = ">";
		overAll = 3;
	}

	public void setOverallG35(ActionEvent e) {
		selOverall.setText("> 3.5");
		overAllCompare = ">";
		overAll = 3.5;
	}

	public void setOverallG4(ActionEvent e) {
		selOverall.setText("> 4");
		overAllCompare = ">";
		overAll = 4;
	}

	public void setOverallG45(ActionEvent e) {
		selOverall.setText("> 4.5");
		overAllCompare = ">";
		overAll = 4.5;
	}

	public void setOverall5(ActionEvent e) {
		selOverall.setText("= 5");
		overAllCompare = "=";
		overAll = 5;
	}

	public void setCityShanghai(ActionEvent e) {
		selCity.setText("Shanghai");
		city = "Shanghai";
	}

	public void setCitySanFrancisco(ActionEvent e) {
		selCity.setText("San Francisco");
		city = "San Francisco";
	}

	public void setCityNewYorkCity(ActionEvent e) {
		selCity.setText("New York City");
		city = "New York City";
	}

	public void setCityNewDelhi(ActionEvent e) {
		selCity.setText("New Delhi");
		city = "New delhi";
	}

	public void setCityMontreal(ActionEvent e) {
		selCity.setText("Montreal");
		city = "Montreal";
	}

	public void setCityLondon(ActionEvent e) {
		selCity.setText("London");
		city = "London";
	}

	public void setCityLasVegas(ActionEvent e) {
		selCity.setText("Las Vegas");
		city = "Las Vegas";
	}

	public void setCityChicago(ActionEvent e) {
		selCity.setText("Chicago");
		city = "Chicago";
	}

	public void setCityBeijing(ActionEvent e) {
		selCity.setText("Beijing");
		city = "Beijing";
	}

	//clears the display list before re-populating
	public void clearDisplayList() {
		advancedDisplayList.getItems().clear();
	}

	@FXML
	public void searchButtonClicked(ActionEvent event) throws IOException {
		//initialize base query
		String searchQuery = SEARCH_BASE;
		//count variable
		int count = 1;
		//binding index variables
		int indexO = 0;
		int indexP = 0;
		int indexC = 0;
		int indexL = 0;
		int indexA = 0;
		int indexS = 0;
		int indexR = 0;

		//build query. add "AND" statements if selection is not 0. set bind indexes so they
		//are placed in the correct positions of the prepared statement
		if (overAll > 0) {
			if (overAllCompare.equals(">")) {
				searchQuery = searchQuery+SEARCH_OVERALL;
			}
			else if (overAllCompare.equals("<")) {
				searchQuery = searchQuery+SEARCH_OVERALL_L2;
			}
			else if (overAllCompare.equals("=")) {
				searchQuery = searchQuery+SEARCH_OVERALL_5;
			}
			count++;
			indexO = count;
		}
		if (price > 0) {
			if (priceCompare.equals(">")) {
				searchQuery = searchQuery+SEARCH_PRICE;
			}
			else if (priceCompare.equals("<")) {
				searchQuery = searchQuery+SEARCH_PRICE_L2;
			}
			else if (priceCompare.equals("=")) {
				searchQuery = searchQuery+SEARCH_PRICE_5;
			}
			count++;
			indexP = count;
		}
		if (cleanliness > 0) {
			if (cleanCompare.equals(">")) {
				searchQuery = searchQuery+SEARCH_CLEAN;
			}
			else if (cleanCompare.equals("<")) {
				searchQuery = searchQuery+SEARCH_CLEAN_L2;
			}
			else if (cleanCompare.equals("=")) {
				searchQuery = searchQuery+SEARCH_CLEAN_5;
			}
			count++;
			indexC = count;
		}
		if (location > 0) {
			if (locCompare.equals(">")) {
				searchQuery = searchQuery+SEARCH_LOC;
			}
			else if (locCompare.equals("<")) {
				searchQuery = searchQuery+SEARCH_LOC_L2;
			}
			else if (locCompare.equals("=")) {
				searchQuery = searchQuery+SEARCH_LOC_5;
			}
			count++;
			indexL = count;
		}
		if (amenities > 0) {
			if (amenCompare.equals(">")) {
				searchQuery = searchQuery+SEARCH_AMEN;
			}
			else if (amenCompare.equals("<")) {
				searchQuery = searchQuery+SEARCH_AMEN_L2;
			}
			else if (amenCompare.equals("=")) {
				searchQuery = searchQuery+SEARCH_AMEN_5;
			}
			count++;
			indexA = count;
		}
		if (service > 0) {
			if (servCompare.equals(">")) {
				searchQuery = searchQuery+SEARCH_SERV;
			}
			else if (servCompare.equals("<")) {
				searchQuery = searchQuery+SEARCH_SERV_L2;
			}
			else if (servCompare.equals("=")) {
				searchQuery = searchQuery+SEARCH_SERV_5;
			}
			count++;
			indexS = count;
		}
		if (room > 0) {
			if (roomCompare.equals(">")) {
				searchQuery = searchQuery+SEARCH_ROOM;
			}
			else if (roomCompare.equals("<")) {
				searchQuery = searchQuery+SEARCH_ROOM_L2;
			}
			else if (roomCompare.equals("=")) {
				searchQuery = searchQuery+SEARCH_ROOM_5;
			}
			count++;
			indexR = count;
		}
		try {
			System.out.println(searchQuery);
			System.out.println(city);
			conn = Connection.getConnection();
			ps = conn.prepareStatement(searchQuery);
			ps.setString(1, city);

			if (indexO > 0) {
				ps.setDouble(indexO, overAll);
			}
			if (indexP > 0) {
				ps.setDouble(indexP, price);
			}
			if (indexC > 0) {
				ps.setDouble(indexC, cleanliness);
			}
			if (indexL > 0) {
				ps.setDouble(indexL, location);
			}
			if (indexA > 0) {
				ps.setDouble(indexA, amenities);
			}
			if (indexS > 0) {
				ps.setDouble(indexS, service);
			}
			if (indexR > 0) {
				ps.setDouble(indexR, room);
			}
			//execute the query
			ResultSet rs = ps.executeQuery();
			clearDisplayList();
			while(rs.next()) {
				System.out.println(rs.getString("hotelName"));
				advancedListItems.addAll(rs.getString("hotelName"));
			}
			advancedDisplayList.setItems(advancedListItems);
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try { ps.close(); } catch (Exception e) { /* ignored */ }
			try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
	}

	@FXML
	public void exitProgram() {
		exitButton.setOnAction(e -> System.exit(0));
	}

}
