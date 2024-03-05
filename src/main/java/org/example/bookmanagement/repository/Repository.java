package org.example.bookmanagement.repository;

import java.util.List;

public interface Repository<T> {

    List<T> getAll();
    T getById(int id);
    void saveAll(List<T> records);
    void save(T record);
    int getLastId();
}
