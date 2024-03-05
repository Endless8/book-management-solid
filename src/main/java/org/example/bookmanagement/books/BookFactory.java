package org.example.bookmanagement.books;

import org.example.bookmanagement.dao.Book;

public interface BookFactory {

    Book createBook(String title, String author, String isbn);
}
