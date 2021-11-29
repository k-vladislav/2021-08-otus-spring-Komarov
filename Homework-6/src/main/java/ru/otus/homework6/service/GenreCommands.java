package ru.otus.homework6.service;


import ru.otus.homework6.models.Book;

import java.util.List;

public interface GenreCommands {
    //Genre
    long addGenreForBook(String title, String genre);

    List<Book> showBooksOfGenre(String genre);

    int updateGenre(String oldGenre, String newGenre);

    long deleteGenreForBook(String title, String genre);

    int deleteAGenreTotally(String genre);
}
