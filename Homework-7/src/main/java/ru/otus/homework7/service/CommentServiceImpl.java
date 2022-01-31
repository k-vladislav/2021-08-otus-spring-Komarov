package ru.otus.homework7.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework7.dao_repositories.BookRepository;
import ru.otus.homework7.models.Book;
import ru.otus.homework7.models.Comment;

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
        Optional<Book> optionalBook = booksRepository.findById(bookId);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.addComment(new Comment(comment));
            booksRepository.save(book);
            result = true;
        }

        return result;
    }
}
