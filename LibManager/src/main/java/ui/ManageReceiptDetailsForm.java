package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ManageReceiptDetailsForm extends JFrame {
    private JTable receiptDetailsTable;
    private DefaultTableModel tableModel;

    public ManageReceiptDetailsForm() {
        setTitle("Manage Receipt Details");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        setLayout(new BorderLayout());
        
        JPanel panel = new JPanel(new BorderLayout());
        
        String[] columnNames = {"Receipt ID", "Book ID", "Quantity", "Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        receiptDetailsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(receiptDetailsTable);
        
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
        
        loadReceiptDetails();

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add receipt detail logic
                String receiptId = JOptionPane.showInputDialog("Enter receipt ID:");
                String bookId = JOptionPane.showInputDialog("Enter book ID:");
                String quantity = JOptionPane.showInputDialog("Enter quantity:");
                String price = JOptionPane.showInputDialog("Enter price:");
                
                try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://NGUYENTAM;databaseName=LibraryDB;user=sa;password=123456");
                     PreparedStatement stmt = conn.prepareStatement("INSERT INTO ReceiptDetails (ReceiptID, BookID, Quantity, Price) VALUES (?, ?, ?, ?)")) {
                    stmt.setInt(1, Integer.parseInt(receiptId));
                    stmt.setInt(2, Integer.parseInt(bookId));
                    stmt.setInt(3, Integer.parseInt(quantity));
                    stmt.setDouble(4, Double.parseDouble(price));
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Receipt detail added successfully!");
                    loadReceiptDetails();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Edit receipt detail logic
                int selectedRow = receiptDetailsTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int receiptId = (Integer) tableModel.getValueAt(selectedRow, 0);
                    String newBookId = JOptionPane.showInputDialog("Enter new book ID:");
                    String newQuantity = JOptionPane.showInputDialog("Enter new quantity:");
                    String newPrice = JOptionPane.showInputDialog("Enter new price:");
                    
                    try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://NGUYENTAM;databaseName=LibraryDB;user=sa;password=123456");
                         PreparedStatement stmt = conn.prepareStatement("UPDATE ReceiptDetails SET BookID = ?, Quantity = ?, Price = ? WHERE ReceiptID = ?")) {
                        stmt.setInt(1, Integer.parseInt(newBookId));
                        stmt.setInt(2, Integer.parseInt(newQuantity));
                        stmt.setDouble(3, Double.parseDouble(newPrice));
                        stmt.setInt(4, receiptId);
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Receipt detail updated successfully!");
                        loadReceiptDetails();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a receipt detail to edit.");
                }
            }
        });
        
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Delete receipt detail logic
                int selectedRow = receiptDetailsTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int receiptId = (Integer) tableModel.getValueAt(selectedRow, 0);
                    
                    try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://NGUYENTAM;databaseName=LibraryDB;user=sa;password=123456");
                         PreparedStatement stmt = conn.prepareStatement("DELETE FROM ReceiptDetails WHERE ReceiptID = ?")) {
                        stmt.setInt(1, receiptId);
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Receipt detail deleted successfully!");
                        loadReceiptDetails();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a receipt detail to delete.");
                }
            }
        });
    }

    private void loadReceiptDetails() {
        tableModel.setRowCount(0);
        try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://NGUYENTAM;databaseName=LibraryDB;user=sa;password=123456");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT ReceiptID, BookID, Quantity, Price FROM ReceiptDetails")) {
            while (rs.next()) {
                int receiptId = rs.getInt("ReceiptID");
                int bookId = rs.getInt("BookID");
                int quantity = rs.getInt("Quantity");
                double price = rs.getDouble("Price");
                tableModel.addRow(new Object[]{receiptId, bookId, quantity, price});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
