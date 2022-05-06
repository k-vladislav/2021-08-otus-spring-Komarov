package ru.otus.homework8.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.homework8.dao_repositories.BookRepository;
import ru.otus.homework8.models.Author;
import ru.otus.homework8.models.Book;

import static org.junit.jupiter.api.Assertions.assertTrue;


class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookRepository bookRepository;

    long bookId;
    long author_id;

    @BeforeEach
    void setUp() {
        Book book = new Book("BOOK");
        book.addAuthor(new Author("AUTHOR_ONE"));
        book.addAuthor(new Author("AUTHOR_TWO"));
        book.addAuthor(new Author("AUTHOR_THREE"));
        Book persistedBook = bookRepository.save(book);
        bookId = Long.parseLong(persistedBook.getId());
        author_id = Long.parseLong(persistedBook.getAuthors().stream().findAny().map(Author::getId).orElse(String.valueOf(0L)));
    }

    @Test
    void addAuthorForBook() {
        boolean author_four = authorService.addAuthorForBook(bookId, "AUTHOR_FOUR");
        assertTrue(author_four);

    }

    @Test
    void deleteAuthorFromBook() {
        boolean deleteAuthorOne = authorService.deleteAuthorFromBook(bookId, author_id);
        assertTrue(deleteAuthorOne);
    }
}