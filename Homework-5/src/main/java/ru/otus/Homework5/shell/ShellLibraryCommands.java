package ru.otus.Homework5.shell;

public interface ShellLibraryCommands {

    void addBookNew(String bookTitle);

    void addAuthor(String bookTitle, String authorLastName);

    void addGenre(String bookTitle, String genre);

    void getBook(String bookTitle);

    // void getAuthor(String authorLastName);

    //  void getGenre(String genre);

    void getAllBooks();

    // void getAllAuthors();

    //  void getAllGenres();

    void countBooks();

    void delBook(String bookTitle);


}
