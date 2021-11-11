package ru.otus.Homework5.service;

import java.util.List;

public interface CommonService<T> {

    int count();

    void insert(String value);

    T getById(long id);

    List<T> getAll();

    void deleteById(long id);
}
