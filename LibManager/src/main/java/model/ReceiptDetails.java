package model;

import java.math.BigDecimal;

public class ReceiptDetails {
    private int receiptDetailID;
    private int receiptID;
    private int bookID;
    private BigDecimal price;
    private int quantity;
    private BigDecimal totalAmount;

    // Constructors
    public ReceiptDetails() {}

    public ReceiptDetails(int receiptDetailID, int receiptID, int bookID, BigDecimal price, int quantity, BigDecimal totalAmount) {
        this.receiptDetailID = receiptDetailID;
        this.receiptID = receiptID;
        this.bookID = bookID;
        this.price = price;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    // Getters and Setters
    public int getReceiptDetailID() { return receiptDetailID; }
    public void setReceiptDetailID(int receiptDetailID) { this.receiptDetailID = receiptDetailID; }

    public int getReceiptID() { return receiptID; }
    public void setReceiptID(int receiptID) { this.receiptID = receiptID; }

    public int getBookID() { return bookID; }
    public void setBookID(int bookID) { this.bookID = bookID; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    @Override
    public String toString() {
        return "ReceiptDetails{" +
               "receiptDetailID=" + receiptDetailID +
               ", receiptID=" + receiptID +
               ", bookID=" + bookID +
               ", price=" + price +
               ", quantity=" + quantity +
               ", totalAmount=" + totalAmount +
               '}';
    }
}
