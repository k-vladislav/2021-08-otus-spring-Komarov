package ru.otus.Homework5.service.link;

import org.springframework.stereotype.Service;
import ru.otus.Homework5.dao.link.LinkingDao;
import ru.otus.Homework5.domain.Author;

@Service
public class BookAuthorService extends AbstractLinkingService<Author> {

    public BookAuthorService(LinkingDao<Author> dao) {
        super(dao);
    }
}
