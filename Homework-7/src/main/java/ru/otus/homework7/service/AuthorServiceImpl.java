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
        boolean isAuthorAdded = false;
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.addAuthor(new Author(lastName));
            bookRepository.save(book);
            isAuthorAdded = true;
        }
        return isAuthorAdded;
    }

    @Override
    @Transactional
    public boolean deleteAuthorFromBook(long bookId, long authorId) {
        boolean isAuthorDeleted = false;
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            Set<Author> authors = book.getAuthors();

            Optional<Author> optionalAuthor = authors.stream()
                    .filter(author -> authorId == author.getId())
                    .findFirst();

            if (optionalAuthor.isPresent()) {
                book.removeAuthor(optionalAuthor.get());
                bookRepository.save(book);
                isAuthorDeleted = true;
            }
        }
        return isAuthorDeleted;
    }
}
