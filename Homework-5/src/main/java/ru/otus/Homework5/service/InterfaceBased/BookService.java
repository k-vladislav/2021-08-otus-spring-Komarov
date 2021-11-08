package ru.otus.Homework5.service.InterfaceBased;

import org.springframework.stereotype.Service;
import ru.otus.Homework5.dao.LibraryDao;
import ru.otus.Homework5.domain.Book;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements LibraryService<Book> {

    private final LibraryDao<Book> dao;

    public BookService(LibraryDao<Book> dao) {
        this.dao = dao;
    }

    @Override
    public int count() {
        return dao.count();
    }

    @Override
    public long insert(String value) {
        if (dao.getIdByValue(value).isEmpty()) {
            return dao.insert(value);
        } else {
            return dao.getIdByValue(value).get();
        }
    }

    @Override
    public Optional<Book> getById(long id) {
        return dao.getById(id);
    }

    @Override
    public Optional<List<Book>> getAll() {
        return dao.getAll();
    }

    @Override
    public void deleteById(long id) {
        dao.deleteById(id);
    }

    @Override
    public Optional<Long> getIdByValue(String value) {
        return dao.getIdByValue(value);
    }
}
