package org.example.bookmanagement.dao;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = BorrowingImp.class)
public interface Borrowing {

    int getUserId();
    void setUserId(int userId);
    int getBookId();
    void setBookId(int bookId);
    String getIsbn();
    void setIsbn(String isbn);
}
