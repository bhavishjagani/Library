package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/Library_DB";
    private static final String user = "root";
    private static final String password = "root"; //Change**

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, user, password);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}