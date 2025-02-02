package controllers;
import com.library.library.Main;
import dao.BookDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import models.Book;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

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
    protected void addBook() {
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
    protected void updateBook() {
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
    protected void removeBook() {
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
    protected void searchBook() {
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
    @FXML
    protected void toAddBook(ActionEvent e) throws Exception {
        codeReducer(e, "add-book.fxml", "Add Book");
    }
    @FXML
    protected void toUpdateBook(ActionEvent e) throws Exception {
        codeReducer(e, "update-book.fxml", "Update Book");
    }
    @FXML
    protected void toRemoveBook(ActionEvent e) throws Exception {
        codeReducer(e, "remove-book.fxml", "Remove Book");
    }
    @FXML
    protected void toSearchBook(ActionEvent e) throws Exception {
        codeReducer(e, "search-book.fxml", "Search Book");
    }
    @FXML
    protected void backToAdminLogin(ActionEvent e) throws Exception {
        codeReducer(e, "admin-login.fxml", "Admin Login");
    }
    @FXML
    protected void backToAdminBookManagement(ActionEvent e) throws Exception {
        codeReducer(e, "admin-book-management.fxml", "Admin Book Management");
    }
    @FXML
    protected void codeReducer(ActionEvent e, String className, String title) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(className)));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 1000, 750));
        stage.setTitle(title);
        stage.show();
        Stage previousStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        previousStage.close();
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}