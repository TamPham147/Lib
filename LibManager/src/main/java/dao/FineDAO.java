package dao;

import model.Fine;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FineDAO {
    private Connection connection;

    public FineDAO(Connection connection) {
        this.connection = connection;
    }

    public void addFine(Fine fine) throws SQLException {
        String sql = "INSERT INTO Fine (LoanID, Reason, Amount) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, fine.getLoanID());
            ps.setString(2, fine.getReason());
            ps.setBigDecimal(3, fine.getAmount());
            ps.executeUpdate();
        }
    }

    public void updateFine(Fine fine) throws SQLException {
        String sql = "UPDATE Fine SET LoanID = ?, Reason = ?, Amount = ? WHERE FineID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, fine.getLoanID());
            ps.setString(2, fine.getReason());
            ps.setBigDecimal(3, fine.getAmount());
            ps.setInt(4, fine.getFineID());
            ps.executeUpdate();
        }
    }

    public void deleteFine(int fineID) throws SQLException {
        String sql = "DELETE FROM Fine WHERE FineID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, fineID);
            ps.executeUpdate();
        }
    }

    public Fine getFineById(int fineID) throws SQLException {
        String sql = "SELECT * FROM Fine WHERE FineID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, fineID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Fine(
                    rs.getInt("FineID"),
                    rs.getInt("LoanID"),
                    rs.getString("Reason"),
                    rs.getBigDecimal("Amount")
                );
            }
            return null;
        }
    }

    public List<Fine> getAllFines() throws SQLException {
        List<Fine> fines = new ArrayList<>();
        String sql = "SELECT * FROM Fine";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                fines.add(new Fine(
                    rs.getInt("FineID"),
                    rs.getInt("LoanID"),
                    rs.getString("Reason"),
                    rs.getBigDecimal("Amount")
                ));
            }
        }
        return fines;
    }
}
