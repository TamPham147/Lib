package model;

public class Account {
    private int accountID;
    private String username;
    private String password;
    private String role;
    private int readerID;
    private int libraryCardID;

    // Constructors
    public Account() {}

    public Account(int accountID, String username, String password, String role, int readerID, int libraryCardID) {
        this.accountID = accountID;
        this.username = username;
        this.password = password;
        this.role = role;
        this.readerID = readerID;
        this.libraryCardID = libraryCardID;
    }

    // Getters and Setters
    public int getAccountID() { return accountID; }
    public void setAccountID(int accountID) { this.accountID = accountID; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public int getReaderID() { return readerID; }
    public void setReaderID(int readerID) { this.readerID = readerID; }

    public int getLibraryCardID() { return libraryCardID; }
    public void setLibraryCardID(int libraryCardID) { this.libraryCardID = libraryCardID; }

    @Override
    public String toString() {
        return "Account{" +
               "accountID=" + accountID +
               ", username='" + username + '\'' +
               ", password='" + password + '\'' +
               ", role='" + role + '\'' +
               ", readerID=" + readerID +
               ", libraryCardID=" + libraryCardID +
               '}';
    }
}
