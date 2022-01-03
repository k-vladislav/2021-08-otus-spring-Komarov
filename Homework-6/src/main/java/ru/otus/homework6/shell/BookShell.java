package ru.otus.homework6.shell;

public interface BookShell {

    /**
     * get all books with authors and genres (eager), but not Comments (lazy)
     */
    void getAllBooks();

    void addBook(String title);

    void showBook(String title);

    void updateBookTitle(String oldTitle, String newTitle);

    void deleteBook(String title);


    void addAuthorForBook(String title, String lastName);

    void deleteAuthorForBook(String title, String lastName);

    void addCommentForBook(String title, String comment);

/*
    void addGenreForBook(String title, String genre);

    void deleteGenreForBook(String title, String genre);
*/
}