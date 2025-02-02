package controllers;
import com.library.library.Main;
import dao.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.*;
import java.io.IOException;
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
    protected void userLogin(ActionEvent e) {
        try {
            String usernameText = username.getText();
            String passwordText = password.getText();
            if (usernameText.isEmpty() || passwordText.isEmpty()) {
                throw new IllegalArgumentException("Fields Can Not Be Empty");
            }
            if (userDAO.validateLogin(usernameText, passwordText)) {
                showAlert(Alert.AlertType.CONFIRMATION, "Success", "Login Success");
                codeReducer(e, "book-borrow-and-return.fxml", "Book Borrow and Return");
            }
            else {
                showAlert(Alert.AlertType.ERROR,"Error", "Login Fail" );
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
                throw new IllegalArgumentException("Fields Can Not Be Empty");
            }
            if (adminDAO.validateLogin(usernameText, passwordText)) {
                showAlert(Alert.AlertType.CONFIRMATION, "Success", "Login Success");
                toAdminBookManagement(e);
            }
            else {
                showAlert(Alert.AlertType.ERROR,"Error", "Login Fail" );
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
    protected void backToUserLogin(ActionEvent e) throws Exception {
        codeReducer(e, "user-login.fxml", "User Login");
    }
    @FXML
    protected void toNewSignup(ActionEvent e) throws Exception {
        codeReducer(e, "new-signup.fxml", "New Signup");
    }
    @FXML
    protected void toAdminLogin(ActionEvent e) throws Exception {
        codeReducer(e, "admin-login.fxml", "Admin Login");
    }
    @FXML
    protected void toAdminBookManagement(ActionEvent e) throws Exception {
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
    @FXML
    protected void signupCodeReducer(String usernameText, String passwordText) {
        String reenterPasswordText = reenterPassword.getText();
        if (usernameText.isEmpty() || passwordText.isEmpty() || reenterPasswordText.isEmpty() || (! passwordText.equals(reenterPasswordText))) {
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