package dao;
import models.Book;
import utils.DatabaseConnector;
import java.sql.*;
import java.util.List;

public class BookDAO {
    public void addBook(Book book) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "INSERT INTO BOOKS (ISBN, TITLE, AUTHOR, QUANTITY) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, book.getISBN());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setInt(4, book.getQuantity()+1);
            statement.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void updateBook(Book book) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "UPDATE BOOKS SET TITLE=?, AUTHOR=?, QUANTITY=? WHERE ISBN=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getQuantity());
            statement.setString(4, book.getISBN());
            statement.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void removeBook(String ISBN) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "DELETE FROM BOOKS WHERE ISBN=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, ISBN);
            statement.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Book searchBook(String ISBN) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM BOOKS WHERE ISBN=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, ISBN);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Book book = new Book();
                book.setISBN(resultSet.getString("ISBN"));
                book.setTitle(resultSet.getString("TITLE"));
                book.setAuthor(resultSet.getString("AUTHOR"));
                book.setQuantity(resultSet.getInt("QUANTITY"));
                return book;
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // Implement a model like User.java, Book.java, create model for BorrowBook History, whatever fields I want to display, holding data, data used in bookDAO, bookController
    // Write query to get data
}