package com.library.library;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 750);
        stage.setTitle("Library Management System");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}

//    Features of the Library Management System
//
//         1. Admin Login:
//         • Admin logs in to manage the library.
//         2. Book Management:
//         • Add, update, delete, and search for books.
//         3. Borrow/Return Books:
//         • Assign books to borrowers.
//         • Record when books are returned.
//         4. Borrower Management:
//         • Add and update borrower details.
//         5. Database Integration:
//         • Use MySQL to store book and borrower data.

//Finish Prototype in Figma

//Add Book, update book, delete book, search book --> use isbn, title/author, create additional frames when button is clicked, finalize colors, fonts, figma
