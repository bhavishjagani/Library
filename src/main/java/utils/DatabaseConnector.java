package utils;
import java.sql.*;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/Library_DB";
    private static final String USER = "root";
    private static final String PASSWORD = "Homestead3#";

    public static Connection getConnection() {
        try {
            // Ensure MySQL Driver is loaded
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Initialize database tables
            createUserTable(connection);
            createBookTable(connection);
            createBorrowBookTable(connection);
            createBorrowHistoryTable(connection);

            return connection;

        }
        catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database", e);
        }
        catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to load MySQL JDBC Driver", e);
        }
    }

    // Method to create USERS table
    public static void createUserTable(Connection connection) throws SQLException {
        String query = """
                CREATE TABLE IF NOT EXISTS USERS (
                    ID INT AUTO_INCREMENT PRIMARY KEY, 
                    USERNAME VARCHAR(50) NOT NULL UNIQUE, 
                    PASSWORD VARCHAR(255) NOT NULL, 
                    EMAIL VARCHAR(100) UNIQUE, 
                    ROLE ENUM('USER', 'ADMIN') DEFAULT 'USER'
                )""";
        codeReducer(connection, query);
    }

    // Method to create BOOKS table
    public static void createBookTable(Connection connection) throws SQLException {
        String query = """
                CREATE TABLE IF NOT EXISTS BOOKS (
                    ID INT AUTO_INCREMENT PRIMARY KEY, 
                    ISBN VARCHAR(13) NOT NULL UNIQUE, 
                    TITLE VARCHAR(250) NOT NULL, 
                    AUTHOR VARCHAR(100) NOT NULL, 
                    QUANTITY INT DEFAULT 1 CHECK (QUANTITY >= 0), 
                    STATUS ENUM('AVAILABLE', 'BORROWED') DEFAULT 'AVAILABLE'
                )""";
        codeReducer(connection, query);
    }

    // Method to create BORROWEDBOOKS table
    public static void createBorrowBookTable(Connection connection) throws SQLException {
        String query = """
                CREATE TABLE IF NOT EXISTS BORROWEDBOOKS (
                    ID INT AUTO_INCREMENT PRIMARY KEY, 
                    USER_ID INT NOT NULL, 
                    BOOK_ID INT NOT NULL, 
                    BORROW_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
                    RETURN_DATE TIMESTAMP NULL,
                    FOREIGN KEY (USER_ID) REFERENCES USERS(ID) ON DELETE CASCADE, 
                    FOREIGN KEY (BOOK_ID) REFERENCES BOOKS(ID) ON DELETE CASCADE
                )""";
        codeReducer(connection, query);
    }

    // Method to create BORROW HISTORY table
    public static void createBorrowHistoryTable(Connection connection) throws SQLException {
        String query = """
                CREATE TABLE IF NOT EXISTS BORROW_BOOK_HISTORY (
                    ID INT AUTO_INCREMENT PRIMARY KEY,
                    ISBN VARCHAR(13) NOT NULL UNIQUE,
                    USER_ID INT NOT NULL,
                    BORROW_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    RETURN_DATE TIMESTAMP NULL,
                    FOREIGN KEY (USER_ID) REFERENCES USERS(ID) ON DELETE CASCADE
                )""";
        codeReducer(connection, query);
    }

    public static void codeReducer(Connection connection, String createTableQuery) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableQuery);
        }
        catch (SQLException e) {
            System.err.println("Failed to execute query: " + createTableQuery);
            e.printStackTrace();
            throw e;
        }
    }
}