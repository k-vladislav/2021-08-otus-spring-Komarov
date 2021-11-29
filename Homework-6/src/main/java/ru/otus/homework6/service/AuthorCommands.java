package ru.otus.homework6.service;


import ru.otus.homework6.models.Book;

import java.util.List;

public interface AuthorCommands {
    //Author
    long addAuthorForBook(String title, String lastName);

    List<Book> showBooksOfAuthor(String lastname);

    int updateAuthorLastName(String oldLastName, String newLastName);

    long deleteAuthorForBook(String title, String lastName);

    int deleteAuthorTotally(String lastName);
}
