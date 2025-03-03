package com.library.operations;

import com.library.catalog.Author;
import com.library.catalog.Book;
import com.library.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LibrarySystem {

    // Add a book to the database
    public void addBook(Book book) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO books (title, genre, author_id) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getGenre());
            stmt.setInt(3, book.getAuthorId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View all books in the database
    public ArrayList<Book> viewBooks() {
        ArrayList<Book> books = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM books";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getInt("author_id")
                );
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }
}

