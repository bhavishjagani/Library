package controllers;
import dao.BookDAO;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import models.Book;
import java.sql.SQLException;

public class BookController {
    @FXML
    private TextField ISBN;
    @FXML
    private TextField title;
    @FXML
    private TextField author;
    @FXML
    private TextField quantity;

    private final BookDAO bookDAO = new BookDAO();

    @FXML
    protected void addBook() throws SQLException {
        String ISBNText = ISBN.getText();
        String titleText = title.getText();
        String authorText = author.getText();
        if (ISBNText.isEmpty() || titleText.isEmpty() || authorText.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Add Book Fail");
            return;
        }
        Book book = new Book();
        book.setISBN(ISBNText);
        book.setTitle(titleText);
        book.setAuthor(authorText);
        bookDAO.addBook(book);
        showAlert(Alert.AlertType.CONFIRMATION, "Success", "Add Book Success");
    }
    @FXML
    protected void updateBook() throws SQLException {
        String ISBNText = ISBN.getText();
        String titleText = title.getText();
        String authorText = author.getText();
        if (ISBNText.isEmpty() || titleText.isEmpty() || authorText.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Update Book Fail");
            return;
        }
        Book book = new Book();
        book.setTitle(titleText);
        book.setAuthor(authorText);
        bookDAO.updateBook(book);
        showAlert(Alert.AlertType.CONFIRMATION, "Success", "Update Book Success");
    }
    @FXML
    protected void removeBook() throws SQLException {
        String ISBNText = ISBN.getText();
        String quantityText = quantity.getText();
        if (ISBNText.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Remove Book Fail");
            return;
        }
        bookDAO.removeBook(ISBNText, Integer.parseInt(quantityText));
        showAlert(Alert.AlertType.CONFIRMATION, "Success", "Remove Book Success");
    }
    @FXML
    protected void searchBook() throws SQLException {
        String ISBNText = ISBN.getText();
        if (ISBNText.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Search Book Fail");
            return;
        }
        Book book = bookDAO.searchBook(ISBNText);
        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        showAlert(Alert.AlertType.CONFIRMATION, "Success", "Search Book Success");
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}