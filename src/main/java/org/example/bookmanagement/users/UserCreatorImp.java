package org.example.bookmanagement.users;

import org.example.bookmanagement.dao.User;
import org.example.bookmanagement.repository.UserRepository;

import static org.example.bookmanagement.constants.ErrorMessages.*;
import static org.example.bookmanagement.constants.InfoMessages.*;

public class UserCreatorImp implements UserCreator {

    private final UserFactory userFactory;
    private final UserRepository userRepository;

    public UserCreatorImp(UserFactory userFactory, UserRepository userRepository) {
        this.userFactory = userFactory;
        this.userRepository = userRepository;
    }

    public String insertNewUser(String name, String email) {
        User newUser = userFactory.createUser(name, email);

        try {
            userRepository.save(newUser);
        } catch (Exception e) {
            return USER_INSERT_ERROR;
        }

        return NEW_USER_INSERTED;
    }
}
