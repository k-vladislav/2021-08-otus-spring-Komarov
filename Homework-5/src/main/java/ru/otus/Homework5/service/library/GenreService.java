package ru.otus.Homework5.service.library;

import org.springframework.stereotype.Service;
import ru.otus.Homework5.dao.library.LibraryDao;
import ru.otus.Homework5.domain.Genre;

import java.util.Optional;

@Service
public class GenreService extends AbstractLibraryService<Genre> {

    public GenreService(LibraryDao<Genre> dao) {
        super(dao);
    }

    @Override
    public long add(String genre) {
        return super.add(genre);
    }

    @Override
    public Optional<Long> getId(String genre) {
        return super.getId(genre);
    }
}
