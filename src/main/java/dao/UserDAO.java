package dao;
import models.Book;
import models.User;
import utils.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public User validateLogin(String username, String password) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setRole(resultSet.getString("role"));
                return user;
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public void newUser(User user) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "INSERT INTO USERS (USERNAME, PASSWORD, ROLE) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            statement.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public User getAdmin(String role) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM USERS WHERE ROLE=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, role);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setRole(resultSet.getString("role"));
                return user;
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public boolean borrowBook(Book book) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "UPDATE BOOKS SET QUANTITY=QUANTITY-1 WHERE ID=? AND QUANTITY>0";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, book.getId());
            statement.setInt(2, book.getQuantity());
            if (statement.executeUpdate() > 0) {
                System.out.println("Book has been borrowed successfully.");
                return true;
            }
            System.out.println("Book is either not available or ISBN is incorrect.");
            return false;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public boolean returnBook(Book book) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "UPDATE BOOKS SET QUANTITY=QUANTITY+1 WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, book.getId());
            statement.setInt(2, book.getQuantity());
            if (statement.executeUpdate() > 0) {
                System.out.println("Book has been returned successfully.");
                return true;
            }
            System.out.println("Book ISBN is incorrect.");
            return false;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}