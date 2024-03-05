package org.example.bookmanagement.datasource;

public class BookDatasourceType implements DatasourceType {
    @Override
    public String getCollectionName() {
        return "books";
    }
}
