package ru.otus.Homework5.service.link;

import org.springframework.stereotype.Service;
import ru.otus.Homework5.dao.link.BookLinksDao;
import ru.otus.Homework5.domain.Author;

@Service
public class BookAuthorService<T> extends AbstractLinkLibraryService<Author> {

    public BookAuthorService(BookLinksDao<Author> dao) {
        super(dao);
    }
}
