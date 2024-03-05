package org.example.bookmanagement.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.bookmanagement.wrapper.MapperWrapper;
import org.example.bookmanagement.dao.Book;
import org.example.bookmanagement.datasource.Datasource;

import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImp extends RepositoryImp<Book> implements BookRepository {

    private final MapperWrapper mapperWrapper;

    public BookRepositoryImp(Datasource datasource, MapperWrapper mapperWrapper) {
        super(datasource);
        this.mapperWrapper = mapperWrapper;
    }

    @Override
    public List<Book> getAllBooks() {
        return mapperWrapper.convertValue(getAll(), new TypeReference<>() {
        });
    }

    @Override
    public Book getBookById(int id) {
        return mapperWrapper.convertValue(getById(id), new TypeReference<>() {
        });
    }

    @Override
    public List<Book> getByTitle(String title) {
        List<Book> books = getAllBooks();
        List<Book> booksOfTitle = new ArrayList<>();

        for (Book book : books)
            if (book.getTitle().equals(title))
                booksOfTitle.add(book);

        return booksOfTitle;
    }

    @Override
    public List<Book> getByAuthor(String author) {
        List<Book> books = getAllBooks();
        List<Book> booksOfAuthor = new ArrayList<>();

        for (Book book : books)
            if (book.getAuthor().equals(author))
                booksOfAuthor.add(book);

        return booksOfAuthor;
    }

    @Override
    public List<Book> getBorrowableBooks() {
        return getBooksByBorrowableStatus(false);
    }

    @Override
    public List<Book> getBorrowedBooks() {
        return getBooksByBorrowableStatus(true);
    }

    private List<Book> getBooksByBorrowableStatus(boolean isBorrowed) {
        List<Book> books = getAllBooks();
        List<Book> booksByIsBorrowed = new ArrayList<>();

        for (Book book : books)
            if (book.isBorrowed() == isBorrowed)
                booksByIsBorrowed.add(book);

        return booksByIsBorrowed;
    }
}
