package com.library.gui;

import javax.swing.*;

public class LibraryGUI extends JFrame {
    public LibraryGUI() {
        setTitle("Library System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Launch LoginScreen first
        SwingUtilities.invokeLater(() -> {
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.setVisible(true);
        });

        // Optionally, hide or dispose LibraryGUI if it's not needed after Login
        this.setVisible(false);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryGUI libraryGUI = new LibraryGUI();
            libraryGUI.setVisible(true);
        });
    }
}
