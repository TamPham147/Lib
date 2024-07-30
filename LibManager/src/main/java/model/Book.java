package model;

public class Book {
    private int bookID;
    private String bookTitle;
    private int categoryID;
    private int publisherID;
    private int authorID;
    private int publishYear;
    private int quantity;
    private int shelfID;
    private String image;
    private String status;

    // Constructors
    public Book() {}

    public Book(int bookID, String bookTitle, int categoryID, int publisherID, int authorID, int publishYear, int quantity, int shelfID, String image, String status) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.categoryID = categoryID;
        this.publisherID = publisherID;
        this.authorID = authorID;
        this.publishYear = publishYear;
        this.quantity = quantity;
        this.shelfID = shelfID;
        this.image = image;
        this.status = status;
    }

    // Getters and Setters
    public int getBookID() { return bookID; }
    public void setBookID(int bookID) { this.bookID = bookID; }

    public String getBookTitle() { return bookTitle; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }

    public int getCategoryID() { return categoryID; }
    public void setCategoryID(int categoryID) { this.categoryID = categoryID; }

    public int getPublisherID() { return publisherID; }
    public void setPublisherID(int publisherID) { this.publisherID = publisherID; }

    public int getAuthorID() { return authorID; }
    public void setAuthorID(int authorID) { this.authorID = authorID; }

    public int getPublishYear() { return publishYear; }
    public void setPublishYear(int publishYear) { this.publishYear = publishYear; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getShelfID() { return shelfID; }
    public void setShelfID(int shelfID) { this.shelfID = shelfID; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Book{" +
               "bookID=" + bookID +
               ", bookTitle='" + bookTitle + '\'' +
               ", categoryID=" + categoryID +
               ", publisherID=" + publisherID +
               ", authorID=" + authorID +
               ", publishYear=" + publishYear +
               ", quantity=" + quantity +
               ", shelfID=" + shelfID +
               ", image='" + image + '\'' +
               ", status='" + status + '\'' +
               '}';
    }
}
