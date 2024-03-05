package org.example.bookmanagement.repository;

import org.example.bookmanagement.dao.Borrowing;

import java.util.List;

public interface BorrowingRepository extends Repository<Borrowing>{

    List<Borrowing> getAllBorrowings();
    void deleteBorrowing(int bookId, String isbn);
}
