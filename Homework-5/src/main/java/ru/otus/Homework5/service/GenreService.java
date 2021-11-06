package ru.otus.Homework5.service;

import org.springframework.stereotype.Service;
import ru.otus.Homework5.dao.Dao;
import ru.otus.Homework5.domain.Genre;

import java.util.List;

@Service
public class GenreService implements CommonService<Genre>{

    private final Dao<Genre> dao;

    public GenreService(Dao<Genre> dao) {
        this.dao = dao;
    }
    
    @Override
    public int count() {
        return dao.count();
    }

    @Override
    public long insert(String value) {
        return dao.insert(new Genre(value));
    }

    @Override
    public Genre getById(long id) {
        return dao.getById(id);
    }

    @Override
    public List<Genre> getAll() {
        return dao.getAll();
    }

    @Override
    public void deleteById(long id) {
        dao.deleteById(id);
    }
}
