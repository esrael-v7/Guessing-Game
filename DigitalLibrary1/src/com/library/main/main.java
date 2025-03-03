package com.library.main;

import com.library.catalog.*;
import com.library.memebrship.*;
import com.library.usermanagement.*;
import com.library.gui.AddUserGUI;
import com.library.gui.DeleteUserGUI;
import com.library.gui.ModifyUserGUI;
import com.library.gui.ViewUserGUI;
import com.library.gui.WelcomeScreen;
import com.library.gui.ViewBookGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main extends JFrame {
    private JButton addButton;
    private JButton viewButton;
    private JButton modifyButton;
    private JButton deleteButton;
    private JButton exitButton;
    private JLabel backgroundLabel;
    private ImageIcon backgroundImage;

    public main() {
        setTitle("Library System - Main Menu");
        setSize(800, 400); // Larger frame for horizontal layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Background image
        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        backgroundImage = new ImageIcon("C:\\Users\\hudso\\OneDrive\\Desktop\\image\\rt.jpg"); // Replace with image path
        backgroundLabel.setIcon(new ImageIcon(backgroundImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
        add(backgroundLabel);

        // Buttons
        addButton = createButton("Add");
        viewButton = createButton("View");
        modifyButton = createButton("Modify");
        deleteButton = createButton("Delete");
        exitButton = createButton("Exit");

        // Add buttons to the background
        backgroundLabel.setLayout(null); // Ensure buttons can be placed on the background
        backgroundLabel.add(addButton);
        backgroundLabel.add(viewButton);
        backgroundLabel.add(modifyButton);
        backgroundLabel.add(deleteButton);
        backgroundLabel.add(exitButton);

        // Button Actions
        addButton.addActionListener(e -> {
            AddUserGUI addUserGUI = new AddUserGUI();
            addUserGUI.setVisible(true);
        });

        viewButton.addActionListener(e -> {
            ViewUserGUI viewUsersGUI = new ViewUserGUI();
            viewUsersGUI.setVisible(true);
        });

        modifyButton.addActionListener(e -> {
            ModifyUserGUI modifyUserGUI = new ModifyUserGUI();
            modifyUserGUI.setVisible(true);
        });

        deleteButton.addActionListener(e -> {
            DeleteUserGUI deleteUserGUI = new DeleteUserGUI();
            deleteUserGUI.setVisible(true);
        });

        viewButton.addActionListener(e -> {
            ViewBookGUI viewbookGUI = new ViewBookGUI();
            viewbookGUI.setVisible(true);
        });

        exitButton.addActionListener(e -> System.exit(0));

        // Resize buttons and background dynamically
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                // Resize background image
                backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
                backgroundLabel.setIcon(new ImageIcon(backgroundImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));

                // Resize and reposition buttons
                int buttonWidth = getWidth() / 6;
                int buttonHeight = 40;
                int yPosition = getHeight() / 2;

                addButton.setBounds(buttonWidth - 50, yPosition, buttonWidth, buttonHeight);
                viewButton.setBounds(2 * buttonWidth - 50, yPosition, buttonWidth, buttonHeight);
                modifyButton.setBounds(3 * buttonWidth - 50, yPosition, buttonWidth, buttonHeight);
                deleteButton.setBounds(4 * buttonWidth - 50, yPosition, buttonWidth, buttonHeight);
                exitButton.setBounds(5 * buttonWidth - 50, yPosition, buttonWidth, buttonHeight);
            }
        });
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(Color.LIGHT_GRAY);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            main mainMenu = new main();
            mainMenu.setVisible(true);
        });
    }
}
