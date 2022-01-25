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
    @Transactional(readOnly = true)
    public Optional<Book> findBookById(long id) {
        return booksRepository.findBookById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findBookByTitle(String title) {
        return booksRepository.findBookByTitle(title);
    }

    @Override
    @Transactional
    public void save(String title) {
        Book book = new Book(title);
        Book save = booksRepository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> getBookWithComments(String title) {
        return booksRepository.findBookWithCommentsByTitle(title);
    }

    @Override
    @Transactional
    public boolean updateTitle(String oldValue, String newValue) {
        int i = booksRepository.updateBookByTitle(oldValue, newValue);
        return i > 0;

        /*Optional<Book> optionalBook = booksRepository.findByName(oldValue);
        optionalBook.ifPresentOrElse(book -> {
            book.setTitle(newValue);
            booksRepository.save(book);
        }, () -> System.out.println("Such book not found"));*/
    }

    @Override
    @Transactional
    public boolean delete(String title) {
        int i = booksRepository.deleteByTitle(title);
        return i>0;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return booksRepository.findAll();
    }

}
