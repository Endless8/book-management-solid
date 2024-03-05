package org.example.bookmanagement.users;

import org.example.bookmanagement.dao.User;
import org.example.bookmanagement.dao.UserImp;
import org.example.bookmanagement.repository.IdGenerator;

public class UserFactoryImp implements UserFactory {

    private final IdGenerator idGenerator;

    public UserFactoryImp(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public User createUser(String name, String email) {
        return new UserImp(idGenerator.getNewId(), name, email);
    }
}
