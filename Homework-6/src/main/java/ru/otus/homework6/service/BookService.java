package ru.otus.homework6.service;

import java.util.Optional;

public interface BookService<T> {

    void save(String value);

    Optional<T> getBookFullInfo(String value);

    void updateValue(String oldValue, String newValue);

    void delete(String value);
}
