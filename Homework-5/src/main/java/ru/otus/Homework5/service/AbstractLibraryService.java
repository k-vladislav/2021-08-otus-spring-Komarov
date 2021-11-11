package ru.otus.Homework5.service;

import ru.otus.Homework5.dao.LibraryDao;

import java.util.List;
import java.util.Optional;

public abstract class AbstractLibraryService<T> {

    private final LibraryDao<T> dao;

    AbstractLibraryService(LibraryDao<T> dao) {
        this.dao = dao;
    }

    public long add(String value) {
/*        Optional<Long> idOpt = dao.getId(value);
        if (idOpt.isEmpty()) {
            return dao.add(value);
        } else {
            return idOpt.get();
        }*/
        return dao.insert(value);
    }

    public int update (long id, String newValue) {
        return dao.update(id,newValue);
    }

    public int count() {
        return dao.count();
    }

    public Optional<T> getById(long id) {
        return dao.getById(id);
    }

    public Optional<List<T>> getAll() {
        return dao.getAll();
    }

    public void delete(String value) {
        dao.delete(value);
    }

    public Optional<Long> getId(String value) {
        return dao.getId(value);
    }

}
