package ru.otus.Homework5.service;

import ru.otus.Homework5.domain.Book;

import java.util.Optional;

public interface BookCommands {
    //Book
    long addBook(String title);

    Optional<Book> showBook(String title);

    int updateBookTitle(String oldTitle, String newTitle);

    int deleteBook(String title);
}
