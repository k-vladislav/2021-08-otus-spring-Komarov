package ru.otus.homework6.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework6.dao_repositories.LibraryRepository;
import ru.otus.homework6.models.Book;
import ru.otus.homework6.models.Comment;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final LibraryRepository<Book> booksRepository;

    public CommentServiceImpl(LibraryRepository<Book> booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    @Transactional
    public boolean addCommentForBook(String title, String comment) {
        Optional<Boolean> aBoolean = booksRepository.findByNameWithComments(title).map(book -> {
            book.addComment(new Comment(comment));
/*            List<Comment> comments = book.getComments();
            comments.add(new Comment(comment));
            book.setComments(comments);*/
            booksRepository.save(book);
            return true;
        });
        return aBoolean.orElse(false);
    }
}
