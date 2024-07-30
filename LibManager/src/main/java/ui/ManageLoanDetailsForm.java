package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ManageLoanDetailsForm extends JFrame {
    private JTable loanDetailsTable;
    private DefaultTableModel tableModel;

    public ManageLoanDetailsForm() {
        setTitle("Manage Loan Details");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        setLayout(new BorderLayout());
        
        JPanel panel = new JPanel(new BorderLayout());
        
        String[] columnNames = {"Loan ID", "Book ID", "Reader ID", "Loan Date", "Return Date"};
        tableModel = new DefaultTableModel(columnNames, 0);
        loanDetailsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(loanDetailsTable);
        
        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("Add");
        JButton btnEdit = new JButton("Edit");
        JButton btnDelete = new JButton("Delete");
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(panel);
        
        loadLoanDetails();

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add loan detail logic
                String bookId = JOptionPane.showInputDialog("Enter book ID:");
                String readerId = JOptionPane.showInputDialog("Enter reader ID:");
                String loanDate = JOptionPane.showInputDialog("Enter loan date (yyyy-mm-dd):");
                String returnDate = JOptionPane.showInputDialog("Enter return date (yyyy-mm-dd):");
                
                try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://NGUYENTAM;databaseName=LibraryDB;user=sa;password=123456");
                     PreparedStatement stmt = conn.prepareStatement("INSERT INTO LoanDetails (BookID, ReaderID, LoanDate, ReturnDate) VALUES (?, ?, ?, ?)")) {
                    stmt.setInt(1, Integer.parseInt(bookId));
                    stmt.setInt(2, Integer.parseInt(readerId));
                    stmt.setDate(3, Date.valueOf(loanDate));
                    stmt.setDate(4, Date.valueOf(returnDate));
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Loan detail added successfully!");
                    loadLoanDetails();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Edit loan detail logic
                int selectedRow = loanDetailsTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int loanId = (Integer) tableModel.getValueAt(selectedRow, 0);
                    String newBookId = JOptionPane.showInputDialog("Enter new book ID:");
                    String newReaderId = JOptionPane.showInputDialog("Enter new reader ID:");
                    String newLoanDate = JOptionPane.showInputDialog("Enter new loan date (yyyy-mm-dd):");
                    String newReturnDate = JOptionPane.showInputDialog("Enter new return date (yyyy-mm-dd):");
                    
                    try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://NGUYENTAM;databaseName=LibraryDB;user=sa;password=123456");
                         PreparedStatement stmt = conn.prepareStatement("UPDATE LoanDetails SET BookID = ?, ReaderID = ?, LoanDate = ?, ReturnDate = ? WHERE LoanID = ?")) {
                        stmt.setInt(1, Integer.parseInt(newBookId));
                        stmt.setInt(2, Integer.parseInt(newReaderId));
                        stmt.setDate(3, Date.valueOf(newLoanDate));
                        stmt.setDate(4, Date.valueOf(newReturnDate));
                        stmt.setInt(5, loanId);
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Loan detail updated successfully!");
                        loadLoanDetails();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a loan detail to edit.");
                }
            }
        });
        
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Delete loan detail logic
                int selectedRow = loanDetailsTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int loanId = (Integer) tableModel.getValueAt(selectedRow, 0);
                    
                    try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://NGUYENTAM;databaseName=LibraryDB;user=sa;password=123456");
                         PreparedStatement stmt = conn.prepareStatement("DELETE FROM LoanDetails WHERE LoanID = ?")) {
                        stmt.setInt(1, loanId);
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Loan detail deleted successfully!");
                        loadLoanDetails();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a loan detail to delete.");
                }
            }
        });
    }

    private void loadLoanDetails() {
        tableModel.setRowCount(0);
        try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://NGUYENTAM;databaseName=LibraryDB;user=sa;password=123456");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT LoanID, BookID, ReaderID, LoanDate, ReturnDate FROM LoanDetails")) {
            while (rs.next()) {
                int loanId = rs.getInt("LoanID");
                int bookId = rs.getInt("BookID");
                int readerId = rs.getInt("ReaderID");
                Date loanDate = rs.getDate("LoanDate");
                Date returnDate = rs.getDate("ReturnDate");
                tableModel.addRow(new Object[]{loanId, bookId, readerId, loanDate, returnDate});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
