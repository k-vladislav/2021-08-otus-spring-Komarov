package ru.otus.Homework5.service.library;

import ru.otus.Homework5.dao.library.LibraryDao;

import java.util.List;
import java.util.Optional;

public abstract class AbstractLibraryService<T> implements LibraryService<T> {

    private final LibraryDao<T> dao;

    AbstractLibraryService(LibraryDao<T> dao) {
        this.dao = dao;
    }

    /**
     * Inserts new value in a table and returns its id. If such value already exists, then returns id of existing row
     *
     * @param value String value to be added
     * @return id of inserted row or id of existing row
     */
    @Override
    public long add(String value) {
        Optional<Long> idOpt = dao.getId(value);
        if (idOpt.isEmpty()) {
            return dao.insert(value);
        } else {
            return idOpt.get();
        }
    }

    @Override
    public int update(long id, String newValue) {
        return dao.update(id, newValue);
    }

    @Override
    public int count() {
        return dao.count();
    }

    @Override
    public Optional<T> getById(long id) {
        return dao.getById(id);
    }

    @Override
    public Optional<List<T>> getAll() {
        return dao.getAll();
    }

    @Override
    public int delete(long id) {
        return dao.delete(id);
    }

    @Override
    public Optional<Long> getId(String value) {
        return dao.getId(value);
    }

}
