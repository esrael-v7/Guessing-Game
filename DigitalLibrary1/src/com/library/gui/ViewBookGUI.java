package com.library.gui;

import com.library.operations.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewBookGUI extends JFrame {
    private JTable bookTable;

    public ViewBookGUI() {
        setTitle("View Books");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel and layout
        JPanel panel = new JPanel(new BorderLayout());
        add(panel);

        // Table setup
        bookTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(bookTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        loadBookData();
    }

    private void loadBookData() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"Book ID", "Book Name", "Title", "Genre", "Author ID", "Published Date", "Price"}, 0);
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM books")) {

            while (resultSet.next()) {
                int bookId = resultSet.getInt("bookId");
                String bookName = resultSet.getString("bookName");
                String title = resultSet.getString("title");
                String genre = resultSet.getString("genre");
                int authorId = resultSet.getInt("authorId");
                String publishedDate = resultSet.getDate("publishedDate").toString();
                double price = resultSet.getDouble("price");

                model.addRow(new Object[]{bookId, bookName, title, genre, authorId, publishedDate, price});
            }

            bookTable.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error fetching book data.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewBookGUI viewBookGUI = new ViewBookGUI();
            viewBookGUI.setVisible(true);
        });
    }
}
