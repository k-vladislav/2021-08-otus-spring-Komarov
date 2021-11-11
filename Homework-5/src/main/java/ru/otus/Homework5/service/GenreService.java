package ru.otus.Homework5.service;

import org.springframework.stereotype.Service;
import ru.otus.Homework5.dao.LibraryDao;
import ru.otus.Homework5.domain.Genre;

@Service
public class GenreService extends AbstractLibraryService<Genre> {

    public GenreService(LibraryDao<Genre> dao) {
        super(dao);
    }
}
