package ru.otus.Homework5.service;

import org.springframework.stereotype.Service;
import ru.otus.Homework5.dao.AuthorDao;
import ru.otus.Homework5.domain.Author;

import java.util.List;

@Service
public class AuthorService implements CommonService<Author>{

    private final AuthorDao authorDao;

    public AuthorService(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public int count() {
        return authorDao.count();
    }

    @Override
    public void insert(String value) {
        authorDao.insert(new Author(value));
    }

    @Override
    public Author getById(long id) {
        return authorDao.getById(id);
    }

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }

    @Override
    public void deleteById(long id) {
        authorDao.deleteById(id);
    }
}
