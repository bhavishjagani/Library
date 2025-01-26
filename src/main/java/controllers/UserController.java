package controllers;
import com.library.library.Main;
import dao.AdminDAO;
import dao.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import models.Admin;
import models.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class UserController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField reenterPassword;

    private final UserDAO userDAO = new UserDAO();
    private final AdminDAO adminDAO = new AdminDAO();

    @FXML
    protected void userLogin(ActionEvent e) throws SQLException, IOException {
        try {
            String usernameText = username.getText();
            String passwordText = password.getText();
            if (usernameText.isEmpty() || passwordText.isEmpty()) {
                throw new IllegalArgumentException("Fields Can Not Be Empty");
            }
            System.out.println("Hello");
            userDAO.validateLogin(usernameText, passwordText);
            showAlert(Alert.AlertType.CONFIRMATION, "Success", "Login Success");
            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("book-borrow-and-return.fxml")));
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 1000, 750));
            stage.setTitle("Book Borrow and Return");
            stage.show();
            Stage previousStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            previousStage.close();
        }
        catch (Exception e1) {
            e1.printStackTrace();
            showAlert(Alert.AlertType.ERROR,"Error", "Login Fail" );
        }

    }
    @FXML
    protected void adminLogin() throws SQLException {
        String usernameText = username.getText();
        String passwordText = password.getText();
        if (usernameText.isEmpty() || passwordText.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Login Fail");
            return;
        }
        adminDAO.validateLogin(usernameText, passwordText);
        showAlert(Alert.AlertType.CONFIRMATION, "Success", "Login Success");
    }
    @FXML
    protected void userSignup(ActionEvent e) throws Exception {
        String usernameText = username.getText();
        String passwordText = password.getText();
        String reenterPasswordText = reenterPassword.getText();
        if (usernameText.isEmpty() || passwordText.isEmpty() || reenterPasswordText.isEmpty() || (! passwordText.equals(reenterPasswordText))) {
            showAlert(Alert.AlertType.ERROR, "Error", "Signup Fail");
            return;
        }
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
        String reenterPasswordText = reenterPassword.getText();
        if (usernameText.isEmpty() || passwordText.isEmpty() || reenterPasswordText.isEmpty() || (! passwordText.equals(reenterPasswordText))) {
            showAlert(Alert.AlertType.ERROR, "Error", "Signup Fail");
            return;
        }
        Admin admin = new Admin();
        admin.setUsername(usernameText);
        admin.setPassword(passwordText);
        adminDAO.newAdmin(admin);
        showAlert(Alert.AlertType.CONFIRMATION, "Success", "Signup Success");
        backToUserLogin(e);
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
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
    @FXML
    protected void toNewSignup(ActionEvent e) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("new-signup.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 1000, 750));
        stage.setTitle("New Signup");
        stage.show();
        Stage previousStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        previousStage.close();
    }
}
//Create other methods like userLogin(), signup, create BookController and do same thing (add book, remove book, search book, update book)