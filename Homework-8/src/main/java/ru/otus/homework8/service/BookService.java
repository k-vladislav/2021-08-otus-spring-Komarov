package ru.otus.homework8.service;

import ru.otus.homework8.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    void save(String value);

    Optional<Book> findById(long bookId);

    boolean updateTitle(long bookId, String newValue);

    boolean delete(long bookId);

    List<Book> getAllBooks();
}
