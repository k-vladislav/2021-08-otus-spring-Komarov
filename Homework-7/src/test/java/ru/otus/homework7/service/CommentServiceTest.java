package ru.otus.homework7.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.homework7.dao_repositories.BookRepository;
import ru.otus.homework7.models.Book;
import ru.otus.homework7.models.Comment;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
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
        bookId = persistedBook.getId();
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