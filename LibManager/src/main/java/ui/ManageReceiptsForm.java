package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageReceiptsForm extends JFrame {
    public ManageReceiptsForm() {
        // Set up the frame
        setTitle("Manage Receipts");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Title Label
        JLabel titleLabel = new JLabel("Manage Receipts", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Dummy content panel
        JTextArea contentArea = new JTextArea();
        contentArea.setText("Content for managing receipts goes here.");
        add(contentArea, BorderLayout.CENTER);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(backButton, BorderLayout.SOUTH);
    }
}
