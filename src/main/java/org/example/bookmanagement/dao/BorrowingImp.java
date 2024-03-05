package org.example.bookmanagement.dao;

public class BorrowingImp implements Borrowing {

    private int userId;
    private int bookId;
    private String isbn;

    public BorrowingImp() {
    }

    public BorrowingImp(int userId, int bookId, String isbn) {
        this.userId = userId;
        this.bookId = bookId;
        this.isbn = isbn;
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int getBookId() {
        return bookId;
    }

    @Override
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
