package ru.otus.Homework5.service;

import org.springframework.stereotype.Service;
import ru.otus.Homework5.dao.LibraryDao;
import ru.otus.Homework5.domain.Author;

@Service
public class AuthorServiceClass extends LibraryServiceClass<Author>{

    public AuthorServiceClass(LibraryDao<Author> dao) {
        super(dao);
    }
}
