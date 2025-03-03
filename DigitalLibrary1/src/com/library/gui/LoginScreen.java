package com.library.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginScreen extends JFrame {
    private JLabel backgroundLabel;
    private ImageIcon backgroundImage;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginScreen() {
        setTitle("Library System - Login");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Background image
        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        backgroundImage = new ImageIcon("C:\\Users\\hudso\\OneDrive\\Desktop\\image\\rt.jpg"); // Replace with image path
        backgroundLabel.setIcon(new ImageIcon(backgroundImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
        add(backgroundLabel);

        // Login form components
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(300, 120, 100, 30);
        usernameLabel.setForeground(Color.WHITE);

        usernameField = new JTextField();
        usernameField.setBounds(400, 120, 150, 30);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(300, 160, 100, 30);
        passwordLabel.setForeground(Color.WHITE);

        passwordField = new JPasswordField();
        passwordField.setBounds(400, 160, 150, 30);

        loginButton = new JButton("Login");
        loginButton.setBounds(400, 210, 150, 30);

        // Add components to the background
        backgroundLabel.setLayout(null); // Allow absolute positioning
        backgroundLabel.add(usernameLabel);
        backgroundLabel.add(usernameField);
        backgroundLabel.add(passwordLabel);
        backgroundLabel.add(passwordField);
        backgroundLabel.add(loginButton);

        // Login button action
        loginButton.addActionListener((ActionEvent e) -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (validateLogin(username, password)) {
                dispose(); // Close the login screen
                SwingUtilities.invokeLater(() -> {
                    WelcomeScreen welcomeScreen = new WelcomeScreen();
                    welcomeScreen.setVisible(true);
                });
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Resize background dynamically
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
                backgroundLabel.setIcon(new ImageIcon(backgroundImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
            }
        });
    }

    private boolean validateLogin(String username, String password) {
        // Replace with actual validation logic
        return username.equals("admin") && password.equals("password");
    }
}
