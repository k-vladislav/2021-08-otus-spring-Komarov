package ru.otus.homework8.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework8.dao_repositories.BookRepository;
import ru.otus.homework8.models.Book;

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
        return booksRepository.findById((int) bookId);
    }

    @Override
    @Transactional
    public boolean updateTitle(long bookId, String newTitle) {
        Optional<Boolean> result = booksRepository.findById((int) bookId).map(book -> {
            book.setTitle(newTitle);
            Book savedBook = booksRepository.save(book);
            return newTitle.equals(savedBook.getTitle());
        });
        return result.orElse(false);
    }

    @Override
    @Transactional
    public boolean delete(long bookId) {
        if (booksRepository.existsById((int) bookId)) {
            booksRepository.deleteById((int) bookId);
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
