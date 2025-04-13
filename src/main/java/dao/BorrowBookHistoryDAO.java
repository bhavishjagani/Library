package dao;
import models.BorrowBookHistory;
import utils.DatabaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowBookHistoryDAO {
    public void addBorrowRecord(BorrowBookHistory record) {
        String query = "INSERT INTO BORROW_BOOK_HISTORY (ISBN, USER_ID, BORROW_DATE, RETURN_DATE) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, record.getISBN());
            statement.setString(2, record.getUserId());
            statement.setDate(3, record.getBorrowDate());
            statement.setDate(4, record.getReturnDate());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Successfully added borrow record for ISBN: " + record.getISBN());
            } else {
                System.out.println("Failed to add borrow record.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<BorrowBookHistory> getUserBorrowHistory() {
        List<BorrowBookHistory> history = new ArrayList<>();
        String query = "SELECT * FROM BORROW_BOOK_HISTORY";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                BorrowBookHistory entry = new BorrowBookHistory(
                        resultSet.getInt("ID"),
                        resultSet.getString("ISBN"),
                        resultSet.getString("USER_ID"),
                        resultSet.getDate("BORROW_DATE"),
                        resultSet.getDate("RETURN_DATE")
                );
                history.add(entry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }
}