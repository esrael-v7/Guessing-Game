package com.library.gui;

import com.library.operations.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ModifyUserGUI extends JFrame {
    private JTextField userIdField, usernameField, emailField, passwordField;
    private JButton updateButton;

    public ModifyUserGUI() {
        setTitle("Modify User");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel userIdLabel = new JLabel("User ID:");
        userIdLabel.setBounds(50, 50, 100, 30);
        add(userIdLabel);

        userIdField = new JTextField();
        userIdField.setBounds(150, 50, 200, 30);
        add(userIdField);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 100, 100, 30);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 100, 200, 30);
        add(usernameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 150, 100, 30);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 150, 200, 30);
        add(emailField);

        updateButton = new JButton("Update");
        updateButton.setBounds(150, 200, 100, 30);
        add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyUser();
            }
        });
    }

    private void modifyUser() {
        String userId = userIdField.getText();
        String username = usernameField.getText();
        String email = emailField.getText();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE user SET username = ?, email = ? WHERE userId = ?")) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setInt(3, Integer.parseInt(userId));

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "User updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "User ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error updating user.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
