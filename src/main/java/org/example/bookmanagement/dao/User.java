package org.example.bookmanagement.dao;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = UserImp.class)
public interface User extends Record {

    String getName();
    void setName(String name);
    String getEmail();
    void setEmail(String email);
}
