package ru.otus.homework8.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework8.dao_repositories.BookRepository;
import ru.otus.homework8.models.Book;
import ru.otus.homework8.models.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final BookRepository booksRepository;

    public CommentServiceImpl(BookRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    @Transactional
    public boolean addCommentForBook(long bookId, String comment) {
        boolean result = false;
        Optional<Book> optionalBook = booksRepository.findById((int) bookId);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.addComment(new Comment(comment));
            booksRepository.save(book);
            result = true;
        }

        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getCommentsForBook(long bookId) {
        List<Comment> comments = new ArrayList<>();
        Optional<Book> optionalBook = booksRepository.findById((int) bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            comments.addAll(book.getComments());
        }
        return comments;
    }
}