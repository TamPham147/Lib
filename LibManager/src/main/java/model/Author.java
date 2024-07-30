package model;

public class Author {
    private int authorID;
    private String authorName;
    private int birthYear;
    private String hometown;

    // Constructors
    public Author() {}

    public Author(int authorID, String authorName, int birthYear, String hometown) {
        this.authorID = authorID;
        this.authorName = authorName;
        this.birthYear = birthYear;
        this.hometown = hometown;
    }

    // Getters and Setters
    public int getAuthorID() { return authorID; }
    public void setAuthorID(int authorID) { this.authorID = authorID; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public int getBirthYear() { return birthYear; }
    public void setBirthYear(int birthYear) { this.birthYear = birthYear; }

    public String getHometown() { return hometown; }
    public void setHometown(String hometown) { this.hometown = hometown; }

    @Override
    public String toString() {
        return "Author{" +
               "authorID=" + authorID +
               ", authorName='" + authorName + '\'' +
               ", birthYear=" + birthYear +
               ", hometown='" + hometown + '\'' +
               '}';
    }
}
