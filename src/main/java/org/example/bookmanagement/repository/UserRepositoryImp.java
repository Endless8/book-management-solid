package org.example.bookmanagement.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.bookmanagement.dao.User;
import org.example.bookmanagement.datasource.Datasource;
import org.example.bookmanagement.wrapper.MapperWrapper;

import java.util.List;

public class UserRepositoryImp extends RepositoryImp<User> implements UserRepository {

    private final MapperWrapper mapperWrapper;

    public UserRepositoryImp(Datasource datasource, MapperWrapper mapperWrapper) {
        super(datasource);
        this.mapperWrapper = mapperWrapper;
    }

    @Override
    public List<User> getAllUsers() {
        return mapperWrapper.convertValue(getAll(), new TypeReference<>() {
        });
    }
}
