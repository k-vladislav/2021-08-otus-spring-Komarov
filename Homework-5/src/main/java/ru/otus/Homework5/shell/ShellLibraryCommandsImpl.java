package ru.otus.Homework5.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.Homework5.domain.Book;
import ru.otus.Homework5.service.WrapLibraryService;

import java.util.List;
import java.util.Optional;

@ShellComponent
public class ShellLibraryCommandsImpl implements ShellLibraryCommands {

    private final WrapLibraryService library;

    public ShellLibraryCommandsImpl(WrapLibraryService library) {
        this.library = library;
    }

    @Override
    @ShellMethod(value = "Add book, prints ID of inserted or existing book",group = "book")
    public void addBook(@ShellOption String title) {
        long id = library.addBook(title);
        System.out.println("Book ID = " + id);
    }

    @Override
    @ShellMethod(value = "Show book",group = "book")
    public void showBook(@ShellOption String title) {
        Optional<Book> book = library.showBook(title);
        book.ifPresentOrElse(System.out::println, () -> System.out.println("Book not found"));
    }

    @Override
    @ShellMethod(value = "Update book title",group = "book")
    public void updateBook(@ShellOption String oldTitle, @ShellOption String newTitle) {
        int i = library.updateBook(oldTitle, newTitle);
        if (i != 0) System.out.println("Update book OK");
        else System.out.println("Update book FAIL");
    }

    @Override
    @ShellMethod(value = "Delete book",group = "book")
    public void deleteBook(@ShellOption String title) {
        int i = library.deleteBook(title);
        if (i != 0) System.out.println("Delete book OK");
        else System.out.println("Delete book FAIL");
    }

    @Override
    @ShellMethod(value = "Add book for author",group = "author")
    public void addAuthorForBook(@ShellOption String title, @ShellOption String lastName) {
        long id = library.addAuthorForBook(title, lastName);
        if (id != 0) System.out.println("Add author for book OK");
        else System.out.println("Add author for book FAIL");
    }

    @Override
    @ShellMethod(value = "Show books for author",group = "author")
    public void showBooksOfAuthor(@ShellOption String lastname) {
        List<Book> books = library.showBooksOfAuthor(lastname);
        books.forEach(book -> System.out.println(book.getTitle()));
    }

    @Override
    @ShellMethod(value = "Update author",group = "author")
    public void updateAuthor(@ShellOption String oldLastName, @ShellOption String newLastName) {
        int i = library.updateAuthor(oldLastName, newLastName);
        if (i != 0) System.out.println("Update author OK");
        else System.out.println("Update author FAIL");
    }

    @Override
    @ShellMethod(value = "Delete author for book",group = "author")
    public void deleteAuthorForBook(@ShellOption String title, @ShellOption String lastName) {
        long id = library.deleteAuthorForBook(title, lastName);
        if (id != 0) System.out.println("Delete author for book OK");
        else System.out.println("Delete author for book FAIL");
    }

    @Override
    @ShellMethod(value = "Delete author",group = "author")
    public void deleteAuthorTotally(@ShellOption String lastName) {
        int i = library.deleteAuthorTotally(lastName);
        if (i != 0) System.out.println("Delete author OK");
        else System.out.println("Delete author FAIL");
    }

    @Override
    @ShellMethod(value = "Add genre for book",group = "genre")
    public void addGenreForBook(@ShellOption String title, @ShellOption String genre) {
        long id = library.addGenreForBook(title, genre);
        if (id != 0) System.out.println("Add genre for book OK");
        else System.out.println("Add genre for book FAIL");
    }

    @Override
    @ShellMethod(value = "Show books of genre",group = "genre")
    public void showBooksOfGenre(@ShellOption String genre) {
        List<Book> books = library.showBooksOfGenre(genre);
        books.forEach(book -> System.out.println(book.getTitle()));
    }

    @Override
    @ShellMethod(value = "Update genre",group = "genre")
    public void updateGenre(@ShellOption String oldGenre, @ShellOption String newGenre) {
        int i = library.updateGenre(oldGenre, newGenre);
        if (i != 0) System.out.println("Update genre OK");
        else System.out.println("Update genre FAIL");
    }

    @Override
    @ShellMethod(value = "Delete genre for book",group = "genre")
    public void deleteGenreForBook(@ShellOption String title, @ShellOption String genre) {
        long id = library.deleteGenreForBook(title, genre);
        if (id != 0) System.out.println("Delete genre for book OK");
        else System.out.println("Delete genre for book FAIL");
    }

    @Override
    @ShellMethod(value = "Delete genre",group = "genre")
    public void deleteAGenreTotally(@ShellOption String genre) {
        int i = library.deleteAGenreTotally(genre);
        if (i != 0) System.out.println("Delete genre OK");
        else System.out.println("Delete genre FAIL");
    }
}
