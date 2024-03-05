package unit;

import org.example.bookmanagement.formatter.MessageFormatter;
import org.example.bookmanagement.books.BookCatalog;
import org.example.bookmanagement.books.BookCatalogImp;
import org.example.bookmanagement.dao.Book;
import org.example.bookmanagement.repository.BookRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.example.bookmanagement.constants.ErrorMessages.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static unit.Mocks.*;

public class BookCatalogTest {

    private static BookCatalog bookCatalog;
    private static BookRepository bookRepository;
    private static MessageFormatter messageFormatter;
    private static List<Book> mockedBooks;

    @BeforeAll
    static void init() {
        bookRepository = mock(BookRepository.class);
        messageFormatter = mock(MessageFormatter.class);
        bookCatalog = new BookCatalogImp(bookRepository, messageFormatter);
        mockedBooks = getMockedBorrowableBooks();
    }

    @Test
    void viewAllBooks() {
        String expected = """
                1) Title: 'The metamorphosis' Author: 'Kafka' ISBN: '123'
                2) Title: 'On the road' Author: 'Kerouac' ISBN: '456'
                """;

        when(bookRepository.getAllBooks()).thenReturn(mockedBooks);
        when(messageFormatter.formatList(mockedBooks)).thenReturn(expected);

        String result = bookCatalog.viewAllBooks();
        assertEquals(expected, result);
    }

    @Test
    void errorViewingAllBooks() {
        List<Book> emptyList = List.of();

        when(bookRepository.getAllBooks()).thenReturn(emptyList);

        String result = bookCatalog.viewAllBooks();
        assertEquals(NO_BOOKS_FOUND, result);
    }

    @Test
    void viewBookByTitle() {
        String expected = "1) Title: 'The metamorphosis' Author: 'Kafka' ISBN: '123'\n";
        String mockedBookTitle = "The metamorphosis";
        int firstBookIndex = 0;
        Book mockedBook = mockedBooks.get(firstBookIndex);
        List<Book> mockedFoundBooks = List.of(mockedBook);

        when(bookRepository.getByTitle(mockedBookTitle)).thenReturn(mockedFoundBooks);
        when(messageFormatter.formatList(mockedFoundBooks)).thenReturn(expected);

        String result = bookCatalog.viewBookByTitle(mockedBookTitle);
        assertEquals(expected, result);
    }

    @Test
    void errorViewingBookByTitle() {
        String mockedBookTitle = "The metamorphosis";
        String expected = String.format(BOOK_NOT_FOUND_FOR_TITLE, mockedBookTitle);
        List<Book> emptyList = List.of();

        when(bookRepository.getByTitle(mockedBookTitle)).thenReturn(emptyList);
        when(messageFormatter.formatErrorMessage(BOOK_NOT_FOUND_FOR_TITLE, mockedBookTitle)).thenReturn(expected);

        String result = bookCatalog.viewBookByTitle(mockedBookTitle);
        assertEquals(expected, result);
    }

    @Test
    void viewBookByAuthor() {
        String expected = "1) Title: 'On the road' Author: 'Kerouac' ISBN: '456'\n";
        String mockedBookAuthor = "Kerouac";
        int secondBookIndex = 1;
        Book mockedBook = mockedBooks.get(secondBookIndex);
        List<Book> mockedFoundBooks = List.of(mockedBook);

        when(bookRepository.getByAuthor(mockedBookAuthor)).thenReturn(mockedFoundBooks);
        when(messageFormatter.formatList(mockedFoundBooks)).thenReturn(expected);

        String result = bookCatalog.viewBooksByAuthor(mockedBookAuthor);
        assertEquals(expected, result);
    }

    @Test
    void errorViewingBookByAuthor() {
        String mockedBookAuthor = "Kerouac";
        String expected = String.format(BOOK_NOT_FOUND_FOR_AUTHOR, mockedBookAuthor);
        List<Book> emptyList = List.of();

        when(bookRepository.getByAuthor(mockedBookAuthor)).thenReturn(emptyList);
        when(messageFormatter.formatErrorMessage(BOOK_NOT_FOUND_FOR_AUTHOR, mockedBookAuthor)).thenReturn(expected);

        String result = bookCatalog.viewBooksByAuthor(mockedBookAuthor);
        assertEquals(expected, result);
    }
}
