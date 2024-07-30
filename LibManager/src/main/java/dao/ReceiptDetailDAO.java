package dao;

import model.ReceiptDetails;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceiptDetailDAO {
    private Connection connection;

    public ReceiptDetailDAO(Connection connection) {
        this.connection = connection;
    }

    public void addReceiptDetail(ReceiptDetails receiptDetail) throws SQLException {
        String sql = "INSERT INTO ReceiptDetails (ReceiptID, BookID, Price, Quantity, TotalAmount) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, receiptDetail.getReceiptID());
            ps.setInt(2, receiptDetail.getBookID());
            ps.setBigDecimal(3, receiptDetail.getPrice());
            ps.setInt(4, receiptDetail.getQuantity());
            ps.setBigDecimal(5, receiptDetail.getTotalAmount());
            ps.executeUpdate();
        }
    }

    public void updateReceiptDetail(ReceiptDetails receiptDetail) throws SQLException {
        String sql = "UPDATE ReceiptDetails SET ReceiptID = ?, BookID = ?, Price = ?, Quantity = ?, TotalAmount = ? WHERE ReceiptDetailID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, receiptDetail.getReceiptID());
            ps.setInt(2, receiptDetail.getBookID());
            ps.setBigDecimal(3, receiptDetail.getPrice());
            ps.setInt(4, receiptDetail.getQuantity());
            ps.setBigDecimal(5, receiptDetail.getTotalAmount());
            ps.setInt(6, receiptDetail.getReceiptDetailID());
            ps.executeUpdate();
        }
    }

    public void deleteReceiptDetail(int receiptDetailID) throws SQLException {
        String sql = "DELETE FROM ReceiptDetails WHERE ReceiptDetailID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, receiptDetailID);
            ps.executeUpdate();
        }
    }

    public ReceiptDetails getReceiptDetailById(int receiptDetailID) throws SQLException {
        String sql = "SELECT * FROM ReceiptDetails WHERE ReceiptDetailID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, receiptDetailID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new ReceiptDetails(
                    rs.getInt("ReceiptDetailID"),
                    rs.getInt("ReceiptID"),
                    rs.getInt("BookID"),
                    rs.getBigDecimal("Price"),
                    rs.getInt("Quantity"),
                    rs.getBigDecimal("TotalAmount")
                );
            }
            return null;
        }
    }

    public List<ReceiptDetails> getAllReceiptDetails() throws SQLException {
        List<ReceiptDetails> receiptDetails = new ArrayList<>();
        String sql = "SELECT * FROM ReceiptDetails";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                receiptDetails.add(new ReceiptDetails(
                    rs.getInt("ReceiptDetailID"),
                    rs.getInt("ReceiptID"),
                    rs.getInt("BookID"),
                    rs.getBigDecimal("Price"),
                    rs.getInt("Quantity"),
                    rs.getBigDecimal("TotalAmount")
                ));
            }
        }
        return receiptDetails;
    }
}
