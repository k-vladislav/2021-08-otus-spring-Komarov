package ru.otus.Homework5.service;

public interface LinkLibraryService {

    void linkBookAuthor (Long bookId, Long authorId);

    void linkBookGenre (Long bookId, Long genreId);
}
