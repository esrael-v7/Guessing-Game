package com.library.catalog;

public class Book {
    private int bookId;
    private String title;
    private String genre;
    private int authorId;

    public Book(int bookId, String title, String genre, int authorId) {
        this.bookId = bookId;
        this.title = title;
        this.genre = genre;
        this.authorId = authorId;
    }

    // Getters and Setters
    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public int getAuthorId() { return authorId; }
    public void setAuthorId(int authorId) { this.authorId = authorId; }
}
