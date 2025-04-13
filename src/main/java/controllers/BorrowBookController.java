package controllers;
import com.library.library.Main;
import dao.BorrowBookHistoryDAO;
import dao.UserDAO;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.BorrowBookHistory;
import javafx.collections.FXCollections;
import java.io.IOException;
import java.util.Objects;
import java.util.ArrayList;

public class BorrowBookController {
    @FXML
    private TableView<BorrowBookHistory> borrowHistoryTable;
    @FXML
    private TableColumn<BorrowBookHistory, Integer> idColumn;
    @FXML
    private TableColumn<BorrowBookHistory, String> ISBNColumn;
    @FXML
    private TableColumn<BorrowBookHistory, String> userIdColumn;
    @FXML
    private TableColumn<BorrowBookHistory, String> borrowDateColumn;
    @FXML
    private TableColumn<BorrowBookHistory, String> returnDateColumn;

    private final BorrowBookHistoryDAO borrowBookHistoryDAO = new BorrowBookHistoryDAO();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ISBNColumn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        borrowDateColumn.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        ArrayList<BorrowBookHistory> borrowHistoryList = (ArrayList<BorrowBookHistory>) borrowBookHistoryDAO.getUserBorrowHistory();

        // Debug print statements
//        System.out.println("Borrow History Records Count: " + borrowHistoryList.size());
//        for (BorrowBookHistory history : borrowHistoryList) {
//            System.out.println("Record: ID=" + history.getId() +
//                    ", ISBN=" + history.getISBN() +
//                    ", User ID=" + history.getUserId() +
//                    ", Borrow Date=" + history.getBorrowDate() +
//                    ", Return Date=" + history.getReturnDate());
//        }

        ObservableList<BorrowBookHistory> borrowedBooks = FXCollections.observableArrayList(borrowHistoryList);
        borrowHistoryTable.setItems(borrowedBooks);
        borrowHistoryTable.refresh();  // 👈 **FORCE REFRESH**
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
}
