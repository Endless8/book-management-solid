package org.example.bookmanagement.dao;

public abstract class RecordImp implements Record {

    private int id;

    public RecordImp() {
    }

    public RecordImp(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
