package model;

public class Bookshelf {
    private int shelfID;
    private String location;
    private String status;

    // Constructors
    public Bookshelf() {}

    public Bookshelf(int shelfID, String location, String status) {
        this.shelfID = shelfID;
        this.location = location;
        this.status = status;
    }

    // Getters and Setters
    public int getShelfID() { return shelfID; }
    public void setShelfID(int shelfID) { this.shelfID = shelfID; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Bookshelf{" +
               "shelfID=" + shelfID +
               ", location='" + location + '\'' +
               ", status='" + status + '\'' +
               '}';
    }
}
