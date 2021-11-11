package ru.otus.Homework5.dao;

import java.util.List;
import java.util.Optional;

public interface LibraryDao<T> {

    long insert(String value);

    int update(long id, String newValue);

    void delete(String value);

    int count();

    Optional<T> getById(long id);

    Optional<List<T>> getAll();

    Optional<Long> getId(String value);

}
