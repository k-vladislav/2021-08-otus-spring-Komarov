package ru.otus.homework6.shell;


import org.springframework.shell.standard.ShellComponent;
import ru.otus.homework6.models.Book;
import ru.otus.homework6.service.LibraryService;

@ShellComponent(value = "Library commands")
public class ShellLibraryCommandsImpl implements ShellLibraryCommands {

    private Book book;

    private final LibraryService lib;

    public ShellLibraryCommandsImpl(LibraryService lib) {
        this.lib = lib;
    }

    @Override
    public void addBook(String title) {
        lib.create(title);
    }

    @Override
    public void showBook(String title) {
        lib.read(title);
    }

    @Override
    public void updateBookTitle(String oldTitle, String newTitle) {
        lib.updateTitle(oldTitle, newTitle);
    }

    @Override
    public void deleteBook(String title) {
        lib.delete(title);
    }

    @Override
    public void addAuthorForBook(String title, String lastName) {
        lib.
    }

    @Override
    public void showBooksOfAuthor(String lastname) {

    }

    @Override
    public void updateAuthorLastName(String oldLastName, String newLastName) {

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
    public void updateGenreName(String oldGenre, String newGenre) {

    }

    @Override
    public void deleteGenreForBook(String title, String genre) {

    }

    @Override
    public void deleteAGenreTotally(String genre) {

    }
}
