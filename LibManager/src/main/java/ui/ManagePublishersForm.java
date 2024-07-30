package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ManagePublishersForm extends JFrame {
    private JTable publisherTable;
    private DefaultTableModel tableModel;

    public ManagePublishersForm() {
        setTitle("Manage Publishers");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        setLayout(new BorderLayout());
        
        JPanel panel = new JPanel(new BorderLayout());
        
        String[] columnNames = {"Publisher ID", "Name", "Address"};
        tableModel = new DefaultTableModel(columnNames, 0);
        publisherTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(publisherTable);
        
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
        
        loadPublishers();

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add publisher logic
                String name = JOptionPane.showInputDialog("Enter publisher name:");
                String address = JOptionPane.showInputDialog("Enter address:");
                
                try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://NGUYENTAM;databaseName=LibraryDB;user=sa;password=123456");
                     PreparedStatement stmt = conn.prepareStatement("INSERT INTO Publisher (PublisherName, Address) VALUES (?, ?)")) {
                    stmt.setString(1, name);
                    stmt.setString(2, address);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Publisher added successfully!");
                    loadPublishers();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Edit publisher logic
                int selectedRow = publisherTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int publisherId = (Integer) tableModel.getValueAt(selectedRow, 0);
                    String newName = JOptionPane.showInputDialog("Enter new name:");
                    String newAddress = JOptionPane.showInputDialog("Enter new address:");
                    
                    try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://NGUYENTAM;databaseName=LibraryDB;user=sa;password=123456");
                         PreparedStatement stmt = conn.prepareStatement("UPDATE Publisher SET PublisherName = ?, Address = ? WHERE PublisherID = ?")) {
                        stmt.setString(1, newName);
                        stmt.setString(2, newAddress);
                        stmt.setInt(3, publisherId);
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Publisher updated successfully!");
                        loadPublishers();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a publisher to edit.");
                }
            }
        });
        
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Delete publisher logic
                int selectedRow = publisherTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int publisherId = (Integer) tableModel.getValueAt(selectedRow, 0);
                    
                    try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://NGUYENTAM;databaseName=LibraryDB;user=sa;password=123456");
                         PreparedStatement stmt = conn.prepareStatement("DELETE FROM Publisher WHERE PublisherID = ?")) {
                        stmt.setInt(1, publisherId);
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Publisher deleted successfully!");
                        loadPublishers();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a publisher to delete.");
                }
            }
        });
    }

    private void loadPublishers() {
        tableModel.setRowCount(0);
        try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://NGUYENTAM;databaseName=LibraryDB;user=sa;password=123456");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT PublisherID, PublisherName, Address FROM Publisher")) {
            while (rs.next()) {
                int publisherId = rs.getInt("PublisherID");
                String name = rs.getString("PublisherName");
                String address = rs.getString("Address");
                tableModel.addRow(new Object[]{publisherId, name, address});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
