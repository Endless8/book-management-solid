package org.example.bookmanagement.borrowings;

import org.example.bookmanagement.formatter.MessageFormatter;
import org.example.bookmanagement.dao.Book;
import org.example.bookmanagement.dao.BorrowingImp;
import org.example.bookmanagement.dao.User;
import org.example.bookmanagement.repository.BookRepository;
import org.example.bookmanagement.repository.BorrowingRepository;
import org.example.bookmanagement.repository.UserRepository;

import java.util.List;

import static org.example.bookmanagement.constants.ErrorMessages.*;
import static org.example.bookmanagement.constants.InfoMessages.*;

public class BorrowingMakerImp implements BorrowingMaker {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BorrowingRepository borrowingRepository;
    private final MessageFormatter messageFormatter;
    private List<Book> borrowableBooks;
    private List<User> activeUsers;
    private int bookId;
    private String bookIsbn;
    private int userId;

    public BorrowingMakerImp(BookRepository bookRepository, UserRepository userRepository, BorrowingRepository borrowingRepository, MessageFormatter messageFormatter) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.borrowingRepository = borrowingRepository;
        this.messageFormatter = messageFormatter;
    }

    public String getBorrowableBooks() {
        borrowableBooks = bookRepository.getBorrowableBooks();
        return messageFormatter.formatList(borrowableBooks);
    }

    public void chooseBook(int bookIndex) {
        Book chosenBook = borrowableBooks.get(bookIndex - 1);
        bookId = chosenBook.getId();
        bookIsbn = chosenBook.getIsbn();
    }

    public String getActiveUsers() {
        activeUsers = userRepository.getAllUsers();
        return messageFormatter.formatList(activeUsers);
    }

    public void chooseUser(int userIndex) {
        userId = activeUsers.get(userIndex - 1).getId();
    }

    public String borrowBook() {
        try {
            borrowingRepository.save(new BorrowingImp(userId, bookId, bookIsbn));
            Book borrowedBook = bookRepository.getBookById(bookId);
            borrowedBook.setBorrowed(true);
            bookRepository.save(borrowedBook);
        } catch (Exception e) {
            return BORROWING_INSERT_ERROR;
        }

        return NEW_BORROWING_INSERTED;
    }
}
