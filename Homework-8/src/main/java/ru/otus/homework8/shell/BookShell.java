package ru.otus.homework8.shell;

public interface BookShell {

    void addBook(String title);

    void showBook(long id);

    void getAllBooks();

    void updateBookTitle(long bookId, String newTitle);

    void deleteBook(long bookId);


    void addAuthorForBook(long bookId, String lastName);

    void deleteAuthorForBook(long bookId, long authorId);


    void addCommentForBook(long bookId, String comment);
    void showCommentsForBook(long bookId);

}