package dao;

import model.Bookshelf;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookshelfDAO {
    private Connection connection;

    public BookshelfDAO(Connection connection) {
        this.connection = connection;
    }

    public void addBookshelf(Bookshelf bookshelf) throws SQLException {
        String sql = "INSERT INTO Bookshelf (Location, Status) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, bookshelf.getLocation());
            ps.setString(2, bookshelf.getStatus());
            ps.executeUpdate();
        }
    }

    public void updateBookshelf(Bookshelf bookshelf) throws SQLException {
        String sql = "UPDATE Bookshelf SET Location = ?, Status = ? WHERE ShelfID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, bookshelf.getLocation());
            ps.setString(2, bookshelf.getStatus());
            ps.setInt(3, bookshelf.getShelfID());
            ps.executeUpdate();
        }
    }

    public void deleteBookshelf(int shelfID) throws SQLException {
        String sql = "DELETE FROM Bookshelf WHERE ShelfID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, shelfID);
            ps.executeUpdate();
        }
    }

    public Bookshelf getBookshelfById(int shelfID) throws SQLException {
        String sql = "SELECT * FROM Bookshelf WHERE ShelfID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, shelfID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Bookshelf(
                    rs.getInt("ShelfID"),
                    rs.getString("Location"),
                    rs.getString("Status")
                );
            }
            return null;
        }
    }

    public List<Bookshelf> getAllBookshelves() throws SQLException {
        List<Bookshelf> bookshelves = new ArrayList<>();
        String sql = "SELECT * FROM Bookshelf";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                bookshelves.add(new Bookshelf(
                    rs.getInt("ShelfID"),
                    rs.getString("Location"),
                    rs.getString("Status")
                ));
            }
        }
        return bookshelves;
    }
}
