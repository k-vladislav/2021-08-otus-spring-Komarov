package ru.otus.Homework5.dao;

import java.util.List;
import java.util.Optional;

public interface LibraryDao<T> {

    int count();

    long insert(String value);

    Optional<T> getById(long id);

    Optional<List<T>> getAll();

    void deleteById(long id);

    Optional<Long> getIdByValue(String value);
}
