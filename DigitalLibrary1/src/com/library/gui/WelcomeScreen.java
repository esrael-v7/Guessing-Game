package com.library.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import com.library.main.main;

public class WelcomeScreen extends JFrame {
    private JLabel backgroundLabel;
    private JButton enterButton;

    public WelcomeScreen() {
        setTitle("Welcome to Library System");
        setSize(600, 400); // Set initial size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Background image
        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\hudso\\OneDrive\\Desktop\\image\\erere.jpg"); // the image path
        backgroundLabel.setIcon(new ImageIcon(backgroundImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
        add(backgroundLabel);

        // Welcome text
        JLabel welcomeText = new JLabel("Welcome to Library System", SwingConstants.CENTER);
        welcomeText.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeText.setForeground(Color.WHITE);
        welcomeText.setBounds(0, 50, getWidth(), 50);
        backgroundLabel.add(welcomeText);

        // Enter button
        enterButton = new JButton("Enter Menu");
        enterButton.setFont(new Font("Arial", Font.BOLD, 16));
        enterButton.setBounds(200, 200, 200, 40);
        enterButton.setBackground(Color.LIGHT_GRAY);
        backgroundLabel.add(enterButton);

        // Mouse listener for hover effect
        enterButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent e) {
                enterButton.setBackground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                enterButton.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                main main = new main();
                main.setVisible(true);
                dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });

        // Resize components with frame
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
                backgroundLabel.setIcon(new ImageIcon(backgroundImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
                welcomeText.setBounds(0, 50, getWidth(), 50);
                enterButton.setBounds(getWidth() / 2 - 100, getHeight() / 2 - 20, 200, 40);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WelcomeScreen welcomeScreen = new WelcomeScreen();
            welcomeScreen.setVisible(true);
        });
    }
}
