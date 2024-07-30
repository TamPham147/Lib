package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ManageAuthorsForm extends JFrame {
    private JTable authorTable;
    private DefaultTableModel tableModel;

    public ManageAuthorsForm() {
        setTitle("Manage Authors");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        setLayout(new BorderLayout());
        
        JPanel panel = new JPanel(new BorderLayout());
        
        String[] columnNames = {"Author ID", "Name", "Birth Year", "Hometown"};
        tableModel = new DefaultTableModel(columnNames, 0);
        authorTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(authorTable);
        
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
        
        loadAuthors();

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add author logic
                String name = JOptionPane.showInputDialog("Enter author name:");
                String birthYearStr = JOptionPane.showInputDialog("Enter birth year:");
                int birthYear = Integer.parseInt(birthYearStr);
                String hometown = JOptionPane.showInputDialog("Enter hometown:");
                
                try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://NGUYENTAM;databaseName=LibraryDB;user=sa;password=123456");
                     PreparedStatement stmt = conn.prepareStatement("INSERT INTO Author (AuthorName, BirthYear, Hometown) VALUES (?, ?, ?)")) {
                    stmt.setString(1, name);
                    stmt.setInt(2, birthYear);
                    stmt.setString(3, hometown);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Author added successfully!");
                    loadAuthors();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Edit author logic
                int selectedRow = authorTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int authorId = (Integer) tableModel.getValueAt(selectedRow, 0);
                    String newName = JOptionPane.showInputDialog("Enter new name:");
                    
                    try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://NGUYENTAM;databaseName=LibraryDB;user=sa;password=123456");
                         PreparedStatement stmt = conn.prepareStatement("UPDATE Author SET AuthorName = ? WHERE AuthorID = ?")) {
                        stmt.setString(1, newName);
                        stmt.setInt(2, authorId);
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Author updated successfully!");
                        loadAuthors();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an author to edit.");
                }
            }
        });
        
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Delete author logic
                int selectedRow = authorTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int authorId = (Integer) tableModel.getValueAt(selectedRow, 0);
                    
                    try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://NGUYENTAM;databaseName=LibraryDB;user=sa;password=123456");
                         PreparedStatement stmt = conn.prepareStatement("DELETE FROM Author WHERE AuthorID = ?")) {
                        stmt.setInt(1, authorId);
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Author deleted successfully!");
                        loadAuthors();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an author to delete.");
                }
            }
        });
    }

    private void loadAuthors() {
        tableModel.setRowCount(0);
        try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://NGUYENTAM;databaseName=LibraryDB;user=sa;password=123456");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT AuthorID, AuthorName, BirthYear, Hometown FROM Author")) {
            while (rs.next()) {
                int authorId = rs.getInt("AuthorID");
                String name = rs.getString("AuthorName");
                int birthYear = rs.getInt("BirthYear");
                String hometown = rs.getString("Hometown");
                tableModel.addRow(new Object[]{authorId, name, birthYear, hometown});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
