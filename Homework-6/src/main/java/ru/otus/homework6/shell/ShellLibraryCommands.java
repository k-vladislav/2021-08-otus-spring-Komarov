package ru.otus.homework6.shell;

public interface ShellLibraryCommands {

    void addBook(String title);

    void showBook(String title);

    void updateBook(String oldTitle, String newTitle);

    void deleteBook(String title);


    void addAuthorForBook(String title, String lastName);

    void showBooksOfAuthor(String lastname);

    void updateAuthor(String oldLastName, String newLastName);

    void deleteAuthorForBook(String title, String lastName);

    void deleteAuthorTotally(String lastName);


    void addGenreForBook(String title, String genre);

    void showBooksOfGenre(String genre);

    void updateGenre(String oldGenre, String newGenre);

    void deleteGenreForBook(String title, String genre);

    void deleteAGenreTotally(String genre);

}