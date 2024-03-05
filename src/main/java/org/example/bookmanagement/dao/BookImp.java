package org.example.bookmanagement.dao;

public class BookImp extends RecordImp implements Book {

    private String title;
    private String author;
    private String isbn;
    private boolean isBorrowed;

    public BookImp() {
    }

    public BookImp(int id, String title, String author, String isbn, boolean isBorrowed) {
        super(id);
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isBorrowed = isBorrowed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    @Override
    public String toString() {
        return "Title: '" + title + '\'' +
                " Author: '" + author + '\'' +
                " ISBN: '" + isbn + '\'';
    }
}
