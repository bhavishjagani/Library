package dao;
import models.*;
import utils.DatabaseConnector;
import java.sql.*;
import java.util.*;

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
    public boolean borrowBook(Book book) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "UPDATE BOOKS SET STATUS = 'borrowed', QUANTITY=QUANTITY-1 WHERE ISBN=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, book.getISBN());
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
            String query = "UPDATE BOOKS SET STATUS = 'available', QUANTITY=QUANTITY+1 WHERE ISBN=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, book.getISBN());
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
    public List<Book> getAllBooks() {
        ArrayList<Book> books = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM BOOKS";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setISBN(resultSet.getString("ISBN"));
                book.setTitle(resultSet.getString("TITLE"));
                book.setAuthor(resultSet.getString("AUTHOR"));
                books.add(book);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return books;
    }
}