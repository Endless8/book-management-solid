package unit;

import org.example.bookmanagement.dao.Book;
import org.example.bookmanagement.dao.BookImp;
import org.example.bookmanagement.dao.User;
import org.example.bookmanagement.dao.UserImp;

import java.util.List;

public class Mocks {

    private static final List<Book> borrowableBooks = List.of(new BookImp(1, "The metamorphosis", "Kafka", "123", false),
            new BookImp(2, "On the road", "Kerouac", "456", false));
    private static final List<Book> borrowedBooks = List.of(new BookImp(1, "The metamorphosis", "Kafka", "123", true),
            new BookImp(2, "On the road", "Kerouac", "456", true));
    private static final List<User> users = List.of(new UserImp(1, "edoardo", "edo@email.com"),
            new UserImp(2, "mimmo", "mim@email.com"));

    static List<Book> getMockedBorrowableBooks() {
        return borrowableBooks;
    }

    static List<Book> getMockedBorrowedBooks() {
        return borrowedBooks;
    }

    static List<User> getMockedUsers() {
        return users;
    }
}
