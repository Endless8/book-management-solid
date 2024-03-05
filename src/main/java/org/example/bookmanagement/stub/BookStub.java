package org.example.bookmanagement.stub;

import org.example.bookmanagement.formatter.MessageFormatter;
import org.example.bookmanagement.formatter.MessageFormatterImp;
import org.example.bookmanagement.books.*;
import org.example.bookmanagement.dao.Book;
import org.example.bookmanagement.dao.BookImp;
import org.example.bookmanagement.datasource.*;
import org.example.bookmanagement.repository.BookRepository;
import org.example.bookmanagement.repository.BookRepositoryImp;
import org.example.bookmanagement.repository.IdGenerator;
import org.example.bookmanagement.repository.IdGeneratorImp;
import org.example.bookmanagement.wrapper.MapperWrapper;
import org.example.bookmanagement.wrapper.MapperWrapperImp;

public class BookStub {

    private static final MapperWrapper MAPPER_WRAPPER = new MapperWrapperImp();
    private static final DatasourceFactory DATASOURCE_FACTORY = new DatasourceFactoryImp();
    private static final DatasourceType BOOK_DATASOURCE_TYPE = new BookDatasourceType();
    private static final Datasource BOOK_DATASOURCE = DATASOURCE_FACTORY.createDatasource(MAPPER_WRAPPER, BOOK_DATASOURCE_TYPE);
    private static final BookRepository BOOK_REPOSITORY = new BookRepositoryImp(BOOK_DATASOURCE, MAPPER_WRAPPER);
    private static final IdGenerator ID_GENERATOR = new IdGeneratorImp<>(BOOK_REPOSITORY);
    private static final BookFactory BOOK_FACTORY = new BookFactoryImp(ID_GENERATOR);
    private static final BookCreator BOOK_CREATOR = new BookCreatorImp(BOOK_FACTORY, BOOK_REPOSITORY);
    private static final MessageFormatter MESSAGE_FORMATTER = new MessageFormatterImp();
    private static final BookCatalog BOOK_CATALOG = new BookCatalogImp(BOOK_REPOSITORY, MESSAGE_FORMATTER);

    public static void main(String[] args) {
        //createBookByRepository();
        createBookByBookCreator();
        //viewBooks();
        //getBookByTitle();
        //getBooksByAuthor();
    }

    private static void getBooksByAuthor() {
        System.out.println(BOOK_CATALOG.viewBooksByAuthor("Kerouac"));
    }

    private static void getBookByTitle() {
        System.out.println(BOOK_CATALOG.viewBookByTitle("The metamorphosis"));
    }

    private static void viewBooks() {
        System.out.println(BOOK_CATALOG.viewAllBooks());
    }

    private static void createBookByBookCreator() {
        BOOK_CREATOR.insertNewBook("On the roadAAA", "Kerouac", "456");
    }

    private static void createBookByRepository() {
        Book book = new BookImp(1, "The metamorphosis", "Kafka", "123", false);
        BOOK_REPOSITORY.save(book);
    }
}
