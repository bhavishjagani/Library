package utils;
import java.sql.*;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/Library_DB";
    private static final String user = "root";
    private static final String password = "Homestead3#";

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, user, password);
            createUserTable(connection);
            createBookTable(connection);
            return connection;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void createUserTable(Connection connection) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS USERS (ID INT AUTO_INCREMENT PRIMARY KEY, USERNAME VARCHAR (50), PASSWORD VARCHAR (255), ROLE ENUM ('USER', 'ADMIN') DEFAULT 'USER')";
        codeReducer(connection, query);
    }
    public static void createBookTable(Connection connection) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS BOOKS (ID INT AUTO_INCREMENT PRIMARY KEY, ISBN VARCHAR (13), TITLE VARCHAR (250), AUTHOR VARCHAR (100), QUANTITY INT DEFAULT 1)";
        codeReducer(connection, query);
    }
    public static void codeReducer(Connection connection, String createTableQuery) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String query = createTableQuery;
            statement.executeUpdate(query);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SQLException();
        }
    }
}