package model;

public class Supplier {
    private int supplierID;
    private String supplierName;

    // Constructors
    public Supplier() {}

    public Supplier(int supplierID, String supplierName) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
    }

    // Getters and Setters
    public int getSupplierID() { return supplierID; }
    public void setSupplierID(int supplierID) { this.supplierID = supplierID; }

    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }

    @Override
    public String toString() {
        return "Supplier{" +
               "supplierID=" + supplierID +
               ", supplierName='" + supplierName + '\'' +
               '}';
    }
}
