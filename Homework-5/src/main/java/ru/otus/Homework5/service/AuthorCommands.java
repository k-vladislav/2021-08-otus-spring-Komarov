package ru.otus.Homework5.service;

import ru.otus.Homework5.domain.Book;

import java.util.List;

public interface AuthorCommands {
    //Author
    long addAuthorForBook(String title, String lastName);

    List<Book> showBooksOfAuthor(String lastname);

    int updateAuthor(String oldLastName, String newLastName);

    long deleteAuthorForBook(String title, String lastName);

    int deleteAuthorTotally(String lastName);
}
