package ru.otus.Homework5.service;

import org.springframework.stereotype.Service;
import ru.otus.Homework5.dao.BookDao;
import ru.otus.Homework5.domain.Book;

import java.util.List;

@Service
public class BookService implements CommonService<Book>{

    private final BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }
    
    @Override
    public int count() {
        return bookDao.count();
    }

    @Override
    public void insert(String value) {
        bookDao.insert(new Book(value));
    }

    @Override
    public Book getById(long id) {
        return bookDao.getById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public void deleteById(long id) {
        bookDao.deleteById(id);
    }
}
