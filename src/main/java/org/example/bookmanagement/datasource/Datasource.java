package org.example.bookmanagement.datasource;

import java.util.List;

public interface Datasource {

    <T> void writeRecordsToFile(List<T> objects);

    <T> List<T> getRecordsFromFile();
}
