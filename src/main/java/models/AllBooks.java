package models;

public class AllBooks {
    private int bookid;
    private String title;
    private String author;
    private int quantity;

    public AllBooks(int bookid, String title, String author, int quantity) {
        this.bookid = bookid;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    public int getBookid() {
        return bookid;
    }
    public void setBookid(int bookid) {
        this.bookid = bookid;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
