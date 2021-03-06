package ru.otus.Homework5.service.library;

import java.util.List;
import java.util.Optional;

public interface LibraryService<T> {
    long add(String value);

    int update(long id, String newValue);

    int count();

    @Deprecated
    Optional<T> getById(long id);

    List<T>  getByListOfId(List<Long> idList);

    List<T> getAll();

    int delete(long id);

    Optional<Long> getId(String value);
}
