package ru.otus.Homework5.service;

import org.springframework.stereotype.Service;
import ru.otus.Homework5.dao.GenreDao;
import ru.otus.Homework5.domain.Genre;

import java.util.List;

@Service
public class GenreService implements CommonService<Genre>{

    private final GenreDao genreDao;

    public GenreService(GenreDao genreDao) {
        this.genreDao = genreDao;
    }
    
    @Override
    public int count() {
        return genreDao.count();
    }

    @Override
    public void insert(String value) {
        genreDao.insert(new Genre(value));
    }

    @Override
    public Genre getById(long id) {
        return genreDao.getById(id);
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }

    @Override
    public void deleteById(long id) {
        genreDao.deleteById(id);
    }
}
