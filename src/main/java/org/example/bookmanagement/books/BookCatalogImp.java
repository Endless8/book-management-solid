package org.example.bookmanagement.books;

import org.example.bookmanagement.formatter.MessageFormatter;
import org.example.bookmanagement.dao.Book;
import org.example.bookmanagement.repository.BookRepository;

import java.util.List;

import static org.example.bookmanagement.constants.ErrorMessages.*;

public class BookCatalogImp implements BookCatalog {

    private final BookRepository bookRepository;
    private final MessageFormatter messageFormatter;

    public BookCatalogImp(BookRepository bookRepository, MessageFormatter messageFormatter) {
        this.bookRepository = bookRepository;
        this.messageFormatter = messageFormatter;
    }

    public String viewAllBooks() {
        List<Book> books = bookRepository.getAllBooks();

        return books.isEmpty() ?
                NO_BOOKS_FOUND :
                messageFormatter.formatList(books);
    }

    public String viewBookByTitle(String title) {
        List<Book> books = bookRepository.getByTitle(title);

        return books.isEmpty() ?
                messageFormatter.formatErrorMessage(BOOK_NOT_FOUND_FOR_TITLE, title) :
                messageFormatter.formatList(books);
    }

    public String viewBooksByAuthor(String author) {
        List<Book> books = bookRepository.getByAuthor(author);

        return books.isEmpty() ?
                messageFormatter.formatErrorMessage(BOOK_NOT_FOUND_FOR_AUTHOR, author) :
                messageFormatter.formatList(books);
    }
}
