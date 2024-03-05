package org.example.bookmanagement.dao;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = BookImp.class)
public interface Book extends Record {

    String getTitle();
    void setTitle(String title);
    String getAuthor();
    void setAuthor(String author);
    String getIsbn();
    void setIsbn(String isbn);
    boolean isBorrowed();
    void setBorrowed(boolean borrowed);
}
