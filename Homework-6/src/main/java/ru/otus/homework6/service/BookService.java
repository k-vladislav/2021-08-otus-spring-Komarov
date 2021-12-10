package ru.otus.homework6.service;


import ru.otus.homework6.models.Book;

import java.util.Optional;

public interface BookService {
    //Book
    boolean persistBook(String title);

    Optional<Book> showBook(String title);

    void updateBookTitle(String oldTitle, String newTitle);

    boolean deleteBook(String title);
}
