package ru.otus.Homework5.service;

import ru.otus.Homework5.domain.Book;

import java.util.List;

public interface GenreCommands {
    //Genre
    long addGenreForBook(String title, String genre);

    List<Book> showBooksOfGenre(String genre);

    int updateGenre(String oldGenre, String newGenre);

    long deleteGenreForBook(String title, String genre);

    int deleteAGenreTotally(String genre);
}
