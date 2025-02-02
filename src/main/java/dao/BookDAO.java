package dao;
import models.Book;
import utils.DatabaseConnector;
import java.sql.*;

public class BookDAO {
    public void addBook(Book book) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "INSERT INTO BOOKS (ISBN, TITLE, AUTHOR) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, book.getISBN());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            book.setQuantity(book.getQuantity()+1);
            statement.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void updateBook(Book book) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "UPDATE BOOKS SET TITLE=?, AUTHOR=? WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getId());
            statement.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void removeBook(String ISBN, int quantity) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "DELETE FROM BOOKS WHERE ISBN=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, ISBN);
            if (quantity==0) {
                throw new IllegalArgumentException();
            }
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
                return book;
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}