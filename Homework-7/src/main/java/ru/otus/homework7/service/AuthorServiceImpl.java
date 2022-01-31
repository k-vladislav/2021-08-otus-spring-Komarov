package ru.otus.homework7.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework7.dao_repositories.BookRepository;
import ru.otus.homework7.models.Author;
import ru.otus.homework7.models.Book;

import java.util.Optional;
import java.util.Set;


@Service
public class AuthorServiceImpl implements AuthorService {

    private final BookRepository bookRepository;

    public AuthorServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public boolean addAuthorForBook(long bookId, String lastName) {
        Optional<Boolean> aBoolean = bookRepository.findById(bookId).map(book -> {
            book.addAuthor(new Author(lastName));
            bookRepository.save(book);
            return true;
        });
        return aBoolean.orElse(false);
    }

    @Override
    @Transactional
    public boolean deleteAuthorFromBook(long bookId, long authorId) {
        boolean result = false;

        Optional<Book> bookByTitle = bookRepository.findById(bookId);
        if (bookByTitle.isPresent()) {
            Book book = bookByTitle.get();
            Set<Author> authors = book.getAuthors();

            Optional<Author> authorToDelete = authors.stream()
                    .filter(author -> authorId == author.getId())
                    .findFirst();

            if (authorToDelete.isPresent()) {
                book.removeAuthor(authorToDelete.get());
                bookRepository.save(book);
                result = true;
            }
        }

        return result;
    }
}
