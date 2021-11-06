package ru.otus.Homework5.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    int count();

    long insert(T t);

    T getById(long id);

    List<T> getAll();

    void deleteById(long id);

    Long getIdByValue(String value);
}
