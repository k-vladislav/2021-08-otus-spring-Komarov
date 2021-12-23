package ru.otus.homework6.shell;


import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework6.models.Book;
import ru.otus.homework6.service.AuthorService;
import ru.otus.homework6.service.BookService;
import ru.otus.homework6.service.printer.BookInfoPrinter;

import java.util.Optional;

@ShellComponent(value = "Library commands")
public class BookShellImpl implements BookShell {

    private final BookService<Book> bookService;
    private final AuthorService authorService;

    public BookShellImpl(BookService<Book> bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    @ShellMethod(key = "ab", value = "add book", group = "book")
    public void addBook(@ShellOption String title) {
        bookService.save(title);
    }

    @Override
    @ShellMethod(key = "sb", value = "show book", group = "book")
    public void showBook(@ShellOption String title) {
        Optional<Book> optionalBook = bookService.getBookFullInfo(title);
        optionalBook.ifPresentOrElse(BookInfoPrinter::print,
                () -> System.out.println("Such book not found"));
    }

    @Override
    @ShellMethod(key = "ubt", value = "upd book title", group = "book")
    public void updateBookTitle(@ShellOption String oldTitle, @ShellOption String newTitle) {
        bookService.updateValue(oldTitle, newTitle);
    }

    @Override
    @ShellMethod(key = "db", value = "delete book", group = "book")
    public void deleteBook(@ShellOption String title) {
        bookService.delete(title);
    }

    @Override
    @ShellMethod(key = "aab", value = "add author for book", group = "author")
    public void addAuthorForBook(String title, String lastName) {
        boolean b = authorService.addAuthorForBook(title, lastName);
        if (b) System.out.println("Author added");
        else System.out.println("Author not added");
    }


    @Override
    @ShellMethod(key = "dab", value = "delete author for book", group = "author")
    public void deleteAuthorForBook(String title, String lastName) {
        boolean b = authorService.deleteAuthorForBook(title, lastName);
        if (b) System.out.println("Author deleted");
        else System.out.println("Author not deleted");
    }

}
