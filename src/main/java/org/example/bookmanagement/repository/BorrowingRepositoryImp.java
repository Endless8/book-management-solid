package org.example.bookmanagement.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.bookmanagement.dao.Borrowing;
import org.example.bookmanagement.datasource.Datasource;
import org.example.bookmanagement.wrapper.MapperWrapper;

import java.util.List;

public class BorrowingRepositoryImp extends RepositoryImp<Borrowing> implements BorrowingRepository {

    private final MapperWrapper mapperWrapper;

    public BorrowingRepositoryImp(Datasource datasource, MapperWrapper mapperWrapper) {
        super(datasource);
        this.mapperWrapper = mapperWrapper;
    }

    @Override
    public List<Borrowing> getAllBorrowings() {
        return mapperWrapper.convertValue(getAll(), new TypeReference<>() {
        });
    }

    @Override
    public void deleteBorrowing(int bookId, String isbn) {
        List<Borrowing> existingBorrowings = getAllBorrowings();

        existingBorrowings.removeIf(borrowing -> borrowing.getBookId() == bookId && borrowing.getIsbn().equals(isbn));

        datasource.writeRecordsToFile(existingBorrowings);
    }
}
