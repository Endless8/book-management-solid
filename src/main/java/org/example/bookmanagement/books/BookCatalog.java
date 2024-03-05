package org.example.bookmanagement.books;

public interface BookCatalog {

    String viewAllBooks();

    String viewBookByTitle(String title);

    String viewBooksByAuthor(String author);
}
