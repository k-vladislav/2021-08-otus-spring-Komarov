package ru.otus.homework8.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.homework8.dao_repositories.BookRepository;
import ru.otus.homework8.models.Book;
import ru.otus.homework8.models.Comment;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;



class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BookRepository bookRepository;

    long bookId;

    @BeforeEach
    void setUp() {
        Book book = new Book("BOOK");
        book.addComment(new Comment("COMMENT_ONE"));
        book.addComment(new Comment("COMMENT_TWO"));
        book.addComment(new Comment("COMMENT_THREE"));
        Book persistedBook = bookRepository.save(book);
        bookId = Long.parseLong(persistedBook.getId());
    }

    @Test
    void addCommentForBook() {
        boolean isCommentAdded = commentService.addCommentForBook(bookId, "COMMENT_FOUR");
        assertTrue(isCommentAdded);
    }

    @Test
    void getCommentsForBook() {
        List<Comment> commentsForBook = commentService.getCommentsForBook(bookId);
        assertEquals(3, commentsForBook.size());
    }
}