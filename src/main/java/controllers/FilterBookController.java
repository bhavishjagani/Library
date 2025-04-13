package controllers;
import com.library.library.Main;
import dao.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Book;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class FilterBookController {
    @FXML
    private TableView<Book> filterBooksTable;
    @FXML
    private TableColumn<Book, Integer> idColumn;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, Integer> quantityColumn;
    @FXML
    private TextField title;
    @FXML
    private TextField author;

    private final UserDAO userDAO = new UserDAO();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    @FXML
    protected void filterBooks(ActionEvent e) throws Exception {
        String titleText = title.getText();
        String authorText = author.getText();

        ArrayList<Book> allBooksList = (ArrayList<Book>) userDAO.getAllBooks();
        ArrayList<Book> filteredBooksList = (ArrayList<Book>) allBooksList.stream().filter(book -> (titleText.isEmpty() || book.getTitle().toLowerCase().contains(titleText)) && (authorText.isEmpty() || book.getAuthor().toLowerCase().contains(authorText))).collect(Collectors.toList());

        filterBooksTable.setItems(FXCollections.observableArrayList(filteredBooksList));
        filterBooksTable.refresh();
    }
    @FXML
    protected void backToBookBorrowAndReturn(ActionEvent e) throws Exception {
        codeReducer(e, "book-borrow-and-return.fxml", "Book Borrow And Return");
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
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
