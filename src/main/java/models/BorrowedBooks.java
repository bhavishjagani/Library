package models;
import java.sql.Timestamp;

public class BorrowedBooks {
    private int id;
    private int userID;
    private int bookID;
    private Timestamp borrowDate;
    private Timestamp returnDate;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public int getBookID() {
        return bookID;
    }
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
    public Timestamp getBorrowDate() {
        return borrowDate;
    }
    public void setBorrowDate(Timestamp borrowDate) {
        this.borrowDate = borrowDate;
    }
    public Timestamp getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }
}