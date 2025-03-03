package com.library.gui;

import com.library.operations.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddUserGUI extends JFrame {
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton submitButton;

    public AddUserGUI() {
        setTitle("Add User");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window, not the entire application
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 50, 100, 30);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 50, 200, 30);
        panel.add(usernameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 100, 100, 30);
        panel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 100, 200, 30);
        panel.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 150, 100, 30);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 150, 200, 30);
        panel.add(passwordField);

        submitButton = new JButton("Submit");
        submitButton.setBounds(150, 200, 100, 30);
        panel.add(submitButton);

        add(panel);

        // Add action listener to handle adding a user
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUserToDatabase();
            }
        });
    }

    private void addUserToDatabase() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO users (username, email, password) VALUES (?, ?, ?)")) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "User added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Close the Add User window
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add user.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
