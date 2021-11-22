package ru.otus.Homework5.service.library;

import org.springframework.stereotype.Service;
import ru.otus.Homework5.dao.library.LibraryDao;
import ru.otus.Homework5.domain.Author;

import java.util.Optional;

@Service
public class AuthorService extends AbstractLibraryService<Author> {


    public AuthorService(LibraryDao<Author> dao) {
        super(dao);
    }

    @Override
    public long add(String lastName) {
        return super.add(lastName);
    }

    @Override
    public Optional<Long> getId(String lastName) {
        return super.getId(lastName);
    }
}
