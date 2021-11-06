package ru.otus.Homework5.service;

import org.springframework.stereotype.Service;
import ru.otus.Homework5.dao.Dao;
import ru.otus.Homework5.domain.Author;

import java.util.List;

@Service
public class AuthorService implements CommonService<Author>{

    private final Dao<Author> dao;

    public AuthorService(Dao<Author> dao) {
        this.dao = dao;
    }

    @Override
    public int count() {
        return dao.count();
    }

    @Override
    public long insert(String value) {
        return dao.insert(new Author(value));
    }

    @Override
    public Author getById(long id) {
        return dao.getById(id);
    }

    @Override
    public List<Author> getAll() {
        return dao.getAll();
    }

    @Override
    public void deleteById(long id) {
        dao.deleteById(id);
    }
}
