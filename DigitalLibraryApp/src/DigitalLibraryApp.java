import javax.swing.*;
import java.awt.*;

public class LibraryManagement {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu());
    }
}

class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Digital Library Management System");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        JButton btnManageBooks = new JButton("Manage Books");
        JButton btnManageAuthors = new JButton("Manage Authors");
        JButton btnManageViewers = new JButton("Manage Viewers");
        JButton btnManageAdmins = new JButton("Manage Admins");

        btnManageBooks.addActionListener(e -> new BookManager());
        btnManageAuthors.addActionListener(e -> new AuthorManager());
        btnManageViewers.addActionListener(e -> new ViewerManager());
        btnManageAdmins.addActionListener(e -> new AdminManager());

        add(btnManageBooks);
        add(btnManageAuthors);
        add(btnManageViewers);
        add(btnManageAdmins);

        setVisible(true);
    }
}

class BookManager extends JFrame {
    public BookManager() {
        setTitle("Manage Books");
        setSize(800, 600);
        setLayout(new BorderLayout());
        
        JPanel panel = new JPanel(new GridLayout(0, 2));
        JLabel lblTitle = new JLabel("Title:");
        JTextField txtTitle = new JTextField();
        JLabel lblAuthor = new JLabel("Author:");
        JTextField txtAuthor = new JTextField();
        JLabel lblCategory = new JLabel("Category:");
        JTextField txtCategory = new JTextField();
        JLabel lblYear = new JLabel("Year of Publish:");
        JTextField txtYear = new JTextField();
        JButton btnInsert = new JButton("Insert");
        JButton btnDisplay = new JButton("Display All Books");

        panel.add(lblTitle);
        panel.add(txtTitle);
        panel.add(lblAuthor);
        panel.add(txtAuthor);
        panel.add(lblCategory);
        panel.add(txtCategory);
        panel.add(lblYear);
        panel.add(txtYear);
        panel.add(btnInsert);
        panel.add(btnDisplay);

        add(panel, BorderLayout.CENTER);

        JTextArea displayArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.SOUTH);

        btnInsert.addActionListener(e -> JOptionPane.showMessageDialog(this, "Book Insert functionality not implemented!"));
        btnDisplay.addActionListener(e -> displayArea.setText("Display All Books functionality not implemented!"));

        setVisible(true);
    }
}

class AuthorManager extends JFrame {
    public AuthorManager() {
        setTitle("Manage Authors");
        setSize(800, 600);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(0, 2));
        JLabel lblName = new JLabel("Name:");
        JTextField txtName = new JTextField();
        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();
        JButton btnInsert = new JButton("Insert");
        JButton btnDisplay = new JButton("Display All Authors");

        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(btnInsert);
        panel.add(btnDisplay);

        add(panel, BorderLayout.CENTER);

        JTextArea displayArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.SOUTH);

        btnInsert.addActionListener(e -> JOptionPane.showMessageDialog(this, "Author Insert functionality not implemented!"));
        btnDisplay.addActionListener(e -> displayArea.setText("Display All Authors functionality not implemented!"));

        setVisible(true);
    }
}

class ViewerManager extends JFrame {
    public ViewerManager() {
        setTitle("Manage Viewers");
        setSize(800, 600);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(0, 2));
        JLabel lblName = new JLabel("Name:");
        JTextField txtName = new JTextField();
        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();
        JButton btnInsert = new JButton("Insert");
        JButton btnDisplay = new JButton("Display All Viewers");

        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(btnInsert);
        panel.add(btnDisplay);

        add(panel, BorderLayout.CENTER);

        JTextArea displayArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.SOUTH);

        btnInsert.addActionListener(e -> JOptionPane.showMessageDialog(this, "Viewer Insert functionality not implemented!"));
        btnDisplay.addActionListener(e -> displayArea.setText("Display All Viewers functionality not implemented!"));

        setVisible(true);
    }
}

class AdminManager extends JFrame {
    public AdminManager() {
        setTitle("Manage Admins");
        setSize(800, 600);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(0, 2));
        JLabel lblUsername = new JLabel("Username:");
        JTextField txtUsername = new JTextField();
        JLabel lblPassword = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField();
        JButton btnInsert = new JButton("Insert");
        JButton btnDisplay = new JButton("Display All Admins");

        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnInsert);
        panel.add(btnDisplay);

        add(panel, BorderLayout.CENTER);

        JTextArea displayArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.SOUTH);

        btnInsert.addActionListener(e -> JOptionPane.showMessageDialog(this, "Admin Insert functionality not implemented!"));
        btnDisplay.addActionListener(e -> displayArea.setText("Display All Admins functionality not implemented!"));

        setVisible(true);
    }
}
