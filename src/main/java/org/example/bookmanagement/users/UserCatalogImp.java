package org.example.bookmanagement.users;

import org.example.bookmanagement.formatter.MessageFormatter;
import org.example.bookmanagement.constants.ErrorMessages;
import org.example.bookmanagement.dao.User;
import org.example.bookmanagement.repository.UserRepository;

import java.util.List;

public class UserCatalogImp implements UserCatalog {

    private final UserRepository userRepository;
    private final MessageFormatter messageFormatter;

    public UserCatalogImp(UserRepository userRepository, MessageFormatter messageFormatter) {
        this.userRepository = userRepository;
        this.messageFormatter = messageFormatter;
    }

    @Override
    public String viewAllUsers() {
        List<User> users = userRepository.getAllUsers();

        return users.isEmpty() ?
                ErrorMessages.NO_USERS_FOUND :
                messageFormatter.formatList(users);
    }
}
