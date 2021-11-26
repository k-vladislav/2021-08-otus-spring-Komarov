package ru.otus.homework6.repositories;

import ru.otus.homework6.models.Book;

import java.util.Optional;

public interface BookRepository {

    void save(Book book);
    Optional<Book> findById(long id);

}
