package unit;

import org.example.bookmanagement.books.BookCreator;
import org.example.bookmanagement.books.BookCreatorImp;
import org.example.bookmanagement.books.BookFactory;
import org.example.bookmanagement.dao.BookImp;
import org.example.bookmanagement.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.example.bookmanagement.constants.ErrorMessages.BOOK_INSERT_ERROR;
import static org.example.bookmanagement.constants.InfoMessages.NEW_BOOK_INSERTED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookCreatorTest {

    private BookCreator bookCreator;
    private BookFactory bookFactory;
    private BookRepository bookRepository;

    @BeforeEach
    void init() {
        bookFactory = mock(BookFactory.class);
        bookRepository = mock(BookRepository.class);
        bookCreator = new BookCreatorImp(bookFactory, bookRepository);
    }

    @Test
    void createNewBook() {
        String title = "The metamorphosis";
        String author = "Kafka";
        String isbn = "123";
        BookImp mockedBook = new BookImp(1, title, author, isbn, false);

        when(bookFactory.createBook(title, author, isbn)).thenReturn(mockedBook);
        doNothing().when(bookRepository).save(mockedBook);

        String result = bookCreator.insertNewBook(title, author, isbn);

        assertEquals(NEW_BOOK_INSERTED, result);
    }

    @Test
    void errorCreatingNewBook() {
        String title = "The metamorphosis";
        String author = "Kafka";
        String isbn = "123";
        BookImp mockedBook = new BookImp(1, title, author, isbn, false);

        when(bookFactory.createBook(title, author, isbn)).thenReturn(mockedBook);
        doThrow(RuntimeException.class).when(bookRepository).save(mockedBook);

        String result = bookCreator.insertNewBook(title, author, isbn);

        assertEquals(BOOK_INSERT_ERROR, result);
    }
}
