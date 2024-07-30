package model;

public class Publisher {
    private int publisherID;
    private String publisherName;
    private String address;
    private String phoneNumber;

    // Constructors
    public Publisher() {}

    public Publisher(int publisherID, String publisherName, String address, String phoneNumber) {
        this.publisherID = publisherID;
        this.publisherName = publisherName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public int getPublisherID() { return publisherID; }
    public void setPublisherID(int publisherID) { this.publisherID = publisherID; }

    public String getPublisherName() { return publisherName; }
    public void setPublisherName(String publisherName) { this.publisherName = publisherName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    @Override
    public String toString() {
        return "Publisher{" +
               "publisherID=" + publisherID +
               ", publisherName='" + publisherName + '\'' +
               ", address='" + address + '\'' +
               ", phoneNumber='" + phoneNumber + '\'' +
               '}';
    }
}
