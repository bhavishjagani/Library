package utils;
import java.sql.*;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/Library_DB";
    private static final String user = "root";
    private static final String password = "Homestead3#"; //**Change**//

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, user, password);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void createUserTable()  throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "CREATE TABLE USERS (ID INT AUTO_INCREMENT PRIMARY KEY, USERNAME VARCHAR (50), PASSWORD VARCHAR (255), ROLE ENUM ('USER', 'ADMIN') DEFAULT USER)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeQuery();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SQLException();
        }
    }
}
//Create similar table method for book