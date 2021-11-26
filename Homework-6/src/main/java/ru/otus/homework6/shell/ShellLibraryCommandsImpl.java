package ru.otus.homework6.shell;


import ru.otus.homework6.models.Book;
import ru.otus.homework6.repositories.BookRepository;

import java.util.Optional;

//@ShellComponent(value = "Library commands")
public class ShellLibraryCommandsImpl implements ShellLibraryCommands {

    private final BookRepository books;

    public ShellLibraryCommandsImpl(BookRepository books) {
        this.books = books;
    }

    //@ShellMethod(key = "sb", value = "Save book")
    public void saveBook( String title) {
        Book book = new Book(title);
        books.save(book);
    }

    //@ShellMethod(key = "fbi", value = "Find book by id")
    public void findBookById( long id) {
        Optional<Book> byId = books.findById(id);
        byId.ifPresentOrElse(System.out::println, () -> System.out.println("not found"));
    }


/*    @Override
    @ShellMethod(key = "ab", value = "Add book, prints ID of inserted or existing book", group = "book")
    public void addBook(@ShellOption String title) {
        long id = library.addBook(title);
        System.out.println("Book ID = " + id);
    }*/

    @Override
    public void addBook(String title) {

    }

    @Override
    public void showBook(String title) {

    }

    @Override
    public void updateBook(String oldTitle, String newTitle) {

    }

    @Override
    public void deleteBook(String title) {

    }

    @Override
    public void addAuthorForBook(String title, String lastName) {

    }

    @Override
    public void showBooksOfAuthor(String lastname) {

    }

    @Override
    public void updateAuthor(String oldLastName, String newLastName) {

    }

    @Override
    public void deleteAuthorForBook(String title, String lastName) {

    }

    @Override
    public void deleteAuthorTotally(String lastName) {

    }

    @Override
    public void addGenreForBook(String title, String genre) {

    }

    @Override
    public void showBooksOfGenre(String genre) {

    }

    @Override
    public void updateGenre(String oldGenre, String newGenre) {

    }

    @Override
    public void deleteGenreForBook(String title, String genre) {

    }

    @Override
    public void deleteAGenreTotally(String genre) {

    }
}
