package dao;

import model.Receipt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceiptDAO {
    private Connection connection;

    public ReceiptDAO(Connection connection) {
        this.connection = connection;
    }

    // Create
    public void addReceipt(Receipt receipt) throws SQLException {
        String sql = "INSERT INTO Receipt (SupplierID, StaffID, ReceiptDate, Status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, receipt.getSupplierID());
            pstmt.setInt(2, receipt.getStaffID());
            pstmt.setDate(3, receipt.getReceiptDate());
            pstmt.setString(4, receipt.getStatus());
            pstmt.executeUpdate();
        }
    }

    // Read
    public Receipt getReceiptById(int receiptID) throws SQLException {
        String sql = "SELECT * FROM Receipt WHERE ReceiptID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, receiptID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Receipt(
                        rs.getInt("ReceiptID"),
                        rs.getInt("SupplierID"),
                        rs.getInt("StaffID"),
                        rs.getDate("ReceiptDate"),
                        rs.getString("Status")
                    );
                }
            }
        }
        return null;
    }

    public List<Receipt> getAllReceipts() throws SQLException {
        List<Receipt> receipts = new ArrayList<>();
        String sql = "SELECT * FROM Receipt";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                receipts.add(new Receipt(
                    rs.getInt("ReceiptID"),
                    rs.getInt("SupplierID"),
                    rs.getInt("StaffID"),
                    rs.getDate("ReceiptDate"),
                    rs.getString("Status")
                ));
            }
        }
        return receipts;
    }

    // Update
    public void updateReceipt(Receipt receipt) throws SQLException {
        String sql = "UPDATE Receipt SET SupplierID = ?, StaffID = ?, ReceiptDate = ?, Status = ? WHERE ReceiptID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, receipt.getSupplierID());
            pstmt.setInt(2, receipt.getStaffID());
            pstmt.setDate(3, receipt.getReceiptDate());
            pstmt.setString(4, receipt.getStatus());
            pstmt.setInt(5, receipt.getReceiptID());
            pstmt.executeUpdate();
        }
    }

    // Delete
    public void deleteReceipt(int receiptID) throws SQLException {
        String sql = "DELETE FROM Receipt WHERE ReceiptID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, receiptID);
            pstmt.executeUpdate();
        }
    }
}
