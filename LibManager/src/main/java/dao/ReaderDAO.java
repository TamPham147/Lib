package dao;

import model.Reader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderDAO {
    private Connection connection;

    public ReaderDAO(Connection connection) {
        this.connection = connection;
    }

    public void addReader(Reader reader) throws SQLException {
        String sql = "INSERT INTO Reader (ReaderName, Gender, PhoneNumber) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, reader.getReaderName());
            ps.setString(2, reader.getGender());
            ps.setString(3, reader.getPhoneNumber());
            ps.executeUpdate();
        }
    }

    public void updateReader(Reader reader) throws SQLException {
        String sql = "UPDATE Reader SET ReaderName = ?, Gender = ?, PhoneNumber = ? WHERE ReaderID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, reader.getReaderName());
            ps.setString(2, reader.getGender());
            ps.setString(3, reader.getPhoneNumber());
            ps.setInt(4, reader.getReaderID());
            ps.executeUpdate();
        }
    }

    public void deleteReader(int readerID) throws SQLException {
        String sql = "DELETE FROM Reader WHERE ReaderID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, readerID);
            ps.executeUpdate();
        }
    }

    public Reader getReaderById(int readerID) throws SQLException {
        String sql = "SELECT * FROM Reader WHERE ReaderID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, readerID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Reader(
                    rs.getInt("ReaderID"),
                    rs.getString("ReaderName"),
                    rs.getString("Gender"),
                    rs.getString("PhoneNumber")
                );
            }
            return null;
        }
    }

    public List<Reader> getAllReaders() throws SQLException {
        List<Reader> readers = new ArrayList<>();
        String sql = "SELECT * FROM Reader";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                readers.add(new Reader(
                    rs.getInt("ReaderID"),
                    rs.getString("ReaderName"),
                    rs.getString("Gender"),
                    rs.getString("PhoneNumber")
                ));
            }
        }
        return readers;
    }
}
