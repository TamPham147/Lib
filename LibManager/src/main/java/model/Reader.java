package model;

public class Reader {
    private int readerID;
    private String readerName;
    private String gender;
    private String phoneNumber;

    // Constructors
    public Reader() {}

    public Reader(int readerID, String readerName, String gender, String phoneNumber) {
        this.readerID = readerID;
        this.readerName = readerName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public int getReaderID() { return readerID; }
    public void setReaderID(int readerID) { this.readerID = readerID; }

    public String getReaderName() { return readerName; }
    public void setReaderName(String readerName) { this.readerName = readerName; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    @Override
    public String toString() {
        return "Reader{" +
               "readerID=" + readerID +
               ", readerName='" + readerName + '\'' +
               ", gender='" + gender + '\'' +
               ", phoneNumber='" + phoneNumber + '\'' +
               '}';
    }
}
