package ru.otus.homework6.shell;


import org.springframework.shell.standard.ShellComponent;
import ru.otus.homework6.service.LibraryService;

@ShellComponent(value = "Library commands")
public class ShellLibraryCommandsImpl implements ShellLibraryCommands {

    private final LibraryService books;

    public ShellLibraryCommandsImpl(LibraryService books) {
        this.books = books;
    }

    @Override
    public void addBook(String title) {
        books.create(title);
    }

    @Override
    public void showBook(String title) {
        books.read(title);
    }

    @Override
    public void updateBookTitle(String oldTitle, String newTitle) {
        books.updateTitle(oldTitle, newTitle);
    }

    @Override
    public void deleteBook(String title) {
        books.delete(title);
    }

    @Override
    public void addAuthorForBook(String title, String lastName) {
        books.
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
