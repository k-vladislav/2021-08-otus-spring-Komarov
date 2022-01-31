package ru.otus.homework7.shell;


import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework7.models.Book;
import ru.otus.homework7.service.AuthorService;
import ru.otus.homework7.service.BookService;
import ru.otus.homework7.service.CommentService;
import ru.otus.homework7.service.printer.BookInfoPrinter;

import java.util.List;
import java.util.Optional;

@ShellComponent(value = "Library commands")
public class BookShellImpl implements BookShell {

    private final BookService bookService;
    private final AuthorService authorService;
    private final CommentService commentService;

    public BookShellImpl(BookService bookService, AuthorService authorService, CommentService commentService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.commentService = commentService;
    }

    @Override
    @ShellMethod(key = "ab", value = "Add a book by title", group = "book")
    public void addBook(@ShellOption String title) {
        bookService.save(title);
    }

    @Override
    @ShellMethod(key = "sb", value = "Show book (incl. comments) by id", group = "book")
    public void showBook(@ShellOption long bookId) {
        Optional<Book> optionalBook = bookService.findById(bookId);
        optionalBook.ifPresentOrElse(BookInfoPrinter::print,
                () -> System.out.println("Such book not found"));
    }

    @Override
    @ShellMethod(key = "gab", value = "Print all books' info", group = "book")
    public void getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        books.forEach(BookInfoPrinter::print);
    }

    @Override
    @ShellMethod(key = "ubt", value = "Update book title by id", group = "book")
    public void updateBookTitle(@ShellOption long bookId, @ShellOption String newTitle) {
        boolean b = bookService.updateTitle(bookId, newTitle);
        if (b) System.out.println("Book title updated");
        else System.out.println("Book title NOT updated");
    }

    @Override
    @ShellMethod(key = "db", value = "Delete book by id", group = "book")
    public void deleteBook(@ShellOption long bookId) {
        boolean delete = bookService.delete(bookId);
        if (delete) System.out.println("Book deleted");
        else System.out.println("Book NOT deleted");
    }

    @Override
    @ShellMethod(key = "aab", value = "Add author for book", group = "author")
    public void addAuthorForBook(@ShellOption long bookId, @ShellOption String lastName) {
        boolean b = authorService.addAuthorForBook(bookId, lastName);
        if (b) System.out.println("Author added");
        else System.out.println("Author not added");
    }

    @Override
    @ShellMethod(key = "dab", value = "Delete author from book", group = "author")
    public void deleteAuthorForBook(@ShellOption long bookId, @ShellOption long authorId) {
        boolean b = authorService.deleteAuthorFromBook(bookId, authorId);
        if (b) System.out.println("Author deleted");
        else System.out.println("Author not deleted");
    }

    @Override
    @ShellMethod(key = "acb", value = "Add comment for book", group = "comment")
    public void addCommentForBook(@ShellOption long bookId, @ShellOption String comment) {
        boolean b = commentService.addCommentForBook(bookId, comment);
        if (b) System.out.println("Comment added");
        else System.out.println("Comment not added");
    }

}
