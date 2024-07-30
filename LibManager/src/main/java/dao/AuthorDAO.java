package dao;

import model.Author;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {
    private Connection connection;

    public AuthorDAO(Connection connection) {
        this.connection = connection;
    }

    public void addAuthor(Author author) throws SQLException {
        String sql = "INSERT INTO Author (AuthorName, BirthYear, Hometown) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, author.getAuthorName());
            ps.setInt(2, author.getBirthYear());
            ps.setString(3, author.getHometown());
            ps.executeUpdate();
        }
    }

    public void updateAuthor(Author author) throws SQLException {
        String sql = "UPDATE Author SET AuthorName = ?, BirthYear = ?, Hometown = ? WHERE AuthorID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, author.getAuthorName());
            ps.setInt(2, author.getBirthYear());
            ps.setString(3, author.getHometown());
            ps.setInt(4, author.getAuthorID());
            ps.executeUpdate();
        }
    }

    public void deleteAuthor(int authorID) throws SQLException {
        String sql = "DELETE FROM Author WHERE AuthorID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, authorID);
            ps.executeUpdate();
        }
    }

    public Author getAuthorById(int authorID) throws SQLException {
        String sql = "SELECT * FROM Author WHERE AuthorID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, authorID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Author(
                    rs.getInt("AuthorID"),
                    rs.getString("AuthorName"),
                    rs.getInt("BirthYear"),
                    rs.getString("Hometown")
                );
            }
            return null;
        }
    }

    public List<Author> getAllAuthors() throws SQLException {
        List<Author> authors = new ArrayList<>();
        String sql = "SELECT * FROM Author";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                authors.add(new Author(
                    rs.getInt("AuthorID"),
                    rs.getString("AuthorName"),
                    rs.getInt("BirthYear"),
                    rs.getString("Hometown")
                ));
            }
        }
        return authors;
    }
}
