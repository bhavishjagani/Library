package controllers;
import com.library.library.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;
public class Controller {
    @FXML
    protected void newSignUp(ActionEvent e) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("new-signup.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 1000, 750));
        stage.setTitle("New Signup");
        stage.show();
        Stage previousStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        previousStage.close();
    }
    @FXML
    protected void adminLogin(ActionEvent e) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("admin-login.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 1000, 750));
        stage.setTitle("Admin Login");
        stage.show();
        Stage previousStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        previousStage.close();
    }
    @FXML
    protected void bookBorrowAndReturn(ActionEvent e) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("book-borrow-and-return.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 1000, 750));
        stage.setTitle("Book Borrow and Return");
        stage.show();
        Stage previousStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        previousStage.close();
    }
    @FXML
    protected void adminBookManagement(ActionEvent e) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("admin-book-management.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 1000, 750));
        stage.setTitle("Admin Book Management");
        stage.show();
        Stage previousStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        previousStage.close();
    }
    @FXML
    protected void addBook(ActionEvent e) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("add-book.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 1000, 750));
        stage.setTitle("Add Book");
        stage.show();
        Stage previousStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        previousStage.close();
    }
    @FXML
    protected void removeBook(ActionEvent e) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("remove-book.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 1000, 750));
        stage.setTitle("Remove Book");
        stage.show();
        Stage previousStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        previousStage.close();
    }
    @FXML
    protected void searchBook(ActionEvent e) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("search-book.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 1000, 750));
        stage.setTitle("Search Book");
        stage.show();
        Stage previousStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        previousStage.close();
    }
    @FXML
    protected void updateBook(ActionEvent e) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("update-book.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 1000, 750));
        stage.setTitle("Update Book");
        stage.show();
        Stage previousStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        previousStage.close();
    }
    @FXML
    protected void backToUserLogin(ActionEvent e) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("user-login.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 1000, 750));
        stage.setTitle("User Login");
        stage.show();
        Stage previousStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        previousStage.close();
    }
}