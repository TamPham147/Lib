package model;

import java.sql.Date;

public class LibraryCard {
    private int libraryCardID;
    private int readerID;
    private Date startDate;
    private Date endDate;
    private String note;

    // Constructors
    public LibraryCard() {}

    public LibraryCard(int libraryCardID, int readerID, Date startDate, Date endDate, String note) {
        this.libraryCardID = libraryCardID;
        this.readerID = readerID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.note = note;
    }

    // Getters and Setters
    public int getLibraryCardID() { return libraryCardID; }
    public void setLibraryCardID(int libraryCardID) { this.libraryCardID = libraryCardID; }

    public int getReaderID() { return readerID; }
    public void setReaderID(int readerID) { this.readerID = readerID; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    @Override
    public String toString() {
        return "LibraryCard{" +
               "libraryCardID=" + libraryCardID +
               ", readerID=" + readerID +
               ", startDate=" + startDate +
               ", endDate=" + endDate +
               ", note='" + note + '\'' +
               '}';
    }
}
