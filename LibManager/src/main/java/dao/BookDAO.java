package dao;

import model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private Connection connection;

    public BookDAO(Connection connection) {
        this.connection = connection;
    }

    public void addBook(Book book) throws SQLException {
        String sql = "INSERT INTO Book (BookTitle, CategoryID, PublisherID, AuthorID, PublishYear, Quantity, ShelfID, Image, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, book.getBookTitle());
            ps.setInt(2, book.getCategoryID());
            ps.setInt(3, book.getPublisherID());
            ps.setInt(4, book.getAuthorID());
            ps.setInt(5, book.getPublishYear());
            ps.setInt(6, book.getQuantity());
            ps.setInt(7, book.getShelfID());
            ps.setString(8, book.getImage());
            ps.setString(9, book.getStatus());
            ps.executeUpdate();
        }
    }

    public void updateBook(Book book) throws SQLException {
        String sql = "UPDATE Book SET BookTitle = ?, CategoryID = ?, PublisherID = ?, AuthorID = ?, PublishYear = ?, Quantity = ?, ShelfID = ?, Image = ?, Status = ? WHERE BookID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, book.getBookTitle());
            ps.setInt(2, book.getCategoryID());
            ps.setInt(3, book.getPublisherID());
            ps.setInt(4, book.getAuthorID());
            ps.setInt(5, book.getPublishYear());
            ps.setInt(6, book.getQuantity());
            ps.setInt(7, book.getShelfID());
            ps.setString(8, book.getImage());
            ps.setString(9, book.getStatus());
            ps.setInt(10, book.getBookID());
            ps.executeUpdate();
        }
    }

    public void deleteBook(int bookID) throws SQLException {
        String sql = "DELETE FROM Book WHERE BookID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, bookID);
            ps.executeUpdate();
        }
    }

    public Book getBookById(int bookID) throws SQLException {
        String sql = "SELECT * FROM Book WHERE BookID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, bookID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Book(
                    rs.getInt("BookID"),
                    rs.getString("BookTitle"),
                    rs.getInt("CategoryID"),
                    rs.getInt("PublisherID"),
                    rs.getInt("AuthorID"),
                    rs.getInt("PublishYear"),
                    rs.getInt("Quantity"),
                    rs.getInt("ShelfID"),
                    rs.getString("Image"),
                    rs.getString("Status")
                );
            }
            return null;
        }
    }

    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM Book";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                books.add(new Book(
                    rs.getInt("BookID"),
                    rs.getString("BookTitle"),
                    rs.getInt("CategoryID"),
                    rs.getInt("PublisherID"),
                    rs.getInt("AuthorID"),
                    rs.getInt("PublishYear"),
                    rs.getInt("Quantity"),
                    rs.getInt("ShelfID"),
                    rs.getString("Image"),
                    rs.getString("Status")
                ));
            }
        }
        return books;
    }
}
