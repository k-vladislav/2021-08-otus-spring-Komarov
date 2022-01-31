package ru.otus.homework7.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework7.dao_repositories.BookRepository;
import ru.otus.homework7.models.Book;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository booksRepository;

    public BookServiceImpl(BookRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    @Transactional
    public void save(String title) {
        Book book = new Book(title);
        booksRepository.save(book);
    }

    @Override
    public Optional<Book> findById(long bookId) {
        return booksRepository.findById(bookId);
    }

    @Override
    @Transactional
    public boolean updateTitle(long bookId, String newTitle) {
        Optional<Boolean> result = booksRepository.findById(bookId).map(book -> {
            book.setTitle(newTitle);
            Book savedBook = booksRepository.save(book);
            return newTitle.equals(savedBook.getTitle());
        });
        return result.orElse(false);
    }

    @Override
    @Transactional
    public boolean delete(long bookId) {
        if (booksRepository.existsById(bookId)) {
            booksRepository.deleteById(bookId);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return booksRepository.findAll();
    }

}
