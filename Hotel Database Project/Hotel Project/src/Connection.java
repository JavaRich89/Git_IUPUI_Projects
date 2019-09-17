import java.sql.DriverManager;

public class Connection {

	private final static String USER_NAME = "admin";
	private final static String PASS_WORD = "admin";
	private final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";

	public static boolean adminLogin(String usernameIn, String passwordIn) {
		if (usernameIn.equals(USER_NAME) && passwordIn.equals(PASS_WORD)) {
			return true;
		}
		else {
			return false;
		}
	}

	public static java.sql.Connection getConnection() throws Exception {
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driver);
			java.sql.Connection conn = DriverManager.getConnection(URL,USER_NAME,PASS_WORD);
			return conn;
		} catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
