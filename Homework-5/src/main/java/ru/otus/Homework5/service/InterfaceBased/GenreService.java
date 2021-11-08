package ru.otus.Homework5.service.InterfaceBased;

import org.springframework.stereotype.Service;
import ru.otus.Homework5.dao.LibraryDao;
import ru.otus.Homework5.domain.Genre;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

@Service
public class GenreService implements LibraryService<Genre> {

    private final LibraryDao<Genre> dao;

    public GenreService(LibraryDao<Genre> dao) {
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
    public Optional<Genre> getById(long id) {
        return dao.getById(id);
    }

    @Override
    public Optional<List<Genre>> getAll() {
        return dao.getAll();
    }

    @Override
    public void deleteById(long id) {
        dao.deleteById(id);
    }

    @Override
    public OptionalLong getIdByValue(String value) {
        return dao.getIdByValue(value);
    }
}
