package model;

import java.sql.Date;

public class Staff {
    private int staffID;
    private String staffName;
    private int birthYear;
    private String gender;
    private String address;
    private String phoneNumber;
    private String status;
    private Date startDate;

    // Constructors
    public Staff() {}

    public Staff(int staffID, String staffName, int birthYear, String gender, String address, String phoneNumber, String status, Date startDate) {
        this.staffID = staffID;
        this.staffName = staffName;
        this.birthYear = birthYear;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.startDate = startDate;
    }

    // Getters and Setters
    public int getStaffID() { return staffID; }
    public void setStaffID(int staffID) { this.staffID = staffID; }

    public String getStaffName() { return staffName; }
    public void setStaffName(String staffName) { this.staffName = staffName; }

    public int getBirthYear() { return birthYear; }
    public void setBirthYear(int birthYear) { this.birthYear = birthYear; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    @Override
    public String toString() {
        return "Staff{" +
               "staffID=" + staffID +
               ", staffName='" + staffName + '\'' +
               ", birthYear=" + birthYear +
               ", gender='" + gender + '\'' +
               ", address='" + address + '\'' +
               ", phoneNumber='" + phoneNumber + '\'' +
               ", status='" + status + '\'' +
               ", startDate=" + startDate +
               '}';
    }
}
