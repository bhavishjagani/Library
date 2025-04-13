package controllers;
import com.library.library.Main;
import dao.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Objects;

public class UserController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField reenterPassword;
    @FXML
    private TextField borrowISBN;
    @FXML
    private TextField returnISBN;

    private final UserDAO userDAO = new UserDAO();
    private final AdminDAO adminDAO = new AdminDAO();
    private final BorrowBookHistoryDAO borrowBookHistoryDAO = new BorrowBookHistoryDAO();
    private static User userLoggedIn = null;


    @FXML
    protected void userLogin(ActionEvent e) {
        try {
            String usernameText = username.getText();
            String passwordText = password.getText();
            if (usernameText.isEmpty() || passwordText.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Fields Cannot Be Empty");
                return;
            }
            if (userDAO.validateLogin(usernameText, passwordText)) {
                User user = userDAO.getUserByUsername(usernameText);
                if (user != null && user.getId() != 0) {
                    userLoggedIn = user;
                    System.out.println("User ID set to: " + userLoggedIn.getId());
                    showAlert(Alert.AlertType.CONFIRMATION, "Success", "Login Success");
                    codeReducer(e, "book-borrow-and-return.fxml", "Book Borrow and Return");
                }
                else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to retrieve user details.");
                }
            }
            else {
                showAlert(Alert.AlertType.ERROR, "Error", "Login Fail");
            }
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    protected void adminLogin(ActionEvent e) {
        try {
            String usernameText = username.getText();
            String passwordText = password.getText();
            if (usernameText.isEmpty() || passwordText.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Fields Cannot Be Empty");
                return;
            }
            if (adminDAO.validateLogin(usernameText, passwordText)) {
                showAlert(Alert.AlertType.CONFIRMATION, "Success", "Login Success");
                toAdminBookManagement(e);
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Login Fail");
            }
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    protected void userSignup(ActionEvent e) throws Exception {
        String usernameText = username.getText();
        String passwordText = password.getText();
        signupCodeReducer(usernameText, passwordText);
        User user = new User();
        user.setUsername(usernameText);
        user.setPassword(passwordText);
        userDAO.newUser(user);
        showAlert(Alert.AlertType.CONFIRMATION, "Success", "Signup Success");
        backToUserLogin(e);
    }

    @FXML
    protected void adminSignup(ActionEvent e) throws Exception {
        String usernameText = username.getText();
        String passwordText = password.getText();
        signupCodeReducer(usernameText, passwordText);
        Admin admin = new Admin();
        admin.setUsername(usernameText);
        admin.setPassword(passwordText);
        adminDAO.newAdmin(admin);
        showAlert(Alert.AlertType.CONFIRMATION, "Success", "Signup Success");
        backToUserLogin(e);
    }

    @FXML
    protected void borrowBook() {
        String ISBNText = borrowISBN.getText();

        if (ISBNText.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter a book ISBN to borrow.");
            return;
        }

        if (userLoggedIn == null || userLoggedIn.getId() == 0) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please log in before borrowing a book.");
            return;
        }

        System.out.println("Borrowing book with ISBN: " + ISBNText + " by User ID: " + userLoggedIn.getId());

        BorrowedBooks borrowedBook = new BorrowedBooks();
        borrowedBook.setUserId(userLoggedIn.getId());
        Book book = new Book();
        book.setISBN(ISBNText);
        boolean success = userDAO.borrowBook(borrowedBook, book);
        showAlert(success ? Alert.AlertType.CONFIRMATION : Alert.AlertType.ERROR, success ? "Success" : "Error", success ? "Book borrowed successfully." : "Book is not available.");
        if (success) {
            BorrowBookHistory borrowBookHistory = new BorrowBookHistory(book.getId(), book.getISBN(), String.valueOf(userLoggedIn.getId()), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
            borrowBookHistoryDAO.addBorrowRecord(borrowBookHistory);
        }
    }

    @FXML
    protected void returnBook() {
        String ISBNText = returnISBN.getText();
        if (ISBNText.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter a book ISBN to return.");
            return;
        }
        Book book = new Book();
        book.setISBN(ISBNText);
        boolean success = userDAO.returnBook(book);
        showAlert(success ? Alert.AlertType.CONFIRMATION : Alert.AlertType.ERROR, success ? "Success" : "Error", success ? "Book returned successfully." : "Invalid ISBN or book return failed.");
    }

    @FXML
    protected void backToUserLogin(ActionEvent e) throws Exception {
        codeReducer(e, "user-login.fxml", "User Login");
    }
    @FXML
    protected void backToBookBorrowAndReturn(ActionEvent e) throws Exception {
        codeReducer(e, "book-borrow-and-return.fxml", "Book Borrow And Return");
    }
    @FXML
    protected void toNewSignup(ActionEvent e) throws Exception {
        codeReducer(e, "new-signup.fxml", "New Signup");
    }
    @FXML
    protected void toAllBooks(ActionEvent e) throws Exception {
        codeReducer(e, "all-books.fxml", "All Books");
    }
    @FXML
    protected void toAdminBookManagement(ActionEvent e) throws Exception {
        codeReducer(e, "admin-book-management.fxml", "Admin Book Management");
    }
    @FXML
    protected void toAdminLogin(ActionEvent e) throws Exception {
        codeReducer(e, "admin-login.fxml", "Admin Book Management");
    }
    @FXML
    protected void filterBook(ActionEvent e) throws Exception {
        codeReducer(e, "filter-book.fxml", "Filter Book");
    }

    @FXML
    protected void codeReducer(ActionEvent e, String className, String title) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(className)));
        if (root == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Unable to load the requested page.");
            return;
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 1000, 750));
        stage.setTitle(title);
        stage.show();
        Stage previousStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        previousStage.close();
    }

    @FXML
    protected void signupCodeReducer(String usernameText, String passwordText) {
        String reenterPasswordText = reenterPassword.getText();
        if (usernameText.isEmpty() || passwordText.isEmpty() || reenterPasswordText.isEmpty()
                || (!passwordText.equals(reenterPasswordText))) {
            showAlert(Alert.AlertType.ERROR, "Error", "Signup Fail");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}