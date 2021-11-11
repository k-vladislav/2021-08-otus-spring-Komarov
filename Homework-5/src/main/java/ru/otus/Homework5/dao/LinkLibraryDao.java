package ru.otus.Homework5.dao;

import java.util.Optional;

public interface LinkLibraryDao {

    Optional<Long> getIdBookAuthor(Long bookId, Long authorId);

    void linkBookAuthor(Long bookId, Long authorId);

    Optional<Long> getIdBookGenre(Long bookId, Long genreId);

    void linkBookGenre(Long bookId, Long genreId);

}
