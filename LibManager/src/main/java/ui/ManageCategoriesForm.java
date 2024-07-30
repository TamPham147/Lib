package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ManageCategoriesForm extends JFrame {
    private JTable categoryTable;
    private DefaultTableModel tableModel;

    public ManageCategoriesForm() {
        setTitle("Manage Categories");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        setLayout(new BorderLayout());
        
        JPanel panel = new JPanel(new BorderLayout());
        
        String[] columnNames = {"Category ID", "Name"};
        tableModel = new DefaultTableModel(columnNames, 0);
        categoryTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(categoryTable);
        
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
        
        loadCategories();

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add category logic
                String name = JOptionPane.showInputDialog("Enter category name:");
                
                try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://NGUYENTAM;databaseName=LibraryDB;user=sa;password=123456");
                     PreparedStatement stmt = conn.prepareStatement("INSERT INTO Category (CategoryName) VALUES (?)")) {
                    stmt.setString(1, name);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Category added successfully!");
                    loadCategories();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Edit category logic
                int selectedRow = categoryTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int categoryId = (Integer) tableModel.getValueAt(selectedRow, 0);
                    String newName = JOptionPane.showInputDialog("Enter new name:");
                    
                    try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://NGUYENTAM;databaseName=LibraryDB;user=sa;password=123456");
                         PreparedStatement stmt = conn.prepareStatement("UPDATE Category SET CategoryName = ? WHERE CategoryID = ?")) {
                        stmt.setString(1, newName);
                        stmt.setInt(2, categoryId);
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Category updated successfully!");
                        loadCategories();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a category to edit.");
                }
            }
        });
        
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Delete category logic
                int selectedRow = categoryTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int categoryId = (Integer) tableModel.getValueAt(selectedRow, 0);
                    
                    try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://NGUYENTAM;databaseName=LibraryDB;user=sa;password=123456");
                         PreparedStatement stmt = conn.prepareStatement("DELETE FROM Category WHERE CategoryID = ?")) {
                        stmt.setInt(1, categoryId);
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Category deleted successfully!");
                        loadCategories();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a category to delete.");
                }
            }
        });
    }

    private void loadCategories() {
        tableModel.setRowCount(0);
        try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://NGUYENTAM;databaseName=LibraryDB;user=sa;password=123456");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT CategoryID, CategoryName FROM Category")) {
            while (rs.next()) {
                int categoryId = rs.getInt("CategoryID");
                String name = rs.getString("CategoryName");
                tableModel.addRow(new Object[]{categoryId, name});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
