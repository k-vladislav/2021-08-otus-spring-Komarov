package ru.otus.Homework5.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.Homework5.service.AuthorService;
import ru.otus.Homework5.service.BookService;
import ru.otus.Homework5.service.GenreService;
import ru.otus.Homework5.service.LinkLibraryService;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ShellComponent
public class ShellLibraryCommandsImpl implements ShellLibraryCommands {

    private final AuthorService authorService;
    private final BookService bookService;
    private final GenreService genreService;
    private final LinkLibraryService linkLibraryService;

    public ShellLibraryCommandsImpl(AuthorService authorService, BookService bookService, GenreService genreService, LinkLibraryService linkLibraryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.genreService = genreService;
        this.linkLibraryService = linkLibraryService;
    }


    @ShellMethod(value = "Add book to library, max 3 authors, max 3 genres", key = "book-add")
    void addBook(@ShellOption(value = "--t") String title,
                 @ShellOption(value = "--a", arity = 3) String[] authors,
                 @ShellOption(value = "--g", arity = 3) String[] genres) {
        long bookId = bookService.insert(title);
        Set<Long> authorIds = Arrays.stream(authors).map(authorService::insert).collect(Collectors.toSet());
        Set<Long> genreIds = Arrays.stream(genres).map(genreService::insert).collect(Collectors.toSet());

        authorIds.forEach(authorId -> linkLibraryService.linkBookAuthor(bookId, authorId));
        genreIds.forEach(genreId -> linkLibraryService.linkBookGenre(bookId, genreId));
    }

    @Override
    @ShellMethod(value = "add book new", key = "addbook")
    public void addBookNew(String bookTitle) {
        long book_id = bookService.insert(bookTitle);
        if (book_id > 0) System.out.println("Book " + bookTitle + " added, book_id = " + book_id);
    }

    @Override
    @ShellMethod(value = "add author", key = "addauthor")
    public void addAuthor(@ShellOption String bookTitle, @ShellOption String authorLastName) {
        Optional<Long> idByValue = bookService.getIdByValue(bookTitle);
        if (idByValue.isPresent()) {
            long authorId = authorService.insert(authorLastName);
            Long bookId = idByValue.get();
            linkLibraryService.linkBookAuthor(bookId, authorId);

        } else {
            System.out.println("Book " + bookTitle + " not found, cancel");
        }
    }

    @Override
    @ShellMethod(value = "add genre", key = "addgenre")
    public void addGenre(@ShellOption String bookTitle, @ShellOption String genre) {
        Optional<Long> idByValue = bookService.getIdByValue(bookTitle);
        if (idByValue.isPresent()) {
            long genreId = genreService.insert(genre);
            Long bookId = idByValue.get();
            linkLibraryService.linkBookGenre(bookId, genreId);
        }
    }

    @Override
    @ShellMethod(value = "get book", key = "getbook")
    public void getBook(@ShellOption String bookTitle) {
        Optional<Long> idByValue = bookService.getIdByValue(bookTitle);
        if (idByValue.isPresent()) {
            System.out.println(bookService.getById(idByValue.get()).get()); //todo optional check
        }
    }

    @Override
    public void getAllBooks() {

    }

    @Override
    public void countBooks() {

    }

    @Override
    public void delBook(String bookTitle) {

    }
}

