package ru.otus.Homework5.service.link;

import org.springframework.stereotype.Service;
import ru.otus.Homework5.dao.link.LinkingDao;
import ru.otus.Homework5.domain.Genre;

@Service
public class BookGenreService extends AbstractLinkingService<Genre> {

    public BookGenreService(LinkingDao<Genre> dao) {
        super(dao);
    }
}
