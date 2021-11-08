package ru.otus.Homework5.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.Homework5.service.AuthorServiceClass;
import ru.otus.Homework5.service.BookServiceClass;
import ru.otus.Homework5.service.GenreServiceClass;
import ru.otus.Homework5.service.LinkLibraryService;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ShellComponent
public class ShellLibraryCommandsImpl implements ShellLibraryCommands {

    private final AuthorServiceClass authorServiceClass;
    private final BookServiceClass bookServiceClass;
    private final GenreServiceClass genreServiceClass;
    private final LinkLibraryService linkLibraryService;

    public ShellLibraryCommandsImpl(AuthorServiceClass authorServiceClass, BookServiceClass bookServiceClass, GenreServiceClass genreServiceClass, LinkLibraryService linkLibraryService) {
        this.authorServiceClass = authorServiceClass;
        this.bookServiceClass = bookServiceClass;
        this.genreServiceClass = genreServiceClass;
        this.linkLibraryService = linkLibraryService;
    }


    @ShellMethod(value = "Add book to library, max 3 authors, max 3 genres", key = "book-add")
    void addBook(@ShellOption(value = "--t") String title,
                 @ShellOption(value = "--a", arity = 3) String[] authors,
                 @ShellOption(value = "--g", arity = 3) String[] genres) {
        long bookId = bookServiceClass.insert(title);
        Set<Long> authorIds = Arrays.stream(authors).map(authorServiceClass::insert).collect(Collectors.toSet());
        Set<Long> genreIds = Arrays.stream(genres).map(genreServiceClass::insert).collect(Collectors.toSet());

        authorIds.forEach(authorId -> linkLibraryService.linkBookAuthor(bookId, authorId));
        genreIds.forEach(genreId -> linkLibraryService.linkBookGenre(bookId, genreId));
    }

    @Override
    @ShellMethod(value = "add book new", key = "addbook")
    public void addBookNew(String bookTitle) {
        long book_id = bookServiceClass.insert(bookTitle);
        if (book_id > 0) System.out.println("Book " + bookTitle + " added, book_id = " + book_id);
    }

    @Override
    @ShellMethod(value = "add author", key = "addauthor")
    public void addAuthor(@ShellOption String bookTitle, @ShellOption String authorLastName) {
        Optional<Long> idByValue = bookServiceClass.getIdByValue(bookTitle);
        if (idByValue.isPresent()) {
            long authorId = authorServiceClass.insert(authorLastName);
            Long bookId = idByValue.get();
            linkLibraryService.linkBookAuthor(bookId, authorId);

        } else {
            System.out.println("Book " + bookTitle + " not found, cancel");
        }
    }

    @Override
    @ShellMethod(value = "add genre", key = "addgenre")
    public void addGenre(@ShellOption String bookTitle, @ShellOption String genre) {
        Optional<Long> idByValue = bookServiceClass.getIdByValue(bookTitle);
        if (idByValue.isPresent()) {
            long genreId = genreServiceClass.insert(genre);
            Long bookId = idByValue.get();
            linkLibraryService.linkBookGenre(bookId, genreId);
        }
    }

    @Override
    @ShellMethod(value = "get book", key = "getbook")
    public void getBook(@ShellOption String bookTitle) {
        Optional<Long> idByValue = bookServiceClass.getIdByValue(bookTitle);
        if (idByValue.isPresent()) {
            System.out.println(bookServiceClass.getById(idByValue.get()).get());
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

