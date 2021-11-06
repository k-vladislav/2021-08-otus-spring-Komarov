package ru.otus.Homework5.service;

import org.springframework.stereotype.Service;
import ru.otus.Homework5.dao.Dao;
import ru.otus.Homework5.domain.Book;

import java.util.List;

@Service
public class BookService implements CommonService<Book>{

    private final Dao<Book> dao;

    public BookService(Dao<Book> dao) {
        this.dao = dao;
    }
    
    @Override
    public int count() {
        return dao.count();
    }

    @Override
    public long insert(String value) {
        return dao.insert(new Book(value));
    }

    @Override
    public Book getById(long id) {
        return dao.getById(id);
    }

    @Override
    public List<Book> getAll() {
        return dao.getAll();
    }

    @Override
    public void deleteById(long id) {
        dao.deleteById(id);
    }
}
