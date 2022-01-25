package ru.otus.homework7.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework7.dao_repositories.BookRepository;
import ru.otus.homework7.models.Author;
import ru.otus.homework7.models.Book;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;


@Service
public class AuthorServiceImpl implements AuthorService {

    private final BookRepository bookRepository;

    public AuthorServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public boolean addAuthorForBook(String title, String lastName) {
        Optional<Boolean> aBoolean = bookRepository.findBookByTitle(title).map(book -> {
            book.addAuthor(new Author(lastName));
            bookRepository.save(book);
            return true;
        });
        return aBoolean.orElse(false);
    }

    @Override
    @Transactional
    public boolean deleteAuthorFromBook(String title, String lastName) {
        boolean result = false;

        Optional<Book> bookByTitle = bookRepository.findBookByTitle(title);
        if (bookByTitle.isPresent()) {
            Book book = bookByTitle.get();
            Set<Author> authors = book.getAuthors();

            Optional<Author> authorToDelete = authors.stream()
                    .filter(author -> lastName.equalsIgnoreCase(author.getLastName()))
                    .findFirst();

            if (authorToDelete.isPresent()) {
                book.removeAuthor(authorToDelete.get());
                bookRepository.save(book);
                result = true;
            }
        }

        return result;

/*        Optional<Boolean> aBoolean = bookRepository.findBookByTitle(title).map(book -> {
            Set<Author> authors = book.getAuthors();
            Optional<Author> authorForDeleting = book.getAuthors().stream()
                    .filter(author -> lastName.equalsIgnoreCase(author.getLastName()))
                    .findFirst();

            authorForDeleting


            Set<Author> cleanAuthors = authors.stream()
                    .filter(author -> !lastName.equalsIgnoreCase(author.getLastName()))
                    .collect(Collectors.toSet());
            book.setAuthors(cleanAuthors);
            bookRepository.save(book);
            return true;
        });
        return aBoolean.orElse(false);*/
    }
}
