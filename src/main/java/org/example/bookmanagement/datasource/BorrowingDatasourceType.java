package org.example.bookmanagement.datasource;

public class BorrowingDatasourceType implements DatasourceType {

    @Override
    public String getCollectionName() {
        return "borrowings";
    }
}
