package org.example.bookmanagement.repository;

import org.example.bookmanagement.dao.User;

import java.util.List;

public interface UserRepository extends Repository<User> {

    List<User> getAllUsers();
}
