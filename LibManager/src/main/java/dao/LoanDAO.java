package dao;

import model.Loan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanDAO {
    private Connection connection;

    public LoanDAO(Connection connection) {
        this.connection = connection;
    }

    public void addLoan(Loan loan) throws SQLException {
        String sql = "INSERT INTO Loan (StaffID, ReaderID, LoanDate, Status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, loan.getStaffID());
            ps.setInt(2, loan.getReaderID());
            ps.setDate(3, loan.getLoanDate());
            ps.setString(4, loan.getStatus());
            ps.executeUpdate();
        }
    }

    public void updateLoan(Loan loan) throws SQLException {
        String sql = "UPDATE Loan SET StaffID = ?, ReaderID = ?, LoanDate = ?, Status = ? WHERE LoanID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, loan.getStaffID());
            ps.setInt(2, loan.getReaderID());
            ps.setDate(3, loan.getLoanDate());
            ps.setString(4, loan.getStatus());
            ps.setInt(5, loan.getLoanID());
            ps.executeUpdate();
        }
    }

    public void deleteLoan(int loanID) throws SQLException {
        String sql = "DELETE FROM Loan WHERE LoanID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, loanID);
            ps.executeUpdate();
        }
    }

    public Loan getLoanById(int loanID) throws SQLException {
        String sql = "SELECT * FROM Loan WHERE LoanID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, loanID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Loan(
                    rs.getInt("LoanID"),
                    rs.getInt("StaffID"),
                    rs.getInt("ReaderID"),
                    rs.getDate("LoanDate"),
                    rs.getString("Status")
                );
            }
            return null;
        }
    }

    public List<Loan> getAllLoans() throws SQLException {
        List<Loan> loans = new ArrayList<>();
        String sql = "SELECT * FROM Loan";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                loans.add(new Loan(
                    rs.getInt("LoanID"),
                    rs.getInt("StaffID"),
                    rs.getInt("ReaderID"),
                    rs.getDate("LoanDate"),
                    rs.getString("Status")
                ));
            }
        }
        return loans;
    }
}
