package model;

import java.sql.Date;

public class LoanDetails {
    private int loanDetailID;
    private int loanID;
    private int bookID;
    private Date returnDate;
    private String note;
    private String status;

    // Constructors
    public LoanDetails() {}

    public LoanDetails(int loanDetailID, int loanID, int bookID, Date returnDate, String note, String status) {
        this.loanDetailID = loanDetailID;
        this.loanID = loanID;
        this.bookID = bookID;
        this.returnDate = returnDate;
        this.note = note;
        this.status = status;
    }

    // Getters and Setters
    public int getLoanDetailID() { return loanDetailID; }
    public void setLoanDetailID(int loanDetailID) { this.loanDetailID = loanDetailID; }

    public int getLoanID() { return loanID; }
    public void setLoanID(int loanID) { this.loanID = loanID; }

    public int getBookID() { return bookID; }
    public void setBookID(int bookID) { this.bookID = bookID; }

    public Date getReturnDate() { return returnDate; }
    public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "LoanDetails{" +
               "loanDetailID=" + loanDetailID +
               ", loanID=" + loanID +
               ", bookID=" + bookID +
               ", returnDate=" + returnDate +
               ", note='" + note + '\'' +
               ", status='" + status + '\'' +
               '}';
    }
}
