package org.example.bookmanagement.repository;

public class IdGeneratorImp<T> implements IdGenerator {

    private final Repository<T> repository;

    public IdGeneratorImp(Repository<T> repository) {
        this.repository = repository;
    }

    @Override
    public int getNewId() {
        return repository.getLastId() + 1;
    }
}
