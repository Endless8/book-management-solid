package org.example.bookmanagement.borrowings;

import org.example.bookmanagement.formatter.MessageFormatter;
import org.example.bookmanagement.dao.Book;
import org.example.bookmanagement.repository.BookRepository;
import org.example.bookmanagement.repository.BorrowingRepository;

import java.util.List;

import static org.example.bookmanagement.constants.ErrorMessages.*;
import static org.example.bookmanagement.constants.InfoMessages.*;

public class BorrowingReturnerImp implements BorrowingReturner {

    private final BookRepository bookRepository;
    private final BorrowingRepository borrowingRepository;
    private final MessageFormatter messageFormatter;
    private List<Book> borrowedBooks;
    private int bookId;
    private String bookIsbn;

    public BorrowingReturnerImp(BookRepository bookRepository, BorrowingRepository borrowingRepository, MessageFormatter messageFormatter) {
        this.bookRepository = bookRepository;
        this.borrowingRepository = borrowingRepository;
        this.messageFormatter = messageFormatter;
    }

    public String getBorrowedBooks() {
        borrowedBooks = bookRepository.getBorrowedBooks();
        return messageFormatter.formatList(borrowedBooks);
    }

    public void chooseBook(int bookIndex) {
        Book chosenBook = borrowedBooks.get(bookIndex - 1);
        bookId = chosenBook.getId();
        bookIsbn = chosenBook.getIsbn();
    }

    public String returnBook() {
        try {
            borrowingRepository.deleteBorrowing(bookId, bookIsbn);
            Book borrowedBook = bookRepository.getBookById(bookId);
            borrowedBook.setBorrowed(false);
            bookRepository.save(borrowedBook);
        } catch (Exception e) {
            return BORROWING_RETURN_ERROR;
        }

        return BORROWING_RETURNED;
    }
}
