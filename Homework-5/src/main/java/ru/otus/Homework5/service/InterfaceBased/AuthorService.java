package ru.otus.Homework5.service.InterfaceBased;

import org.springframework.stereotype.Service;
import ru.otus.Homework5.dao.LibraryDao;
import ru.otus.Homework5.domain.Author;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService implements LibraryService<Author> {

    private final LibraryDao<Author> dao;

    public AuthorService(LibraryDao<Author> dao) {
        this.dao = dao;
    }

    @Override
    public int count() {
        return dao.count();
    }

    @Override
    public long insert(String value) {
        return dao.insert(value);
    }

    @Override
    public Optional<Author> getById(long id) {
        return dao.getById(id);
    }

    @Override
    public Optional<List<Author>> getAll() {
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
