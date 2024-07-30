package model;

import java.sql.Date;

public class Receipt {
    private int receiptID;
    private int supplierID;
    private int staffID;
    private Date receiptDate;
    private String status;

    // Constructors
    public Receipt() {}

    public Receipt(int receiptID, int supplierID, int staffID, Date receiptDate, String status) {
        this.receiptID = receiptID;
        this.supplierID = supplierID;
        this.staffID = staffID;
        this.receiptDate = receiptDate;
        this.status = status;
    }

    // Getters and Setters
    public int getReceiptID() { return receiptID; }
    public void setReceiptID(int receiptID) { this.receiptID = receiptID; }

    public int getSupplierID() { return supplierID; }
    public void setSupplierID(int supplierID) { this.supplierID = supplierID; }

    public int getStaffID() { return staffID; }
    public void setStaffID(int staffID) { this.staffID = staffID; }

    public Date getReceiptDate() { return receiptDate; }
    public void setReceiptDate(Date receiptDate) { this.receiptDate = receiptDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Receipt{" +
               "receiptID=" + receiptID +
               ", supplierID=" + supplierID +
               ", staffID=" + staffID +
               ", receiptDate=" + receiptDate +
               ", status='" + status + '\'' +
               '}';
    }
}
