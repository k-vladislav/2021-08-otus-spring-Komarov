package ru.otus.Homework5.dao.library;

import java.util.List;
import java.util.Optional;

public interface LibraryDao<T> {

    long insert(String value);

    int update(long id, String newValue);

    int delete(long id);

    int count();

    Optional<T> getById(long id);

    Optional<List<T>> getAll();

    Optional<Long> getId(String value);

}
