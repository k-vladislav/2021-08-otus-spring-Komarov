package ru.otus.Homework5.service.InterfaceBased;

import ru.otus.Homework5.dao.LibraryDao;

import java.util.List;
import java.util.Optional;

public interface LibraryService<T> {

    int count();

    long insert(String value);

    Optional<T> getById(long id);

    Optional<List<T>> getAll();

    void deleteById(long id);

    Optional<Long> getIdByValue(String value);
}
