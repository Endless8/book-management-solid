package org.example.bookmanagement.borrowings;

public interface BorrowingMaker {

    String getBorrowableBooks();
    void chooseBook(int bookIndex);
    String getActiveUsers();
    void chooseUser(int userIndex);
    String borrowBook();
}
