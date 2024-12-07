package com.library.library;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class HelloController {
    @FXML
    protected void Login() throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) root.getScene().getWindow();
            Scene scene = new Scene(root, 1000, 750);
            stage.setTitle("Library Management System");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}

//Review all 50 leetcode problems, test next time
//Continue working on library, update teacher