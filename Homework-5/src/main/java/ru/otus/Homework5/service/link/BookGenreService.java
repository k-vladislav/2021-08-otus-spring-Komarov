package ru.otus.Homework5.service.link;

import org.springframework.stereotype.Service;
import ru.otus.Homework5.dao.link.BookLinksDao;
import ru.otus.Homework5.domain.Genre;

@Service
public class BookGenreService<T> extends AbstractLinkLibraryService<Genre> {

    public BookGenreService(BookLinksDao<Genre> dao) {
        super(dao);
    }
}
