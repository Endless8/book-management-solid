package org.example.bookmanagement.repository;

import org.example.bookmanagement.dao.Book;

import java.util.List;

public interface BookRepository extends Repository<Book> {

    List<Book> getAllBooks();
    Book getBookById(int id);
    List<Book> getByTitle(String title);
    List<Book> getByAuthor(String author);
    List<Book> getBorrowableBooks();
    List<Book> getBorrowedBooks();
}
