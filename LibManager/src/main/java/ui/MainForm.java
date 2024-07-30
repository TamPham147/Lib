package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MainForm extends JFrame {

    private Image image;
    private Point imageLocation = new Point(100, 100);
    private Point mousePressLocation;

    public MainForm() {
        setTitle("Library Management System");
        setSize(921, 637);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        // Panel cho các button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1, 10, 10));
        buttonPanel.setPreferredSize(new Dimension(200, getHeight()));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tạo các button
        JButton btnManageBooks = new JButton("Manage Books");
        JButton btnManageAuthors = new JButton("Manage Authors");
        JButton btnManageCategories = new JButton("Manage Categories");
        JButton btnManagePublishers = new JButton("Manage Publishers");
        JButton btnManageSuppliers = new JButton("Manage Suppliers");
        JButton btnManageStaff = new JButton("Manage Staff");
        JButton btnManageReaders = new JButton("Manage Readers");
        JButton btnManageLoans = new JButton("Manage Loans");
        JButton btnManageReceipts = new JButton("Manage Receipts");
        JButton btnManageLoanDetails = new JButton("Manage Loan Details");
        JButton btnManageReceiptDetails = new JButton("Manage Receipt Details");
        JButton btnManageFines = new JButton("Manage Fines");
        JButton btnManageLibraryCards = new JButton("Manage Library Cards");
        JButton btnManageAccounts = new JButton("Manage Accounts");

        buttonPanel.add(btnManageBooks);
        buttonPanel.add(btnManageAuthors);
        buttonPanel.add(btnManageCategories);
        buttonPanel.add(btnManagePublishers);
        buttonPanel.add(btnManageSuppliers);
        buttonPanel.add(btnManageStaff);
        buttonPanel.add(btnManageReaders);
        buttonPanel.add(btnManageLoans);
        buttonPanel.add(btnManageReceipts);
        buttonPanel.add(btnManageLoanDetails);
        buttonPanel.add(btnManageReceiptDetails);
        buttonPanel.add(btnManageFines);
        buttonPanel.add(btnManageLibraryCards);
        buttonPanel.add(btnManageAccounts);

        // Panel cho khu vực chào mừng và hình ảnh
        JPanel centerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image != null) {
                    g.drawImage(image, imageLocation.x, imageLocation.y, this);
                }
            }
        };
        centerPanel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome to Aptech Library System", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setBounds(41, 20, 629, 40);
        centerPanel.add(welcomeLabel);

        // Set image
        setImage("C:\\Users\\ASUS\\Downloads\\logo.png");

        // Add mouse listener for dragging image
        centerPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePressLocation = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mousePressLocation = null;
            }
        });

        centerPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (mousePressLocation != null) {
                    int dx = e.getX() - mousePressLocation.x;
                    int dy = e.getY() - mousePressLocation.y;
                    imageLocation.translate(dx, dy);
                    mousePressLocation = e.getPoint();
                    centerPanel.repaint();
                }
            }
        });

        getContentPane().add(buttonPanel, BorderLayout.WEST);
        getContentPane().add(centerPanel, BorderLayout.CENTER);

        // Thêm hành động cho các button
        btnManageBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageBooksForm().setVisible(true);
            }
        });

        btnManageAuthors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageAuthorsForm().setVisible(true);
            }
        });

        btnManageCategories.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageCategoriesForm().setVisible(true);
            }
        });

        btnManagePublishers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManagePublishersForm().setVisible(true);
            }
        });

        btnManageSuppliers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageSuppliersForm().setVisible(true);
            }
        });

        btnManageStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageStaffForm().setVisible(true);
            }
        });

        btnManageReaders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageReadersForm().setVisible(true);
            }
        });

        btnManageLoans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageLoansForm().setVisible(true);
            }
        });

        btnManageReceipts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageReceiptsForm().setVisible(true);
            }
        });

        btnManageLoanDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageLoanDetailsForm().setVisible(true);
            }
        });

        btnManageReceiptDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageReceiptDetailsForm().setVisible(true);
            }
        });

        btnManageFines.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageFinesForm().setVisible(true);
            }
        });

        btnManageLibraryCards.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageLibraryCardsForm().setVisible(true);
            }
        });

        btnManageAccounts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageAccountsForm().setVisible(true);
            }
        });

        setVisible(true);
    }

    private void setImage(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        image = icon.getImage();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainForm());
    }
}
