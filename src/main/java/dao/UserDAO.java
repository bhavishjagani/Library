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
    public boolean borrowBook(BorrowedBooks borrowedBook, Book book) {
        try (Connection connection = DatabaseConnector.getConnection()) {

            // Step 1: Get the Book ID using the provided ISBN
            String getBookIdQuery = "SELECT ID FROM BOOKS WHERE ISBN = ?";
            PreparedStatement bookIdStatement = connection.prepareStatement(getBookIdQuery);
            bookIdStatement.setString(1, book.getISBN());
            ResultSet bookResultSet = bookIdStatement.executeQuery();

            if (!bookResultSet.next()) {
                System.out.println("No book found with ISBN: " + book.getISBN());
                return false;
            }

            int bookId = bookResultSet.getInt("ID"); // Get the correct book ID
            book.setBookID(bookId);

            // Step 2: Check if the User ID exists
            String checkUserQuery = "SELECT ID FROM USERS WHERE ID = ?";
            PreparedStatement userCheckStatement = connection.prepareStatement(checkUserQuery);
            userCheckStatement.setInt(1, borrowedBook.getUserID());
            ResultSet userResultSet = userCheckStatement.executeQuery();

            if (!userResultSet.next()) {
                System.out.println("User ID " + borrowedBook.getUserID() + " does not exist.");
                return false;
            }

            // Step 3: Update the book status and reduce quantity by 1
            String updateBookQuery = "UPDATE BOOKS SET STATUS = 'borrowed', QUANTITY = QUANTITY - 1 WHERE ID = ? AND QUANTITY > 0";
            PreparedStatement updateBookStatement = connection.prepareStatement(updateBookQuery);
            updateBookStatement.setInt(1, bookId);

            if (updateBookStatement.executeUpdate() > 0) {
                System.out.println("Book with ID " + bookId + " has been borrowed by User ID: " + borrowedBook.getUserID());

                // Step 4: Insert into the BorrowedBooks table
                String insertBorrowedBookQuery = "INSERT INTO BORROWEDBOOKS (USER_ID, BOOK_ID, BORROW_DATE) VALUES (?, ?, ?)";
                PreparedStatement insertBorrowedBookStatement = connection.prepareStatement(insertBorrowedBookQuery);
                insertBorrowedBookStatement.setInt(1, borrowedBook.getUserID());
                insertBorrowedBookStatement.setInt(2, bookId); // Correct book ID
                insertBorrowedBookStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                insertBorrowedBookStatement.executeUpdate();
                return true;
            }
            else {
                System.out.println("Book is not available or ISBN is incorrect.");
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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
                book.setId(resultSet.getInt("ID"));
                book.setTitle(resultSet.getString("TITLE"));
                book.setAuthor(resultSet.getString("AUTHOR"));
                book.setQuantity(resultSet.getInt("QUANTITY"));
                books.add(book);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(books);
        return books;
    }
    public User getUserByUsername(String username) {
        User user = null;
        String query = "SELECT * FROM USERS WHERE USERNAME = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("ID"));
                user.setUsername(resultSet.getString("USERNAME"));
                user.setPassword(resultSet.getString("PASSWORD"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public Book searchBookTitle(String ISBN) {
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

    public Book searchBookAuthor(String ISBN) {
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
}