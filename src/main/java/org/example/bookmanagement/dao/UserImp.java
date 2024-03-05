package org.example.bookmanagement.dao;

public class UserImp extends RecordImp implements User {

    private String name;
    private String email;

    public UserImp() {
    }

    public UserImp(int id, String name, String email) {
        super(id);
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Name: '" + name + '\'' +
                " Email: '" + email + '\'';
    }
}
