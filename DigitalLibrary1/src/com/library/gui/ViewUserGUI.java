package com.library.gui;

import com.library.operations.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewUserGUI extends JFrame {
    private JTable userTable;

    public ViewUserGUI() {
        setTitle("View Users");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel and layout
        JPanel panel = new JPanel(new BorderLayout());
        add(panel);

        // Table setup
        userTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(userTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        loadUserData();
    }

    private void loadUserData() {
        // Create table model with column names
        DefaultTableModel model = new DefaultTableModel(new String[]{"User ID", "Username", "Email"}, 0);

        // Fetch data from the database
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT userId, username, email FROM user")) {

            // Populate the table with data
            while (resultSet.next()) {
                int userId = resultSet.getInt("userId");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                model.addRow(new Object[]{userId, username, email});
            }

            // Set the table model
            userTable.setModel(model);
        } catch (Exception e) {
            // Handle errors and show error message
            JOptionPane.showMessageDialog(this, "Error fetching user data. Please check the database connection.", 
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewUserGUI viewUserGUI = new ViewUserGUI();
            viewUserGUI.setVisible(true);
        });
    }
}
