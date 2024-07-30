package model;

import java.sql.Date;

public class Loan {
    private int loanID;
    private int staffID;
    private int readerID;
    private Date loanDate;
    private String status;

    // Constructors
    public Loan() {}

    public Loan(int loanID, int staffID, int readerID, Date loanDate, String status) {
        this.loanID = loanID;
        this.staffID = staffID;
        this.readerID = readerID;
        this.loanDate = loanDate;
        this.status = status;
    }

    // Getters and Setters
    public int getLoanID() { return loanID; }
    public void setLoanID(int loanID) { this.loanID = loanID; }

    public int getStaffID() { return staffID; }
    public void setStaffID(int staffID) { this.staffID = staffID; }

    public int getReaderID() { return readerID; }
    public void setReaderID(int readerID) { this.readerID = readerID; }

    public Date getLoanDate() { return loanDate; }
    public void setLoanDate(Date loanDate) { this.loanDate = loanDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Loan{" +
               "loanID=" + loanID +
               ", staffID=" + staffID +
               ", readerID=" + readerID +
               ", loanDate=" + loanDate +
               ", status='" + status + '\'' +
               '}';
    }
}
