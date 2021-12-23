package ru.otus.homework6.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework6.dao_repositories.LibraryRepository;
import ru.otus.homework6.models.Book;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService<Book> {

    private final LibraryRepository<Book> booksRepository;

    public BookServiceImpl(LibraryRepository<Book> booksRepository) {
        this.booksRepository = booksRepository;
    }


    @Override
    @Transactional
    public void save(String title) {
        Book book = new Book(title);
        booksRepository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> getBookFullInfo(String title) {
        return booksRepository.findByNameWithAttributes(title);
    }

    @Override
    @Transactional
    public void updateValue(String oldValue, String newValue) {
        Optional<Book> optionalBook = booksRepository.findByName(oldValue);
        optionalBook.ifPresentOrElse(book -> {
            book.setTitle(newValue);
            booksRepository.save(book);
        }, () -> System.out.println("Such book not found"));
    }

    @Override
    @Transactional
    public void delete(String value) {
        Optional<Book> optionalBook = booksRepository.findByName(value);
        optionalBook.ifPresentOrElse(booksRepository::delete, () -> System.out.println("Such book not found"));
    }

}
