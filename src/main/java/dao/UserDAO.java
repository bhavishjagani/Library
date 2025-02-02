package dao;
import models.*;
import utils.DatabaseConnector;
import java.sql.*;

public class UserDAO {
    public boolean validateLogin(String username, String password) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD=? AND ROLE=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, "User");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setRole(resultSet.getString("role"));
                return true;
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public void newUser(User user) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "INSERT INTO USERS (USERNAME, PASSWORD, ROLE) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, "User");
            statement.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public User getAdmin(String role) {
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
    public boolean borrowBook(Book book) {
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
    public boolean returnBook(Book book) {
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