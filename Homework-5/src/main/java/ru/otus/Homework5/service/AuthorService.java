package ru.otus.Homework5.service;

import org.springframework.stereotype.Service;
import ru.otus.Homework5.dao.LibraryDao;
import ru.otus.Homework5.domain.Author;

@Service
public class AuthorService extends AbstractLibraryService<Author> {

    public AuthorService(LibraryDao<Author> dao) {
        super(dao);
    }
}
