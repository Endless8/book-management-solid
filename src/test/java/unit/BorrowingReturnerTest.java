package unit;

import org.example.bookmanagement.borrowings.BorrowingReturner;
import org.example.bookmanagement.borrowings.BorrowingReturnerImp;
import org.example.bookmanagement.dao.Book;
import org.example.bookmanagement.formatter.MessageFormatter;
import org.example.bookmanagement.repository.BookRepository;
import org.example.bookmanagement.repository.BorrowingRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.example.bookmanagement.constants.InfoMessages.BORROWING_RETURNED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static unit.Mocks.*;

public class BorrowingReturnerTest {

    private static BorrowingReturner borrowingReturner;
    private static BookRepository bookRepository;
    private static BorrowingRepository borrowingRepository;
    private static MessageFormatter messageFormatter;
    private static List<Book> mockedBooks;

    @BeforeAll
    static void init() {
        bookRepository = mock(BookRepository.class);
        borrowingRepository = mock(BorrowingRepository.class);
        messageFormatter = mock(MessageFormatter.class);
        borrowingReturner = new BorrowingReturnerImp(bookRepository, borrowingRepository, messageFormatter);
        mockedBooks = getMockedBorrowedBooks();
    }

    @Test
    void returnBookFullTest() {
        getBorrowedBooks();
        borrowingReturner.chooseBook(1);
        returnBook();
    }

    private void getBorrowedBooks() {
        String expected = """
                1) Title: 'The metamorphosis' Author: 'Kafka' ISBN: '123'
                2) Title: 'On the road' Author: 'Kerouac' ISBN: '456'
                """;

        when(bookRepository.getBorrowedBooks()).thenReturn(mockedBooks);
        when(messageFormatter.formatList(mockedBooks)).thenReturn(expected);

        String result = borrowingReturner.getBorrowedBooks();

        assertEquals(expected, result);
    }

    private void returnBook() {
        Book mockedBook = mockedBooks.get(0);

        doNothing().when(borrowingRepository).deleteBorrowing(1, "123");
        when(bookRepository.getBookById(1)).thenReturn(mockedBook);
        doNothing().when(bookRepository).save(mockedBook);

        String result = borrowingReturner.returnBook();

        assertEquals(BORROWING_RETURNED, result);
    }
}
