package com.library.gui;

import com.library.operations.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteUserGUI extends JFrame {
    private JTextField userIdField;
    private JButton deleteButton;

    public DeleteUserGUI() {
        setTitle("Delete User");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel userIdLabel = new JLabel("User ID:");
        userIdLabel.setBounds(50, 50, 100, 30);
        add(userIdLabel);

        userIdField = new JTextField();
        userIdField.setBounds(120, 50, 100, 30);
        add(userIdField);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(120, 100, 100, 30);
        add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUser();
            }
        });
    }

    private void deleteUser() {
        String userId = userIdField.getText();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE userId = ?")) {

            preparedStatement.setInt(1, Integer.parseInt(userId));

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "User deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "User ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error deleting user.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
