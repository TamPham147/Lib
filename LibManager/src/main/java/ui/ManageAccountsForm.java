package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ManageAccountsForm extends JFrame {
    private JTable accountsTable;
    private DefaultTableModel tableModel;

    public ManageAccountsForm() {
        setTitle("Manage Accounts");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        setLayout(new BorderLayout());
        
        JPanel panel = new JPanel(new BorderLayout());
        
        String[] columnNames = {"Account ID", "Username", "Password", "Role"};
        tableModel = new DefaultTableModel(columnNames, 0);
        accountsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(accountsTable);
        
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
        
        loadAccounts();

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add account logic
                String username = JOptionPane.showInputDialog("Enter username:");
                String password = JOptionPane.showInputDialog("Enter password:");
                String role = JOptionPane.showInputDialog("Enter role (Admin/User):");
                
                try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://NGUYENTAM;databaseName=LibraryDB;user=sa;password=123456");
                     PreparedStatement stmt = conn.prepareStatement("INSERT INTO Accounts (Username, Password, Role) VALUES (?, ?, ?)")) {
                    stmt.setString(1, username);
                    stmt.setString(2, password);
                    stmt.setString(3, role);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Account added successfully!");
                    loadAccounts();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Edit account logic
                int selectedRow = accountsTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int accountId = (Integer) tableModel.getValueAt(selectedRow, 0);
                    String newUsername = JOptionPane.showInputDialog("Enter new username:");
                    String newPassword = JOptionPane.showInputDialog("Enter new password:");
                    String newRole = JOptionPane.showInputDialog("Enter new role (Admin/User):");
                    
                    try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://NGUYENTAM;databaseName=LibraryDB;user=sa;password=123456");
                         PreparedStatement stmt = conn.prepareStatement("UPDATE Accounts SET Username = ?, Password = ?, Role = ? WHERE AccountID = ?")) {
                        stmt.setString(1, newUsername);
                        stmt.setString(2, newPassword);
                        stmt.setString(3, newRole);
                        stmt.setInt(4, accountId);
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Account updated successfully!");
                        loadAccounts();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an account to edit.");
                }
            }
        });
        
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Delete account logic
                int selectedRow = accountsTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int accountId = (Integer) tableModel.getValueAt(selectedRow, 0);
                    
                    try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://NGUYENTAM;databaseName=LibraryDB;user=sa;password=123456");
                         PreparedStatement stmt = conn.prepareStatement("DELETE FROM Accounts WHERE AccountID = ?")) {
                        stmt.setInt(1, accountId);
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Account deleted successfully!");
                        loadAccounts();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an account to delete.");
                }
            }
        });
    }

    private void loadAccounts() {
        tableModel.setRowCount(0);
        try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://NGUYENTAM;databaseName=LibraryDB;user=sa;password=123456");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT AccountID, Username, Password, Role FROM Accounts")) {
            while (rs.next()) {
                int accountId = rs.getInt("AccountID");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String role = rs.getString("Role");
                tableModel.addRow(new Object[]{accountId, username, password, role});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
