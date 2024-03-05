package org.example.bookmanagement.borrowings;

public interface BorrowingReturner {

    String getBorrowedBooks();
    void chooseBook(int bookIndex);
    String returnBook();
}
