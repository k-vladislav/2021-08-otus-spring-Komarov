package ru.otus.Homework5.service.library;

import org.springframework.stereotype.Service;
import ru.otus.Homework5.dao.library.LibraryDao;
import ru.otus.Homework5.domain.Book;

import java.util.Optional;

@Service
public class BookService extends AbstractLibraryService<Book> {

    public BookService(LibraryDao<Book> dao) {
        super(dao);
    }


    @Override
    public long add(String title) {
        return super.add(title);
    }

    @Override
    public Optional<Long> getId(String title) {
        return super.getId(title);
    }


}
