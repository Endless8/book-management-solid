package org.example.bookmanagement.datasource;

public class UserDatasourceType implements DatasourceType {
    @Override
    public String getCollectionName() {
        return "users";
    }
}
