package ru.otus.homework7.service;

import java.util.List;
import java.util.Optional;

public interface BookService<T> {

    void save(String value);

    Optional<T> getBookWithComments(String value);

    void updateValue(String oldValue, String newValue);

    void delete(String value);

    List<T> getAllBooks();
}
