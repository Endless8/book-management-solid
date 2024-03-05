package unit;

import org.example.bookmanagement.borrowings.BorrowingMaker;
import org.example.bookmanagement.borrowings.BorrowingMakerImp;
import org.example.bookmanagement.dao.Book;
import org.example.bookmanagement.dao.BorrowingImp;
import org.example.bookmanagement.dao.User;
import org.example.bookmanagement.formatter.MessageFormatter;
import org.example.bookmanagement.repository.BookRepository;
import org.example.bookmanagement.repository.BorrowingRepository;
import org.example.bookmanagement.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.example.bookmanagement.constants.InfoMessages.NEW_BORROWING_INSERTED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static unit.Mocks.*;

public class BorrowingMakerTest {

    private static BorrowingMaker borrowingMaker;
    private static BookRepository bookRepository;
    private static UserRepository userRepository;
    private static BorrowingRepository borrowingRepository;
    private static MessageFormatter messageFormatter;
    private static List<Book> mockedBooks;
    private static List<User> mockedUsers;

    @BeforeAll
    static void init() {
        bookRepository = mock(BookRepository.class);
        userRepository = mock(UserRepository.class);
        borrowingRepository = mock(BorrowingRepository.class);
        messageFormatter = mock(MessageFormatter.class);
        borrowingMaker = new BorrowingMakerImp(bookRepository, userRepository, borrowingRepository, messageFormatter);
        mockedBooks = getMockedBorrowableBooks();
        mockedUsers = getMockedUsers();
    }

    @Test
    void borrowBookFullTest() {
        getBorrowableBooks();
        borrowingMaker.chooseBook(1);
        getActiveUsers();
        borrowingMaker.chooseUser(1);
        borrowBook();
    }

    private void getBorrowableBooks() {
        String expected = """
                1) Title: 'The metamorphosis' Author: 'Kafka' ISBN: '123'
                2) Title: 'On the road' Author: 'Kerouac' ISBN: '456'
                """;

        when(bookRepository.getBorrowableBooks()).thenReturn(mockedBooks);
        when(messageFormatter.formatList(mockedBooks)).thenReturn(expected);

        String result = borrowingMaker.getBorrowableBooks();

        assertEquals(expected, result);
    }

    private void getActiveUsers() {
        String expected = """
                1) Name: 'edoardo' Email: 'edo@email.com'}
                2) Name: 'mimmo' Email: 'mim@email.com'}
                """;

        when(userRepository.getAllUsers()).thenReturn(mockedUsers);
        when(messageFormatter.formatList(mockedUsers)).thenReturn(expected);

        String result = borrowingMaker.getActiveUsers();

        assertEquals(expected, result);
    }

    private void borrowBook() {
        int userId = 1;
        int bookId = 1;
        Book mockedBook = mockedBooks.get(0);
        BorrowingImp borrowingMock = new BorrowingImp(userId, bookId, "123");

        doNothing().when(borrowingRepository).save(borrowingMock);
        when(bookRepository.getBookById(bookId)).thenReturn(mockedBook);
        doNothing().when(bookRepository).save(mockedBook);

        String result = borrowingMaker.borrowBook();

        assertEquals(NEW_BORROWING_INSERTED, result);
    }
}
