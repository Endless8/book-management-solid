package org.example.bookmanagement.stub;

import org.example.bookmanagement.formatter.MessageFormatter;
import org.example.bookmanagement.formatter.MessageFormatterImp;
import org.example.bookmanagement.borrowings.BorrowingMaker;
import org.example.bookmanagement.borrowings.BorrowingMakerImp;
import org.example.bookmanagement.borrowings.BorrowingReturner;
import org.example.bookmanagement.borrowings.BorrowingReturnerImp;
import org.example.bookmanagement.datasource.*;
import org.example.bookmanagement.repository.*;
import org.example.bookmanagement.wrapper.MapperWrapper;
import org.example.bookmanagement.wrapper.MapperWrapperImp;

public class BorrowingStub {

    private static final MapperWrapper MAPPER_WRAPPER = new MapperWrapperImp();
    private static final DatasourceFactory DATASOURCE_FACTORY = new DatasourceFactoryImp();
    private static final DatasourceType BOOK_DATASOURCE_TYPE = new BookDatasourceType();
    private static final Datasource BOOK_DATASOURCE = DATASOURCE_FACTORY.createDatasource(MAPPER_WRAPPER, BOOK_DATASOURCE_TYPE);
    private static final BookRepository BOOK_REPOSITORY = new BookRepositoryImp(BOOK_DATASOURCE, MAPPER_WRAPPER);private static final DatasourceType USER_DATASOURCE_TYPE = new UserDatasourceType();
    private static final Datasource USER_DATASOURCE = DATASOURCE_FACTORY.createDatasource(MAPPER_WRAPPER, USER_DATASOURCE_TYPE);
    private static final UserRepository USER_REPOSITORY = new UserRepositoryImp(USER_DATASOURCE, MAPPER_WRAPPER);
    private static final DatasourceType BORROWING_DATASOURCE_TYPE = new BorrowingDatasourceType();
    private static final Datasource BORROWING_DATASOURCE = DATASOURCE_FACTORY.createDatasource(MAPPER_WRAPPER, BORROWING_DATASOURCE_TYPE);
    private static final BorrowingRepository BORROWING_REPOSITORY = new BorrowingRepositoryImp(BORROWING_DATASOURCE, MAPPER_WRAPPER);
    private static final MessageFormatter MESSAGE_FORMATTER = new MessageFormatterImp();
    private static final BorrowingMaker BORROWING_MAKER = new BorrowingMakerImp(BOOK_REPOSITORY, USER_REPOSITORY, BORROWING_REPOSITORY, MESSAGE_FORMATTER);
    private static final BorrowingReturner BORROWING_RETURNER = new BorrowingReturnerImp(BOOK_REPOSITORY, BORROWING_REPOSITORY, MESSAGE_FORMATTER);

    public static void main(String[] args) {
        //borrowBook();
        //returnBook();
    }

    private static void returnBook() {
        BORROWING_RETURNER.getBorrowedBooks();
        BORROWING_RETURNER.chooseBook(1);
        BORROWING_RETURNER.returnBook();
    }

    private static void borrowBook() {
        BORROWING_MAKER.getBorrowableBooks();
        BORROWING_MAKER.chooseBook(1);
        BORROWING_MAKER.getActiveUsers();
        BORROWING_MAKER.chooseUser(1);
        BORROWING_MAKER.borrowBook();
    }
}
