package ui;

import database.DatabaseConnection;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManageBooksForm extends JFrame {

    private JTextField txtBookTitle;
    private JComboBox<String> cbCategory;
    private JComboBox<String> cbPublisher;
    private JComboBox<String> cbAuthor;
    private JComboBox<String> cbShelf;
    private JTextField txtPublishYear;
    private JTextField txtQuantity;
    private JLabel lblImage;
    private JButton btnSelectImage;
    private JTextField txtSearch;
    private JButton btnSearch;
    private JTable tblBooks;
    private DefaultTableModel tblModel;
    private ImageIcon selectedImage;

    public ManageBooksForm() {
        setTitle("Manage Books");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create panel for book details
        JPanel panelDetails = new JPanel();
        panelDetails.setLayout(new BoxLayout(panelDetails, BoxLayout.Y_AXIS));
        panelDetails.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add components to panelDetails
        panelDetails.add(createLabelAndField("Book Title:", txtBookTitle = new JTextField(20)));
        panelDetails.add(createLabelAndComboBox("Category:", cbCategory = new JComboBox<>(new String[]{"Select", "Category 1", "Category 2"})));
        panelDetails.add(createLabelAndComboBox("Publisher:", cbPublisher = new JComboBox<>(new String[]{"Select", "Publisher 1", "Publisher 2"})));
        panelDetails.add(createLabelAndComboBox("Author:", cbAuthor = new JComboBox<>(new String[]{"Select", "Author 1", "Author 2"})));
        panelDetails.add(createLabelAndComboBox("Shelf:", cbShelf = new JComboBox<>(new String[]{"Select", "Shelf 1", "Shelf 2"})));
        panelDetails.add(createLabelAndField("Publish Year:", txtPublishYear = new JTextField(4)));
        panelDetails.add(createLabelAndField("Quantity:", txtQuantity = new JTextField(4)));

        // Create image panel
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout());
        btnSelectImage = new JButton("Select Image");
        lblImage = new JLabel();
        lblImage.setPreferredSize(new Dimension(150, 150));
        imagePanel.add(btnSelectImage);
        imagePanel.add(lblImage);

        // Add image panel to details panel
        panelDetails.add(imagePanel);

        // Add search panel
        JPanel panelSearch = new JPanel();
        panelSearch.setLayout(new FlowLayout());
        txtSearch = new JTextField(20);
        btnSearch = new JButton("Search");
        panelSearch.add(txtSearch);
        panelSearch.add(btnSearch);

        // Create table for showing books
        tblModel = new DefaultTableModel(new Object[]{"Title", "Category", "Publisher", "Author", "Shelf", "Year", "Quantity", "Image"}, 0);
        tblBooks = new JTable(tblModel);
        JScrollPane scrollPane = new JScrollPane(tblBooks);

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton btnAdd = new JButton("Add");
        JButton btnEdit = new JButton("Edit");
        JButton btnDelete = new JButton("Delete");
        JButton btnReload = new JButton("Reload");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnReload);

        // Add components to the frame
        add(panelDetails, BorderLayout.NORTH);
        add(panelSearch, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_END);

        // Add action listeners
        btnSelectImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "png", "jpg", "gif"));
                int result = fileChooser.showOpenDialog(ManageBooksForm.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    selectedImage = new ImageIcon(selectedFile.getAbsolutePath());
                    lblImage.setIcon(selectedImage);
                }
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle search logic here
                // For example, filter the table based on txtSearch.getText()
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle add logic here
                String bookTitle = txtBookTitle.getText();
                String category = (String) cbCategory.getSelectedItem();
                String publisher = (String) cbPublisher.getSelectedItem();
                String author = (String) cbAuthor.getSelectedItem();
                String shelf = (String) cbShelf.getSelectedItem();
                String publishYear = txtPublishYear.getText();
                String quantity = txtQuantity.getText();
                String image = selectedImage != null ? selectedImage.getDescription() : "No Image";

                // Add new book to table
                tblModel.addRow(new Object[]{bookTitle, category, publisher, author, shelf, publishYear, quantity, image});
                // Clear inputs
                clearInputs();
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle edit logic here
                int selectedRow = tblBooks.getSelectedRow();
                if (selectedRow != -1) {
                    tblModel.setValueAt(txtBookTitle.getText(), selectedRow, 0);
                    tblModel.setValueAt(cbCategory.getSelectedItem(), selectedRow, 1);
                    tblModel.setValueAt(cbPublisher.getSelectedItem(), selectedRow, 2);
                    tblModel.setValueAt(cbAuthor.getSelectedItem(), selectedRow, 3);
                    tblModel.setValueAt(cbShelf.getSelectedItem(), selectedRow, 4);
                    tblModel.setValueAt(txtPublishYear.getText(), selectedRow, 5);
                    tblModel.setValueAt(txtQuantity.getText(), selectedRow, 6);
                    tblModel.setValueAt(selectedImage != null ? selectedImage.getDescription() : "No Image", selectedRow, 7);
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle delete logic here
                int selectedRow = tblBooks.getSelectedRow();
                if (selectedRow != -1) {
                    tblModel.removeRow(selectedRow);
                }
            }
        });

        btnReload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle reload logic here
                loadDataFromDatabase();
            }
        });

        // Load data when form is initialized
        loadDataFromDatabase();
    }

    private JPanel createLabelAndField(String labelText, JTextField textField) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel(labelText));
        panel.add(textField);
        return panel;
    }

    private JPanel createLabelAndComboBox(String labelText, JComboBox<String> comboBox) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel(labelText));
        panel.add(comboBox);
        return panel;
    }

    private void clearInputs() {
        txtBookTitle.setText("");
        cbCategory.setSelectedIndex(0);
        cbPublisher.setSelectedIndex(0);
        cbAuthor.setSelectedIndex(0);
        cbShelf.setSelectedIndex(0);
        txtPublishYear.setText("");
        txtQuantity.setText("");
        lblImage.setIcon(null);
        selectedImage = null;
    }

    private void loadDataFromDatabase() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT Title, CategoryID, PublisherID, AuthorID, ShelfID, PublishYear, Quantity, ImagePath FROM Book";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                // Clear existing data
                tblModel.setRowCount(0);

                // Load data from ResultSet
                while (resultSet.next()) {
                    String title = resultSet.getString("Title");
                    String category = resultSet.getString("CategoryID");
                    String publisher = resultSet.getString("PublisherID");
                    String author = resultSet.getString("AuthorID");
                    String shelf = resultSet.getString("ShelfID");
                    String publishYear = resultSet.getString("PublishYear");
                    int quantity = resultSet.getInt("Quantity");
                    String imagePath = resultSet.getString("ImagePath");

                    tblModel.addRow(new Object[]{title, category, publisher, author, shelf, publishYear, quantity, imagePath});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load data from database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManageBooksForm().setVisible(true));
    }
}
