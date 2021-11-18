package ru.otus.Homework5.service;

import ru.otus.Homework5.domain.Book;

import java.util.List;
import java.util.Optional;

public interface MainLibraryService {

    //Book
    long addBook(String title);
    Optional<Book> showBook(String title);
    int updateBookTitle(String oldTitle, String newTitle);
    int deleteBook(String title);

    //Author
    long addAuthorForBook(String title, String lastName);

    List<Book> showBooksOfAuthor(String lastname);

    int updateAuthor(String oldLastName, String newLastName);

    long deleteAuthorForBook(String title, String lastName);

    int deleteAuthorTotally(String lastName);

    //Genre
    long addGenreForBook(String title, String genre);

    List<Book> showBooksOfGenre(String genre);

    int updateGenre(String oldGenre, String newGenre);

    long deleteGenreForBook(String title, String genre);

    int deleteAGenreTotally(String genre);

}
