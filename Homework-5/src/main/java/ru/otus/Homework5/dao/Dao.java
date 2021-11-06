package ru.otus.Homework5.dao;

import java.util.List;

public interface Dao<T> {

    int count();

    void insert(T t);

    T getById(long id);

    List<T> getAll();

    void deleteById(long id);
}
