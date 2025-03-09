package models;
import java.sql.Date;

public class BorrowBookHistory {
    private int id;
    private String ISBN;
    private String userId;
    private Date borrowDate;
    private Date returnDate;

    public BorrowBookHistory(int id, String ISBN, String userId, Date borrowDate, Date returnDate) {
        this.id = id;
        this.ISBN = ISBN;
        this.userId = userId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getISBN() { return ISBN; }
    public void setISBN(String ISBN) { this.ISBN = ISBN; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public Date getBorrowDate() { return borrowDate; }
    public void setBorrowDate(Date borrowDate) { this.borrowDate = borrowDate; }
    public Date getReturnDate() { return returnDate; }
    public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }
}