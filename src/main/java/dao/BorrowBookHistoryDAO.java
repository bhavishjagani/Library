package dao;
import models.BorrowBookHistory;
import utils.DatabaseConnector;
import java.sql.*;
import java.util.ArrayList;

public class BorrowBookHistoryDAO {
    public void addBorrowRecord(BorrowBookHistory record) {
        String query = "INSERT INTO BORROW_BOOK_HISTORY (ISBN, USER_ID, BORROW_DATE, RETURN_DATE) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, record.getISBN());
            statement.setString(2, record.getUserId());
            statement.setDate(3, record.getBorrowDate());
            statement.setDate(4, record.getReturnDate());
            statement.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<BorrowBookHistory> getUserBorrowHistory() {
        ArrayList<BorrowBookHistory> history = new ArrayList<>();
        String query = "SELECT * FROM BORROW_BOOK_HISTORY";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    history.add(new BorrowBookHistory(
                            resultSet.getInt("ID"),
                            resultSet.getString("ISBN"),
                            resultSet.getString("USER_ID"),
                            resultSet.getDate("BORROW_DATE"),
                            resultSet.getDate("RETURN_DATE")
                    ));
                }
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return history;
    }
}