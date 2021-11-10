package ru.otus.Homework5.service;

import ru.otus.Homework5.dao.LibraryDao;

import java.util.List;
import java.util.Optional;

public abstract class AbstractLibraryService<T> {

    private final LibraryDao<T> dao;

     AbstractLibraryService(LibraryDao<T> dao) {
        this.dao = dao;
    }

    public int count() {
        return dao.count();
    }

    public long insert(String value) {
        if (dao.getIdByValue(value).isEmpty()) {
            return dao.insert(value);
        } else {
            return dao.getIdByValue(value).get();
        }
    }

    public Optional<T> getById(long id) {
        return dao.getById(id);
    }

    public Optional<List<T>> getAll() {
        return dao.getAll();
    }

    public void deleteById(long id) {
        dao.deleteById(id);
    }

    public Optional<Long> getIdByValue(String value) {
        return dao.getIdByValue(value);
    }
}
