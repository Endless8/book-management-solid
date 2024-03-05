package org.example.bookmanagement.books;

import org.example.bookmanagement.dao.Book;
import org.example.bookmanagement.dao.BookImp;
import org.example.bookmanagement.repository.IdGenerator;

public class BookFactoryImp implements BookFactory {

    private final IdGenerator idGenerator;

    public BookFactoryImp(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public Book createBook(String title, String author, String isbn) {
        return new BookImp(idGenerator.getNewId(), title, author, isbn, false);
    }
}
