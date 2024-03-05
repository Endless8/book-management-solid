package org.example.bookmanagement.users;

import org.example.bookmanagement.dao.User;

public interface UserFactory {

    User createUser(String name, String email);
}
