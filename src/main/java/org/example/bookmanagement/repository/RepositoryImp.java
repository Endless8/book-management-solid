package org.example.bookmanagement.repository;

import org.example.bookmanagement.dao.Record;
import org.example.bookmanagement.datasource.Datasource;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;

public class RepositoryImp<T> implements Repository<T> {

    final Datasource datasource;

    public RepositoryImp(Datasource datasource) {
        this.datasource = datasource;
    }

    @Override
    public List<T> getAll() {
        return datasource.getRecordsFromFile();
    }

    @Override
    public T getById(int id) {
        List<T> records = getAll();

        for (T record : records)
            if (record instanceof LinkedHashMap recordInstance && (int) recordInstance.get("id") == id)
                return record;

        throw new NoSuchElementException("Record not found for ID: " + id);
    }

    @Override
    public void saveAll(List<T> records) {
        List<T> existingRecords = getAll();

        for (T newRecord : records) {
            if (newRecord instanceof Record newRecordInstance)
                existingRecords.removeIf(element -> element instanceof LinkedHashMap existingRecord
                        && (int) existingRecord.get("id") == newRecordInstance.getId());

            existingRecords.add(newRecord);
        }

        datasource.writeRecordsToFile(existingRecords);
    }

    @Override
    public void save(T record) {
        saveAll(List.of(record));
    }

    @Override
    public int getLastId() {
        int lastId = 1;
        List<T> records = getAll();

        for (T record : records)
            if (record instanceof Record recordInstance && recordInstance.getId() > lastId)
                lastId = recordInstance.getId();

        return lastId;
    }
}
