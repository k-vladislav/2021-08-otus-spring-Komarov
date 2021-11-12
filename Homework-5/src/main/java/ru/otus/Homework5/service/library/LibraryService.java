package ru.otus.Homework5.service.library;

import java.util.List;
import java.util.Optional;

public interface LibraryService<T> {
    long add(String value);

    int update(long id, String newValue);

    int count();

    Optional<T> getById(long id);

    Optional<List<T>> getAll();

    int delete(long id);

    Optional<Long> getId(String value);
}
