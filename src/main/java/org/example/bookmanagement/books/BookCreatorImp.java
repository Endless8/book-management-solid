package org.example.bookmanagement.books;

import org.example.bookmanagement.constants.ErrorMessages;
import org.example.bookmanagement.constants.InfoMessages;
import org.example.bookmanagement.dao.Book;
import org.example.bookmanagement.repository.BookRepository;

public class BookCreatorImp implements BookCreator {

    private final BookFactory bookFactory;
    private final BookRepository bookRepository;

    public BookCreatorImp(BookFactory bookFactory, BookRepository bookRepository) {
        this.bookFactory = bookFactory;
        this.bookRepository = bookRepository;
    }

    @Override
    public String insertNewBook(String title, String author, String isbn) {
        Book newBook = bookFactory.createBook(title, author, isbn);

        try {
            bookRepository.save(newBook);
        } catch (Exception e) {
            return ErrorMessages.BOOK_INSERT_ERROR;
        }

        return InfoMessages.NEW_BOOK_INSERTED;
    }
}
