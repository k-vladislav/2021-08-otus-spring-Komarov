package ru.otus.Homework5.service;

import org.springframework.stereotype.Service;
import ru.otus.Homework5.dao.LibraryDao;
import ru.otus.Homework5.domain.Book;

import java.util.Set;

@Service
public class BookService extends AbstractLibraryService<Book> {

    public BookService(LibraryDao<Book> dao) {
        super(dao);
    }

    public void linkAuthorGenre(long bookId, Set<Long> authorIds, Set<Long> genreIds) {

    }
}
