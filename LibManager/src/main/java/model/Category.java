package model;

public class Category {
    private int categoryID;
    private String categoryName;
    private String status;

    // Constructors
    public Category() {}

    public Category(int categoryID, String categoryName, String status) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.status = status;
    }

    // Getters and Setters
    public int getCategoryID() { return categoryID; }
    public void setCategoryID(int categoryID) { this.categoryID = categoryID; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Category{" +
               "categoryID=" + categoryID +
               ", categoryName='" + categoryName + '\'' +
               ", status='" + status + '\'' +
               '}';
    }
}
