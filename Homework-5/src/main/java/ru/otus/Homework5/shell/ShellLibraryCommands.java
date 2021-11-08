package ru.otus.Homework5.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.Homework5.service.AuthorServiceClass;
import ru.otus.Homework5.service.BookServiceClass;
import ru.otus.Homework5.service.GenreServiceClass;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@ShellComponent
public class ShellLibraryCommands {

    private final AuthorServiceClass authorServiceClass;
    private final BookServiceClass bookServiceClass;
    private final GenreServiceClass genreServiceClass;

    public ShellLibraryCommands(AuthorServiceClass authorServiceClass, BookServiceClass bookServiceClass, GenreServiceClass genreServiceClass) {
        this.authorServiceClass = authorServiceClass;
        this.bookServiceClass = bookServiceClass;
        this.genreServiceClass = genreServiceClass;
    }


    @ShellMethod(value = "Add book to library, max 3 authors, max 3 genres", key = "book-add")
    void addBook(@ShellOption(value = "--t") String title,
                 @ShellOption(value = "--a", arity = 3) String[] authors,
                 @ShellOption(value = "--g", arity = 3) String[] genres) {
        long bookId = bookServiceClass.insert(title);
        Set<Long> authorIds = Arrays.stream(authors).map(authorServiceClass::insert).collect(Collectors.toSet());
        Set<Long> genreIds = Arrays.stream(genres).map(genreServiceClass::insert).collect(Collectors.toSet());
        System.out.println("Book " + title + " inserted");
        System.out.println("authorsId:");
        authorIds.forEach(System.out::println);
        System.out.println("genreIds");
        genreIds.forEach(System.out::println);
    }
}

