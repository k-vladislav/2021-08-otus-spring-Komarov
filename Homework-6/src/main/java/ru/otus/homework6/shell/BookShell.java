package ru.otus.homework6.shell;

public interface BookShell {

    void addBook(String title);

    void showBook(String title);

    void updateBookTitle(String oldTitle, String newTitle);

    void deleteBook(String title);


    void addAuthorForBook(String title, String lastName);

    void deleteAuthorForBook(String title, String lastName);

/*
    void addGenreForBook(String title, String genre);

    void deleteGenreForBook(String title, String genre);
*/
}