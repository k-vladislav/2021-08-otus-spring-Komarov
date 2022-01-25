package ru.otus.homework7.service;

import ru.otus.homework7.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> findBookById(long id);

    Optional<Book> findBookByTitle(String title);

    void save(String value);

    Optional<Book> getBookWithComments(String value);

    boolean updateTitle(String oldValue, String newValue);

    boolean delete(String value);

    List<Book> getAllBooks();
}
