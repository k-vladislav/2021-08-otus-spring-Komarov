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
    @ShellMethod(key = "gab", value = "Print all books' titles, authors and genres", group = "book")
    public void getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        books.forEach(BookInfoPrinter::print);
    }

    @Override
    @ShellMethod(key = "ab", value = "Add a book by title", group = "book")
    public void addBook(@ShellOption String title) {
        bookService.save(title);
        //todo print result
    }

    @Override
    @ShellMethod(key = "sb", value = "Show book (incl. comments) by title", group = "book")
    public void showBook(@ShellOption String title) {
        Optional<Book> optionalBook = bookService.getBookWithComments(title);
        optionalBook.ifPresentOrElse(BookInfoPrinter::printWithComments,
                () -> System.out.println("Such book not found"));
    }

    @Override
    @ShellMethod(key = "ubt", value = "Update book title by oldTitle", group = "book")
    public void updateBookTitle(@ShellOption String oldTitle, @ShellOption String newTitle) {
        boolean b = bookService.updateTitle(oldTitle, newTitle);
        if (b) System.out.println("Book title updated");
        else System.out.println("Book title NOT updated");
    }

    @Override
    @ShellMethod(key = "db", value = "Delete book by title", group = "book")
    public void deleteBook(@ShellOption String title) {
        boolean delete = bookService.delete(title);
        if (delete) System.out.println("Book deleted");
        else System.out.println("Book NOT deleted");
    }

    @Override
    @ShellMethod(key = "aab", value = "Add author for book", group = "author")
    public void addAuthorForBook(@ShellOption String title, @ShellOption String lastName) {
        boolean b = authorService.addAuthorForBook(title, lastName);
        if (b) System.out.println("Author added");
        else System.out.println("Author not added");
    }

    @Override
    @ShellMethod(key = "dab", value = "Delete author from book", group = "author")
    public void deleteAuthorForBook(@ShellOption String title, @ShellOption String lastName) {
        boolean b = authorService.deleteAuthorFromBook(title, lastName);
        if (b) System.out.println("Author deleted");
        else System.out.println("Author not deleted");
    }

    @Override
    @ShellMethod(key = "acb", value = "Add comment for book", group = "comment")
    public void addCommentForBook(@ShellOption String title, @ShellOption String comment) {
        boolean b = commentService.addCommentForBook(title, comment);
        if (b) System.out.println("Comment added");
        else System.out.println("Comment not added");
    }

}
