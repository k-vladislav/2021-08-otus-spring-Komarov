package ru.otus.homework8.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import ru.otus.homework8.dao_repositories.BookRepository;
import ru.otus.homework8.models.Author;
import ru.otus.homework8.models.Book;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        bookId = Long.parseLong(persistedBook.getId());
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

        boolean isDeletedBookExists = bookRepository.existsById((int) bookId);
        assertFalse(isDeletedBookExists);
    }

    @Test
    void getAllBooks() {
        List<Book> allBooks = bookService.getAllBooks();
        assertTrue(allBooks.size()>0);
    }
}