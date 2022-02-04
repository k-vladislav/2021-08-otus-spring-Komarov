package ru.otus.homework7.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.homework7.dao_repositories.BookRepository;
import ru.otus.homework7.models.Author;
import ru.otus.homework7.models.Book;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    long bookId;

    @BeforeEach
    void setUp() {
        Book book = new Book("BOOK");
        book.addAuthor(new Author("AUTHOR_ONE"));
        book.addAuthor(new Author("AUTHOR_TWO"));
        book.addAuthor(new Author("AUTHOR_THREE"));
        Book persistedBook = bookRepository.save(book);
        bookId = persistedBook.getId();
    }

    @Test
    void save() {
        bookService.save("BOOK_FOR_SAVE");

        Book probeBook = new Book("BOOK_FOR_SAVE");
        ExampleMatcher bookMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withMatcher("title", ExampleMatcher.GenericPropertyMatcher::caseSensitive);
        Example<Book> exampleBook = Example.of(probeBook, bookMatcher);

        boolean isBookExist = bookRepository.exists(exampleBook);
        assertTrue(isBookExist);
    }

    @Test
    void findById() {
        Optional<Book> optionalBook = bookService.findById(bookId);
        assertTrue(optionalBook.isPresent());
    }

    @Test
    void updateTitle() {
        boolean isChangedTitle = bookService.updateTitle(bookId, "BOOK_CHANGED_TITLE");
        assertTrue(isChangedTitle);

        Book probeBook = new Book("BOOK_CHANGED_TITLE");
        ExampleMatcher bookMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withMatcher("title", ExampleMatcher.GenericPropertyMatcher::caseSensitive);
        Example<Book> exampleBook = Example.of(probeBook, bookMatcher);

        boolean isBookExist = bookRepository.exists(exampleBook);
        assertTrue(isBookExist);

    }

    @Test
    void delete() {
        boolean isDeletedBook = bookService.delete(bookId);
        assertTrue(isDeletedBook);

        boolean isDeletedBookExists = bookRepository.existsById(bookId);
        assertFalse(isDeletedBookExists);
    }

    @Test
    void getAllBooks() {
        List<Book> allBooks = bookService.getAllBooks();
        assertTrue(allBooks.size()>0);
    }
}